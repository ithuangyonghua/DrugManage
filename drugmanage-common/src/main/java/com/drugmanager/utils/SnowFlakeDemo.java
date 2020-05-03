package com.drugmanager.utils;


	/**
	 * 雪花算法demo
	 * <p>
	 * Snowflake生成的是Long类型的ID，一个Long类型占8个字节，每个字节占8比特，也就是说一个Long类型占64个比特。
	 * <p>
	 * Snowflake ID组成结构：正数位（占1比特）+ 时间戳（占41比特）+ 机器ID（占5比特）+
	 *                      数据中心（占5比特）+ 自增值（占12比特），总共64比特组成的一个Long类型。
	 * 第一个bit位（1bit）：Java中long的最高位是符号位代表正负，正数是0，负数是1，
	 *                     一般生成ID都为正数，所以默认为0。
	 * 时间戳部分（41bit）：毫秒级的时间，不建议存当前时间戳，而是用（当前时间戳 - 固定开始时间戳）的差值，
	 *                    可以使产生的ID从更小的值开始；41位的时间戳可以使用69年，
	 *                    (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69年
	 * 工作机器id（10bit）：也被叫做workId，这个可以灵活配置，机房或者机器号组合都可以。
	 * 序列号部分（12bit），自增值支持同一毫秒内同一个节点可以生成4096个ID
	 *
	 */
public class SnowFlakeDemo {
	    /**
	     * 起始的时间戳
	     */
	    private final static long START_TIMESTAMP = 1480166465631L;
	    /**
	     * 系列号占用位数
	     */
	    private final static long SEQUENCE_BIT = 12;
	    /**
	     * 机器标识占用的位数
	     */
	    private final static long MACHINE_BIT = 5;
	    /**
	     * 数据中心占用的位数
	     */
	    private final static long DATA_CENTER_BIT = 5;
	    /**
	     * 序列号的最大值
	     */
	    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
	    /**
	     * 机器标识号最大值
	     */
	    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MAX_SEQUENCE);
	    /**
	     * 数据中心端的最大值
	     */
	    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
	    /**
	     * 序列号左移值
	     */
	    private final static long MACHINE_LEFT = SEQUENCE_BIT;

	    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

	    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + MACHINE_BIT;
	    /**
	     * 数据中心id
	     */
	    private static long dataCenterId;
	    /**
	     * 机器码
	     */
	    private static long machineId;
	    /**
	     * 序列号
	     * 默认0
	     */
	    private static long sequence = 0L;
	    /**
	     * 上一次时间戳
	     */
	    private static long lastTimeStamp = -1L;

	    private static final String lockVal = "tmp";

	    /**
	     * 获取当前时间戳并返回
	     * 如果当前时间小于上一秒时间，则重现获取当前时间，直到获取到可用的最新时间
	     *
	     */
	    private static long getNextMill() {
	        long mill = getNewTimeStamp();
	        while (mill <= lastTimeStamp) {
	            mill = getNewTimeStamp();
	        }
	        return mill;
	    }

	    /**
	     * 当前时间戳
	     */
	    private static long getNewTimeStamp() {
	        return System.currentTimeMillis();
	    }

	    public SnowFlakeDemo(long dataCenterId, long machineId) throws IllegalAccessException {
	        if (dataCenterId > MAX_DATA_CENTER_NUM|| dataCenterId < 0) {
	            throw new IllegalAccessException("数据中心id的值异常，大于最大数值或者小于了0");
	        }
	        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
	            throw new IllegalAccessException("设备id的值异常，大于最大值或者小于了0");
	        }
	        this.dataCenterId = dataCenterId;
	        this.machineId = machineId;
	    }

	    /**
	     * 获取下一个id
	     *
	     */
	    public static long getNextId() {
	        synchronized (lockVal) {
	            long currentTimeStamp = getNewTimeStamp();
	            if (currentTimeStamp < lastTimeStamp) {
	                throw new RuntimeException("上一次使用的时间戳大于当前时间，拒绝生成id");
	            }
	            if (currentTimeStamp == lastTimeStamp) {
	                //时间相等，序列号自增
	                sequence = (sequence + 1) & MAX_SEQUENCE;
	                if (sequence == 0L) {
	                    currentTimeStamp = getNextMill();
	                }
	            } else {
	                sequence = 0L;
	            }
	            lastTimeStamp = currentTimeStamp;
	            return (currentTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT
	                    | dataCenterId << DATA_CENTER_LEFT
	                    | machineId << MACHINE_LEFT
	                    | sequence;
	        }
	    }
	    /**
	     * 如果没有跨机房的集群
	     * 该设备id可以采用服务器的内网ip最后一段值即可
	     *
	     */
	    public static void main(String[] args) throws IllegalAccessException {
	        SnowFlakeDemo snowFlakeDemo = new SnowFlakeDemo(2, 3);
	        for (int i = 0; i < (1 << 4); i++) {
	            System.out.println(snowFlakeDemo.getNextId());
	        }

	    }

	}
