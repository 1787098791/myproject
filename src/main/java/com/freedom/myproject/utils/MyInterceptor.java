package com.freedom.myproject.utils;

import com.freedom.myproject.dto.InsideStaffDto;
import com.freedom.myproject.dto.PlatCustomersInfoDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        //System.out.println(uri);
        //1.如果是登录或者注册页面的请求则不拦截返回true(controller中登录、注册对应的请求路径也不能拦截)
        if (uri.equals("/staffLoginInside")||uri.equals("/resetCustomersPassword")||uri.equals("/entpriselogin")||uri.equals("/checkLoginName")||uri.equals("/entpriseregist")||uri.equals("/entregist.html")||uri.equals("/entlogin.html")||uri.equals("/forgetpassword.html")||uri.equals("/rslogin.html")||uri.equals("/rsmain.html")){
            return true;
        }
        if (uri.contains("Inside")){
            InsideStaffDto insideStaff = (InsideStaffDto) httpServletRequest.getSession().getAttribute("insideStaff");
            if (insideStaff!=null){
                return true;
            }else{
                httpServletResponse.sendRedirect("rsmain.html");
                return false;
            }
        }
        PlatCustomersInfoDto platCustomersInfoDto = (PlatCustomersInfoDto) httpServletRequest.getSession().getAttribute("entpriseCustomers");
        if (platCustomersInfoDto!=null){
            return true;
        }else{
        httpServletResponse.sendRedirect("rsmain.html");
        return false;
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
