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
			// session��Ϣ
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			// �ȱ��������Ϣ��
			Storage storage = new Storage();
			storage.setCreateTime(DateUtils.formatDate(new Date()));
			storage.setId(id);
			storage.setMonystatus(0);
			storage.setStatus(0);
			storage.setSid(storageVo.getSid());
			storage.setLoginacct(loginUser.getLoginacct());
			storageDao.saveStorage(storage);// ��������Ϣ
			// ��������ɸѡ
			List<Sdetail> sdetails = storageVo.getSdetails();// ǰ�˴�����������,�����ǲ�����Ҫ����ɸѡ�Ƿ����ظ�ֵ
			Map<Integer, Sdetail> map = new HashMap();
			List<Sdetail> resultlist = new ArrayList<Sdetail>();
			for (int i = 0; i < sdetails.size(); i++) {
				sdetails.get(i).setDetailid(id);
				if (map.containsKey(sdetails.get(i).getDrugid())) {// ��Ϊ��
					sdetails.get(i).setNownum(sdetails.get(i).getNownum()
							+ Float.valueOf(map.get(sdetails.get(i).getDrugid()).getNownum()));
					map.put(sdetails.get(i).getDrugid(), sdetails.get(i));
				} else {// Ϊ��
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
		// ��ȡ������ҩƷ
		List<Sdetail> SdetailList = sdetailService.queryById(id);
		//��ȡ֮ǰ�Ŀ��
		List<Inventory> InventoryList = inventoryService.queryAll();
		//�޸Ŀ��
		for(int i=0;i<SdetailList.size();i++){//��
			for(int j=0;j<InventoryList.size();j++){//��
				 if(SdetailList.get(i).getDrugid()==InventoryList.get(j).getDrugid()){
					 //��Ҫ���
					Integer newSum =  (int) (InventoryList.get(j).getInventorySum()+ SdetailList.get(i).getNownum());
					 //��ȡ֮ǰ�Ŀ��
					 inventoryService.updateSumByDrugId(InventoryList.get(i).getId(),newSum);
				 }
			}
		}
		return ajaxResult;
	}
   
	/**
	 * ɾ��
	 */
	public Object deleteStorageById(String id) {
		// TODO Auto-generated method stub
		AjaxResult ajaxResult = new AjaxResult();
		try {
			//��ɾ����ϸ��
			sdetailService.deleteSdetailById(id);
			//Ȼ����ɾ������
			storageDao.deleteStorageById(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		
		
		return ajaxResult;
	}
    /**
     * ����ɾ��
     */
	public Object deleteStorageBouth(String [] id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap();
			map.put("detailids", id);
			//��ɾ����ϸ��
			sdetailService.deleteSdetaileBouth(map);
			//��ɾ������
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
