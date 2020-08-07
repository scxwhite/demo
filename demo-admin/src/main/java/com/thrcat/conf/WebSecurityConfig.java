package com.thrcat.conf;


import com.thrcat.Constants;
import com.thrcat.annotion.UncheckLogin;
import com.thrcat.controller.BaseController;
import com.thrcat.exceptions.UnLoginException;
import com.thrcat.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:
 *
 * @author scx
 * @create 2020/03/24
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {


    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        InterceptorRegistration addRegistry = interceptorRegistry.addInterceptor(getSecurityInterceptor());
        addRegistry.excludePathPatterns("/error").excludePathPatterns("/login/**");
    }

    private static class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            BaseController.set(request);
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
            HandlerMethod method = (HandlerMethod) handler;
            UncheckLogin methodAnnotation = method.getMethodAnnotation(UncheckLogin.class);
            if (methodAnnotation != null) {
                return true;
            }
            UncheckLogin declaredAnnotation = method.getBeanType().getDeclaredAnnotation(UncheckLogin.class);
            if (declaredAnnotation != null) {
                return true;
            }
            String heraToken = JwtUtils.getValFromCookies(Constants.TOKEN_NAME, request);
            if (heraToken != null && JwtUtils.verifyToken(heraToken)) {
                return true;
            }
            throw new UnLoginException("未登录");
        }


        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            BaseController.remove();
            super.postHandle(request, response, handler, modelAndView);
        }
    }

}
