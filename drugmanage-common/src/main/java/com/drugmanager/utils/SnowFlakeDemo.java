package com.drugmanager.utils;


	/**
	 * ѩ���㷨demo
	 * <p>
	 * Snowflake���ɵ���Long���͵�ID��һ��Long����ռ8���ֽڣ�ÿ���ֽ�ռ8���أ�Ҳ����˵һ��Long����ռ64�����ء�
	 * <p>
	 * Snowflake ID��ɽṹ������λ��ռ1���أ�+ ʱ�����ռ41���أ�+ ����ID��ռ5���أ�+
	 *                      �������ģ�ռ5���أ�+ ����ֵ��ռ12���أ����ܹ�64������ɵ�һ��Long���͡�
	 * ��һ��bitλ��1bit����Java��long�����λ�Ƿ���λ����������������0��������1��
	 *                     һ������ID��Ϊ����������Ĭ��Ϊ0��
	 * ʱ������֣�41bit�������뼶��ʱ�䣬������浱ǰʱ����������ã���ǰʱ��� - �̶���ʼʱ������Ĳ�ֵ��
	 *                    ����ʹ������ID�Ӹ�С��ֵ��ʼ��41λ��ʱ�������ʹ��69�꣬
	 *                    (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69��
	 * ��������id��10bit����Ҳ������workId���������������ã��������߻�������϶����ԡ�
	 * ���кŲ��֣�12bit��������ֵ֧��ͬһ������ͬһ���ڵ��������4096��ID
	 *
	 */
public class SnowFlakeDemo {
	    /**
	     * ��ʼ��ʱ���
	     */
	    private final static long START_TIMESTAMP = 1480166465631L;
	    /**
	     * ϵ�к�ռ��λ��
	     */
	    private final static long SEQUENCE_BIT = 12;
	    /**
	     * ������ʶռ�õ�λ��
	     */
	    private final static long MACHINE_BIT = 5;
	    /**
	     * ��������ռ�õ�λ��
	     */
	    private final static long DATA_CENTER_BIT = 5;
	    /**
	     * ���кŵ����ֵ
	     */
	    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
	    /**
	     * ������ʶ�����ֵ
	     */
	    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MAX_SEQUENCE);
	    /**
	     * �������Ķ˵����ֵ
	     */
	    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
	    /**
	     * ���к�����ֵ
	     */
	    private final static long MACHINE_LEFT = SEQUENCE_BIT;

	    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

	    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + MACHINE_BIT;
	    /**
	     * ��������id
	     */
	    private static long dataCenterId;
	    /**
	     * ������
	     */
	    private static long machineId;
	    /**
	     * ���к�
	     * Ĭ��0
	     */
	    private static long sequence = 0L;
	    /**
	     * ��һ��ʱ���
	     */
	    private static long lastTimeStamp = -1L;

	    private static final String lockVal = "tmp";

	    /**
	     * ��ȡ��ǰʱ���������
	     * �����ǰʱ��С����һ��ʱ�䣬�����ֻ�ȡ��ǰʱ�䣬ֱ����ȡ�����õ�����ʱ��
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
	     * ��ǰʱ���
	     */
	    private static long getNewTimeStamp() {
	        return System.currentTimeMillis();
	    }

	    public SnowFlakeDemo(long dataCenterId, long machineId) throws IllegalAccessException {
	        if (dataCenterId > MAX_DATA_CENTER_NUM|| dataCenterId < 0) {
	            throw new IllegalAccessException("��������id��ֵ�쳣�����������ֵ����С����0");
	        }
	        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
	            throw new IllegalAccessException("�豸id��ֵ�쳣���������ֵ����С����0");
	        }
	        this.dataCenterId = dataCenterId;
	        this.machineId = machineId;
	    }

	    /**
	     * ��ȡ��һ��id
	     *
	     */
	    public static long getNextId() {
	        synchronized (lockVal) {
	            long currentTimeStamp = getNewTimeStamp();
	            if (currentTimeStamp < lastTimeStamp) {
	                throw new RuntimeException("��һ��ʹ�õ�ʱ������ڵ�ǰʱ�䣬�ܾ�����id");
	            }
	            if (currentTimeStamp == lastTimeStamp) {
	                //ʱ����ȣ����к�����
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
	     * ���û�п�����ļ�Ⱥ
	     * ���豸id���Բ��÷�����������ip���һ��ֵ����
	     *
	     */
	    public static void main(String[] args) throws IllegalAccessException {
	        SnowFlakeDemo snowFlakeDemo = new SnowFlakeDemo(2, 3);
	        for (int i = 0; i < (1 << 4); i++) {
	            System.out.println(snowFlakeDemo.getNextId());
	        }

	    }

	}
