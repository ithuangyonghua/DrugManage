package com.drugmanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.Permission;
import com.drugmanager.service.PermissionService;

@RequestMapping("/permission")
@Controller
public class PermissionController {

	@Autowired
	PermissionService permissionService;

	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}

	@ResponseBody
	@RequestMapping("/loadAssignData")
	public Object loadAssignData(Integer rid) {
		//封装角色与权限的关系
		List<Permission> permissions = new ArrayList<Permission>();//
		List<Permission> ps = permissionService.queryAll();// 获取所有的节点数据

		// 获取当前角色已经分配的许可信息
		List<Integer> permissionids = permissionService.queryPermissionidByRid(rid);

		Map<Integer, Permission> map = new HashMap<Integer, Permission>();
		for (Permission p : ps) {
			if (permissionids.contains(p.getId())) {
				p.setChecked(true);
			} else {
				p.setChecked(false);
			}
			map.put(p.getId(), p);
		}
		for (Permission p : ps) {
			Permission children = p;// 子节点
			if (p.getPid() == 0) {// 如果节点的pid为0则说明为根节点
				permissions.add(p);
			} else {// 否则不是根节点,则说明肯定是有父节点的
				Permission parent = map.get(children.getPid());
				parent.getChildren().add(children);
			}
		}
		return permissions;
	}

	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		/*
		 * Permission root = new Permission(); root.setId(0);
		 * getChildrens(root); return root.getChildren();
		 */

		// List<Permission> permissions= new ArrayList<Permission>();
		// List<Permission> ps= permissionService.queryAll();//获取所有的节点数据
		// 线性遍历
		/*
		 * for(Permission p : ps){ //遍历 //子节点 Permission children = p;
		 * if(p.getPid()==0){//如果节点的pid为0则说明为根节点 permissions.add(p); }else{
		 * for(Permission innerPermission:ps){
		 * if(children.getPid().equals(innerPermission.getId())){ //父节点
		 * Permission parent = innerPermission;
		 * parent.getChildren().add(children); break; } } } }
		 */
		List<Permission> permissions = new ArrayList<Permission>();//
		List<Permission> ps = permissionService.queryAll();// 获取所有的节点数据
		Map<Integer, Permission> map = new HashMap<Integer, Permission>();
		for (Permission p : ps) {
			map.put(p.getId(), p);
		}
		for (Permission p : ps) {
			Permission children = p;// 子节点
			if (p.getPid() == 0) {// 如果节点的pid为0则说明为根节点
				permissions.add(p);
			} else {// 否则不是根节点,则说明肯定是有父节点的
				Permission parent = map.get(children.getPid());
				parent.getChildren().add(children);
			}
		}
		return permissions;
	}

	/**
	 * 递归实现树状菜单,但是效率比较低
	 * 
	 * @param parent
	 */
	public void getChildrens(Permission parent) {
		List<Permission> queryChildrens = permissionService.queryChildren(parent.getId());
		for (Permission queryChildren : queryChildrens) {
			getChildrens(queryChildren);
		}
		parent.setChildren(queryChildrens);
	}

	// 跳转到添加页面
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}

	/**
	 * 插入权限信息
	 * 
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insertPermission(Permission permission) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			permissionService.insertPermission(permission);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	/**
	 * 修改
	 * 
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Integer id, Model model) {
		Permission permission = permissionService.queryPermissionById(id);
		model.addAttribute("permissionInfo", permission);
		return "permission/edit";
	}

	@ResponseBody
	@RequestMapping("/update")
	public Object updatePermission(Permission permission) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			permissionService.updatePermission(permission);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	/**
	 * 删除
	 * 
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object deletePermission(Permission permission) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			permissionService.deletePermission(permission);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

}
