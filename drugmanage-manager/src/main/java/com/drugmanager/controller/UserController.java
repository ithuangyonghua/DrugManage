package com.drugmanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.Page;
import com.drugmanager.bean.Role;
import com.drugmanager.bean.User;
import com.drugmanager.service.RoleService;
import com.drugmanager.service.UserService;
import com.drugmanager.utils.DateUtils;

/**
 * 用户Controller
 * 
 * @author Hyh
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	/**
	 * 跳转到用户首页
	 * 
	 * @return
	 */
	@RequestMapping("/index1")
	public String index1(@RequestParam(defaultValue = "1", required = false) Integer pageno,
			@RequestParam(defaultValue = "2", required = false) Integer pagesize, Model model) {
		// 实现分页查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageno - 1) * pagesize);
		map.put("size", pagesize);
		List<User> userData = userService.pageQueryData(map);
		model.addAttribute("userList", userData);
		// 当前页码
		model.addAttribute("pageno", pageno);
		// 最大页面(总页码)
		int totalsize = userService.pageQueryCount(map);
		int totalno = 0;
		if (totalsize % pagesize == 0) {
			totalno = totalsize / pagesize;
		} else {
			totalno = totalsize / pagesize + 1;
		}
		// 总页码
		model.addAttribute("totalno", totalno);
		return "user/index1";
	}

	/**
	 * 修改后: 跳转到用户页面
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "user/index";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping("add")
	public String add() {
		return "user/add";
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		User user = userService.queryUserById(id);
		model.addAttribute("userInfo", user);
		return "user/edit";
	}

	/**
	 * 查询功能
	 * 
	 * @param pageno
	 * @param pagesize
	 * @param queryText
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize, String queryText) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno - 1) * pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			List<User> userData = userService.pageQueryData(map);
			int totalsize = userService.pageQueryCount(map);
			int totalno = 0;
			if (totalsize % pagesize == 0) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			Page<User> page = new Page();
			page.setDatas(userData);
			page.setPageno(pageno);
			page.setTotalno(totalno);
			page.setTotalsize(totalsize);
			ajaxResult.setSuccess(true);
			ajaxResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	/**
	 * 插入
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insert")
	public Object insertUser(User user) {
		AjaxResult ajaxResult = new AjaxResult();

		try {
			user.setPassword("123");
			user.setCreatetime(DateUtils.formatDate(new Date()));
			;
			userService.insertUser(user);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object updateUser(User user) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			userService.updateUser(user);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object deleteUser(String id) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			userService.deleteUser(id);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}

	/**
	 * 批量删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteBoath")
	public Object deleteBoathUser(Integer[] ids) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userids", ids);
			userService.deleteBoathUser(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
    /**
     * 分配角色页面
     * @param id
     * @param model
     * @return
     */
	@RequestMapping("assignRole")
	public String assignRole(String id, Model model) {
		//
		User user = userService.queryUserById(id);
		model.addAttribute("userInfo", user);
		List<Role> all = roleService.queryAll();//所有的角色
//		model.addAttribute("roleInfo", all);

		//已分配的集合
		List<Role> assignList =new ArrayList<Role>();
		//未分配的集合
		List<Role> unassignList =new ArrayList<Role>();
		
		List<Integer> rids = userService.assignRoleById(id);
		for(Role roleall:all){
			if(rids.contains(roleall.getId())){//已分配
				assignList.add(roleall);
			}else{//未分配
				unassignList.add(roleall);
			}
		}
		model.addAttribute("assignList", assignList);
		model.addAttribute("unassignList", unassignList);
		return "user/assignRole";
	}
	
	/**
	 * 分配角色
	 * @param id
	 * @param unassignnames
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign(Integer id,String [] unassignnames) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", id);
			map.put("roleids", unassignnames);
			userService.insertAssign(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	/**
	 * 取消分配角色
	 * @param id
	 * @param assignnames
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUnAssign")
	public Object doUnAssign(Integer id,String [] assignnames) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", id);
			map.put("roleids", assignnames);
			userService.deleteAssign(map);
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
}
