/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.service.BaseService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.utils.UserAgentUtils;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 手机端视图拦截器
 * @author tlkzzz
 * @version 2014-9-1
 */
public class MobileInterceptor extends BaseService implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		String method = request.getRequestURI();
		method = method.substring(method.lastIndexOf("/")+1);
		if(!"QRScan".equals(method)&&!"getQR".equals(method)){
			if(UserUtils.getUser()==null||StringUtils.isBlank(UserUtils.getUser().getId())){
				response.sendRedirect(request.getContextPath()+Global.getShopPath()+"/QRScan");
				return false;
			}else {
				UserUtils.updateLoginInfo();
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null){
			// 如果是手机或平板访问的话，则跳转到手机视图页面。
			if(UserAgentUtils.isMobileOrTablet(request) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")){
//				modelAndView.setViewName("mobile/" + modelAndView.getViewName());
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
	}

}
