package com.drugmanager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.drugmanager.bean.User;

/**
 * 登陆拦截器
 * @author Hyh
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
    
	/**
	 * 在完成视图渲染之后,执行此方法
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}
    /**
     * 控制器执行完毕之后执行的逻辑操作
     */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
    /**
     * 控制器执行之前完成业务逻辑
     * 方法的返回值决定逻辑是否继续执行,
     * true:表示继续执行
     * false:表示不再继续执行
     */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		String path = session.getServletContext().getContextPath();
		if(loginUser==null){
			response.sendRedirect(path + "/login");
			return false;
		}else{
			return true;
		}
	}

}
