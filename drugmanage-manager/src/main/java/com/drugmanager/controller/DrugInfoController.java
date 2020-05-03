package com.drugmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.DrugCategory;
import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Inventory;
import com.drugmanager.bean.Page;
import com.drugmanager.service.DrugCategoryService;
import com.drugmanager.service.DrugInfoService;
import com.drugmanager.service.InventoryService;
import com.drugmanager.vo.DrugInfoVo;

@Controller
@RequestMapping("/drug")
public class DrugInfoController {
	@Autowired
	DrugInfoService drugInfoService;
	
	@Autowired
	DrugCategoryService drugCategoryService;
	
	@Autowired
	InventoryService inventoryService;
     /**
      * 跳转到登陆页面
      * @return
      */
	 @RequestMapping("/index")
	 public String index(Model model){
		 List<DrugCategory> drugCategoryAll = drugCategoryService.queryAll();
		 model.addAttribute("drugCategoryAll", drugCategoryAll);
		 return "druginfo/index";
	 }
	   @ResponseBody
		@RequestMapping("/pageQuery")
		public Object pageQuery(Integer pageno, Integer pagesize, String queryID,String queryName,Integer queryCategory) {
			AjaxResult ajaxResult = new AjaxResult();
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("start", (pageno - 1) * pagesize);
				map.put("size", pagesize);
				map.put("queryID", queryID);
				map.put("queryName", queryName);
				map.put("queryCategory", queryCategory);
				List<DrugInfoVo> rolelist = drugInfoService.pageQuery(map);
				int totalsize = drugInfoService.pageQueryCount(map);// 总条数
				int totalno = totalsize / pagesize;
				if (totalsize % pagesize != 0) {
					totalno += 1;
				}
				ajaxResult.setSuccess(true);
				Page<DrugInfoVo> page = new Page();
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
	 @RequestMapping("/insertDrugCategory")
		public String add(Model model) {
			 List<DrugCategory> drugCategoryAll = drugCategoryService.queryAll();
			 model.addAttribute("drugCategoryAll", drugCategoryAll);
			return "druginfo/add";
		}
		
		@ResponseBody
		@RequestMapping("/insert")
		public Object insertDrugInfo(DrugInfo drugInfo) {
			AjaxResult ajaxResult = new AjaxResult();
			try {
				
				Integer insertDrugInfo = drugInfoService.insertDrugInfo(drugInfo);
				Inventory inventory = new Inventory();
				inventory.setDrugid(drugInfo.getDrugid());
				inventory.setInventorySum(0);
				inventoryService.insertInventory(inventory);
				
				ajaxResult.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setSuccess(false);
			}
			return ajaxResult;
		}
		@RequestMapping("/edit")
		public String edit(String id, Model model) {
			 List<DrugCategory> drugCategoryAll = drugCategoryService.queryAll();
			 model.addAttribute("drugCategoryAll", drugCategoryAll);
			 DrugInfo drugInfo = drugInfoService.queryDrugById(id);
			 model.addAttribute("drugInfo", drugInfo);
			 return "druginfo/edit";
		}
		@ResponseBody
		@RequestMapping("/update")
		public Object update(DrugInfo DrugInfo) {
			AjaxResult ajaxResult = new AjaxResult();
			try {
				drugInfoService.updateDrugInfo(DrugInfo);
				ajaxResult.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setSuccess(false);
			}
			return ajaxResult;
		}
		@ResponseBody
		@RequestMapping("/deleteDrugInfoById")
		public Object deleteDrugInfoById(String id) {
			AjaxResult ajaxResult = new AjaxResult();
			try {
				inventoryService.deleteInventoryByDrugId(id);
				drugInfoService.deleteDrugInfoById(id);
				
				ajaxResult.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setSuccess(false);
			}
			return ajaxResult;
		}
		
		
		@ResponseBody
		@RequestMapping("/deleteDrugInfoBouth")
		public Object deleteDrugInfoBouth(String [] id) {
			AjaxResult ajaxResult = new AjaxResult();
			try {
				Map<String,Object> map = new HashMap();
				map.put("drugids", id);
				inventoryService.deleteInventoryBouthDrugId(map);
				drugInfoService.deleteDrugInfoBouth(map);
				ajaxResult.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setSuccess(false);
			}
			return ajaxResult;
		}
		
		@ResponseBody
		@RequestMapping("/queryById/{id}")
		public Object queryById(@PathVariable("id") Integer id) {
			AjaxResult ajaxResult = new AjaxResult();
			try {
				List<DrugInfo> queryById = drugInfoService.queryById(id);
				ajaxResult.setData(queryById);
				ajaxResult.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult.setSuccess(false);
			}
			return ajaxResult;
		}
}
