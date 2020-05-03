package com.drugmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.DrugCategory;
import com.drugmanager.bean.Page;
import com.drugmanager.bean.Storage;
import com.drugmanager.bean.Supplier;
import com.drugmanager.service.DrugCategoryService;
import com.drugmanager.service.SdetailService;
import com.drugmanager.service.StorageService;
import com.drugmanager.service.SupplierService;
import com.drugmanager.vo.SdetailVo;
import com.drugmanager.vo.StorageVo;

@Controller
@RequestMapping("/storage")
public class StorageController {
	@Autowired
	SupplierService supplierService;

	@Autowired
	DrugCategoryService drugCategoryService;

	@Autowired
	StorageService storageService;
	
	@Autowired
	SdetailService sdetailService;

	@RequestMapping("/index")
	public String showindex() {
		return "storage/index";
	}

	@RequestMapping("/add")
	public String index(Model model) {
		// ��Ӧ��
		List<Supplier> supplierInfo = supplierService.queryAll();
		model.addAttribute("supplierInfo", supplierInfo);

		// ���
		List<DrugCategory> drugCategoryAll = drugCategoryService.queryAll();
		model.addAttribute("drugCategoryAll", drugCategoryAll);
		return "storage/add";
	}

	// ���湦��
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Object saveStorage(@RequestBody StorageVo storageVo, HttpServletRequest request) {
		return storageService.saveStorage(storageVo, request);
	}
 
	//��ѯ
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize, String searchText) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno - 1) * pagesize);
			map.put("size", pagesize);
			map.put("searchText", searchText);
			List<Storage> rolelist = storageService.pageQuery(map);
			int totalsize = storageService.pageQueryCount(map);// ������
			int totalno = totalsize / pagesize;
			if (totalsize % pagesize != 0) {
				totalno += 1;
			}
			ajaxResult.setSuccess(true);
			Page<Storage> page = new Page();
			page.setDatas(rolelist);
			page.setTotalsize(totalsize);
			page.setPageno(pageno);
			page.setTotalno(totalno);
			ajaxResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	// ���
	@ResponseBody
	@RequestMapping("/updateStatusId")
	public Object updateStatusId(String id) {
		return storageService.updateStatusId(id);
	}

	//ɾ��
	@ResponseBody
	@RequestMapping("/deleteStorageById")
	public Object deleteStorageById(String id) {
		return storageService.deleteStorageById(id);
	}
	
	//����ɾ��
	@ResponseBody
	@RequestMapping("/deleteStorageBouth")
	public Object deleteStorageBouth(String [] id) {
		return storageService.deleteStorageBouth(id);
	}
	
	//��ϸ
	@RequestMapping("/detail")
	public String detail(String id,Model model){
		List<SdetailVo> querySdetailVo = sdetailService.querySdetailVo(id);
		model.addAttribute("sdetailVoInfo", querySdetailVo);
	    return "storage/detail";	
	}
	
	//����
	@RequestMapping("/accounts")
	public String accounts(String id,Model model){
		//��ѯ
		List<SdetailVo> querySdetailVo = sdetailService.querySdetailVo(id);
		model.addAttribute("sdetailVoInfo", querySdetailVo);
	    return "storage/accounts";	
	}
	
	//�޸�֧��״̬
	@ResponseBody
	@RequestMapping("/updateMoneryStatusId")
	public Object updateMoneryStatusId(String id,Integer monery) {//һ���Ƕ�����  һ���ǽ��
		return storageService.updateMoneryStatusId(id,monery);
	}
}
