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
import com.drugmanager.bean.DrugInfo;
import com.drugmanager.bean.Inventory;
import com.drugmanager.bean.Page;
import com.drugmanager.bean.Permission;
import com.drugmanager.bean.Supplier;
import com.drugmanager.service.DrugInfoService;
import com.drugmanager.service.InventoryService;
import com.drugmanager.vo.InventoryVo;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private  DrugInfoService drugInfoService;
	
	/**
	 * 跳转到主页面,
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "inventory/index";
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize, String queryID, String queryName) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno - 1) * pagesize);
			map.put("size", pagesize);
			map.put("queryID", queryID);
			map.put("queryName", queryName);
			List<InventoryVo> supplierlist = inventoryService.pageQuery(map);
			int totalsize = inventoryService.pageQueryCount(map);// 总条数
			int totalno = totalsize / pagesize;
			if (totalsize % pagesize != 0) {
				totalno += 1;
			}
			ajaxResult.setSuccess(true);
			Page<InventoryVo> page = new Page();
			page.setDatas(supplierlist);
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
//	InventoryController
	@RequestMapping("/insertInventory")
	public String add(Model model) {
		List<DrugInfo> DrugInfo = drugInfoService.queryAll();
		model.addAttribute("drugInfo", DrugInfo);
		return "inventory/add";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insertInventory(Inventory inventory) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			inventoryService.insertInventory(inventory);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@RequestMapping("/edit")
	public String edit(String id,Model model) {
		Inventory inventoryInfo = inventoryService.queryInventoryById(id);
		model.addAttribute("inventoryInfo", inventoryInfo);
		return "inventory/edit";
	}
	@ResponseBody
	@RequestMapping("/update")
	public Object update(Inventory inventory) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			inventoryService.updateInventory(inventory);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping("/deleteInventoryById")
	public Object deleteInventoryById(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			inventoryService.deleteInventoryById(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteInventoryBouth")
	public Object deleteInventoryBouth(String [] id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap();
			map.put("inventoryids", id);
			inventoryService.deleteInventoryBouth(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
}
