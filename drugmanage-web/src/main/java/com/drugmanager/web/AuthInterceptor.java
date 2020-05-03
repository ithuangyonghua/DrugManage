package com.drugmanager.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.drugmanager.bean.Permission;
import com.drugmanager.service.PermissionService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	PermissionService permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取用户的请求路径
		String url = request.getRequestURI();
		// 登陆用户拥有的权限
		Set<String> autoUrlSet = (Set<String>) request.getSession().getAttribute("autoUrlSet");
		String path = request.getSession().getServletContext().getContextPath();
		// 判断路径是否需要进行权限验证
		// 查询所有需要验证的路径集合--即就是需要验证的路径
		List<Permission> permissions = permissionService.queryAll();
		// 为啥用set,因为有些路径可能是重复的--即把需要验证的路径放入Set
		Set<String> uriSet = new HashSet<String>();
		for (Permission permission : permissions) {
			if (!StringUtils.isEmpty(permission.getUrl()) && !"".equals(permission.getUrl())) {
				uriSet.add(path + permission.getUrl());
			}
		}
		if (uriSet.contains(url)) {
			// 权限验证
			// 用户是否具有相对应的权限
			if (autoUrlSet.contains(url)) {
				return true;
			} else {
				response.sendRedirect(path + "/error");
				return false;
			}
		} else {
			return true;
		}
	}

}
