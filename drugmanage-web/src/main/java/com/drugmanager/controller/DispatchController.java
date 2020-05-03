package com.drugmanager.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugmanager.bean.AjaxResult;
import com.drugmanager.bean.Permission;
import com.drugmanager.bean.User;
import com.drugmanager.service.PermissionService;
import com.drugmanager.service.UserService;

/**
 * ��½ && �˳�
 * 
 * @author Hyh
 *o
 */
@Controller
public class DispatchController {
	@Autowired
	UserService userService;
	public static final Logger logger = LoggerFactory.getLogger(DispatchController.class);
	@Autowired
	PermissionService permissionService;
//	Logger Logger = LoggerFactory.getLogger(getClass());
	/**
	 * ��ת������ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	/**
	 * ��ת����½
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * ��ת����ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String main() {
//		return "main";
		return "redirect:drug/index";
	}

	/**
	 * ִ�е�½
	 * 
	 * @return
	 */
	@RequestMapping("/dologin")
	public String doLogin(User user, Model model) {
		// (1)��ȡ������
		// (1-1) HttpServletRequest
		// (1-2) �ڷ��������б������ӱ���Ӧ�Ĳ���,������ͬ(������ǰ�˵�name����ֵ�뷽������һ��)
		// (1-3) �������ݷ�װΪʵ�������(������ǰ�˵�name����ֵ��ʵ������һ��)

		// (2)��ѯ�û���Ϣ
		User dbuser = userService.queryForLogin(user);
		// (3)�ж��û��Ƿ����
		if (dbuser != null) {
			// ��½�ɹ�,��ת����ҳ��
			return "main";// Ĭ����ת���ķ�ʽ
		} else {
			// ��½ʧ��,��ת�ص�½ҳ��,����ʾ������Ϣ
			String message = "��½���˺Ż��������,����������";
			model.addAttribute("msg", message);
			return "redirect:login";
		}

	}

	/**
	 * Ajax ��½����
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAjaxLogin")
	public Object doAjaxLogin(User user, HttpSession session) {
		AjaxResult ajaxResult = new AjaxResult();
		User dbuser = userService.queryForLogin(user);
		if (dbuser != null) {
			// ��½�ɹ�,��ת����ҳ��
			ajaxResult.setSuccess(true);
			// ��װ�û���Ȩ�޹�ϵ
//			Logger.debug("ddddddd");
			List<Permission> permissions = permissionService.queryPermissionByUser(dbuser);// ��ѯ��½�û����е����
			Set<String> autoUrlSet = new HashSet<String>();
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();//
			Permission root = null;//
			for (Permission permission : permissions) {
				if (!StringUtils.isEmpty(permission.getUrl()) && !"".equals(permission.getUrl())) {
					autoUrlSet.add(session.getServletContext().getContextPath() + permission.getUrl());
				}
				permissionMap.put(permission.getId(), permission);
			}
			for (Permission permission : permissions) {
				Permission children = permission;
				if (permission.getPid() == 0) {
					root = children;
				} else {
					Permission parent = permissionMap.get(children.getPid());
					parent.getChildren().add(children);
				}
			}
			session.setAttribute("rootPermission", root);
			session.setAttribute("autoUrlSet", autoUrlSet);
			session.setAttribute("loginUser", dbuser);
			return ajaxResult;// Ĭ����ת���ķ�ʽ
		} else {
			// ��½ʧ��,��ת�ص�½ҳ��,����ʾ������Ϣ
			// String message = "��½���˺Ż��������,����������";
			// model.addAttribute("msg", message);
			ajaxResult.setSuccess(false);
			return ajaxResult;
		}
	}

	/**
	 * �˳�ϵͳ
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String Logout(HttpSession session) {
		// session.removeAttribute("loginUser");
		session.invalidate();
		return "redirect:login";// �ض��򵽵�¼ҳ����������·��Ҳ����login
	}

}
