package com.drugmanager.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.Inventory;
import com.drugmanager.bean.Sdetail;
import com.drugmanager.bean.Storage;
import com.drugmanager.bean.User;
import com.drugmanager.dao.StorageDao;
import com.drugmanager.service.InventoryService;
import com.drugmanager.service.SdetailService;
import com.drugmanager.service.StorageService;
import com.drugmanager.utils.DateUtils;
import com.drugmanager.utils.SnowFlakeDemo;
import com.drugmanager.vo.StorageVo;

@Service
public class StorageServiceImpl implements StorageService {
	@Autowired
	SdetailService sdetailService;
  
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	StorageDao storageDao;

	public Object saveStorage(StorageVo storageVo, HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			long nextId = SnowFlakeDemo.getNextId();
			String id = "O" + String.valueOf(nextId);
			// session信息
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			// 先保存入库信息表
			Storage storage = new Storage();
			storage.setCreateTime(DateUtils.formatDate(new Date()));
			storage.setId(id);
			storage.setMonystatus(0);
			storage.setStatus(0);
			storage.setSid(storageVo.getSid());
			storage.setLoginacct(loginUser.getLoginacct());
			storageDao.saveStorage(storage);// 保存库存信息
			// 进行数据筛选
			List<Sdetail> sdetails = storageVo.getSdetails();// 前端传过来的数据,我们是不是需要进行筛选是否有重复值
			Map<Integer, Sdetail> map = new HashMap();
			List<Sdetail> resultlist = new ArrayList<Sdetail>();
			for (int i = 0; i < sdetails.size(); i++) {
				sdetails.get(i).setDetailid(id);
				if (map.containsKey(sdetails.get(i).getDrugid())) {// 不为空
					sdetails.get(i).setNownum(sdetails.get(i).getNownum()
							+ Float.valueOf(map.get(sdetails.get(i).getDrugid()).getNownum()));
					map.put(sdetails.get(i).getDrugid(), sdetails.get(i));
				} else {// 为空
					map.put(sdetails.get(i).getDrugid(), sdetails.get(i));
				}
			}
			for (Entry<Integer, Sdetail> i : map.entrySet()) {
				resultlist.add(i.getValue());
			}
			sdetailService.saveSdetails(resultlist);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	public List<Storage> pageQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return storageDao.pageQuery(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return storageDao.pageQueryCount(map);
	}

	public Object updateStatusId(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		storageDao.updateStatusId(id);
		ajaxResult.setSuccess(true);
		// 获取新入库的药品
		List<Sdetail> SdetailList = sdetailService.queryById(id);
		//获取之前的库存
		List<Inventory> InventoryList = inventoryService.queryAll();
		//修改库存
		for(int i=0;i<SdetailList.size();i++){//新
			for(int j=0;j<InventoryList.size();j++){//旧
				 if(SdetailList.get(i).getDrugid()==InventoryList.get(j).getDrugid()){
					 //需要入库
					Integer newSum =  (int) (InventoryList.get(j).getInventorySum()+ SdetailList.get(i).getNownum());
					 //获取之前的库存
					 inventoryService.updateSumByDrugId(InventoryList.get(i).getId(),newSum);
				 }
			}
		}
		return ajaxResult;
	}
   
	/**
	 * 删除
	 */
	public Object deleteStorageById(String id) {
		// TODO Auto-generated method stub
		AjaxResult ajaxResult = new AjaxResult();
		try {
			//先删除明细表
			sdetailService.deleteSdetailById(id);
			//然后再删除入库表
			storageDao.deleteStorageById(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		
		
		return ajaxResult;
	}
    /**
     * 批量删除
     */
	public Object deleteStorageBouth(String [] id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap();
			map.put("detailids", id);
			//先删除明细表
			sdetailService.deleteSdetaileBouth(map);
			//再删除入库表
			storageDao.deleteStorageBouth(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		// TODO Auto-generated method stub
//		storageDao.deleteStorageBouth(map);
		return ajaxResult;
	}

	public Object updateMoneryStatusId(String id, Integer monery) {
		// TODO Auto-generated method stub
		AjaxResult ajaxResult = new AjaxResult();
		try {
			storageDao.updateMoneryStatusId(id,monery);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	

}
