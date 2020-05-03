package com.drugmanager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.Page;
import com.drugmanager.bean.Role;
import com.drugmanager.bean.Supplier;
import com.drugmanager.service.SupplierService;
import com.drugmanager.utils.DateUtils;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	SupplierService supplierService;

	@RequestMapping("/index")
	public String index() {
		return "supplier/index";
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
			List<Supplier> supplierlist = supplierService.pageQuery(map);
			int totalsize = supplierService.pageQueryCount(map);// ×ÜÌõÊý
			int totalno = totalsize / pagesize;
			if (totalsize % pagesize != 0) {
				totalno += 1;
			}
			ajaxResult.setSuccess(true);
			Page<Supplier> page = new Page();
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
	
	@RequestMapping("/insertSupplier")
	public String add() {
		return "supplier/add";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insertSupplier(Supplier supplier) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			supplierService.insertSupplier(supplier);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		Supplier supplier = supplierService.querySupplierById(id);
		model.addAttribute("supplierInfo", supplier);
		return "supplier/edit";
	}
	@ResponseBody
	@RequestMapping("/update")
	public Object update(Supplier supplier) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			supplierService.updateSupplier(supplier);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping("/deleteSupplierById")
	public Object deleteSupplierById(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			supplierService.deleteSupplierById(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteRoleBouth")
	public Object deleteSupplierBouth(String [] id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap();
			map.put("supplierids", id);
			supplierService.deleteSupplierBouth(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
}
