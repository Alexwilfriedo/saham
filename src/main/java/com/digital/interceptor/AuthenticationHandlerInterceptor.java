package com.digital.interceptor;

import com.digital.model.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexwilfriedo
 */
@Component
public class AuthenticationHandlerInterceptor implements HandlerInterceptor {

    private static List<String> unmatchedPath = new ArrayList<String>() {{
        add("/login");
        add("/creer_compte");
        add("/activation");
        add("/");
        add("/user/authenticate");
    }};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final boolean isUserConnected = request.getSession().getAttribute("username") != null &&
                !request.getSession().getAttribute("username").toString().isEmpty();
        if (!isUserConnected) {
            response.sendRedirect("/login");
            //response.sendRedirect("/disconnect");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
