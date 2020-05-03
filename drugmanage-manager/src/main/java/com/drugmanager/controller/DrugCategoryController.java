package com.drugmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.DrugCategory;
import com.drugmanager.bean.Page;
import com.drugmanager.bean.Supplier;
import com.drugmanager.service.DrugCategoryService;

@Controller
@RequestMapping("/drugcategory")
public class DrugCategoryController {
	
	@Autowired
	DrugCategoryService  drugCategoryService;
	
	@RequestMapping("/index")
	public String index() {
		return "drugcategory/index";
	}
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize, String searchText) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno - 1) * pagesize);
			map.put("size", pagesize);
			map.put("searchText", searchText);
			List<DrugCategory> rolelist = drugCategoryService.pageQuery(map);
			int totalsize = drugCategoryService.pageQueryCount(map);// ×ÜÌõÊý
			int totalno = totalsize / pagesize;
			if (totalsize % pagesize != 0) {
				totalno += 1;
			}
			ajaxResult.setSuccess(true);
			Page<DrugCategory> page = new Page();
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
	@RequestMapping("/insertDrugcategory")
	public String add() {
		return "drugcategory/add";
	}
	@ResponseBody
	@RequestMapping("/insert")
	public Object insertDrugcategory(DrugCategory drugCategory) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			drugCategoryService.insertDrugCategory(drugCategory);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		DrugCategory drugCategory = drugCategoryService.querySupplierById(id);
		model.addAttribute("drugCategoryInfo", drugCategory);
		return "drugcategory/edit";
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update(DrugCategory drugCategory) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			drugCategoryService.updateDrugCategory(drugCategory);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping("/deleteDrugCategoryById")
	public Object deleteDrugCategoryById(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			drugCategoryService.deleteDrugCategoryById(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteDrugCategoryBouth")
	public Object deleteDrugCategoryBouth(String [] id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap();
			map.put("drugCategoryids", id);
			drugCategoryService.deleteDrugCategoryBouth(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
}
