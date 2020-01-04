package crmxx.interceptors;

import crmxx.service.UserService;
import crmxx.utils.AssertUtils;
import crmxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
@Component
public class LoginIntertor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        获取登录用户的ID
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtils.isNotLogin(null==userId||userId==0||null==userService.queryById(userId),"用户未登录！");
        return true;
    }
}
