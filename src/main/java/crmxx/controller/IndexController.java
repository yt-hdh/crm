package crmxx.controller;

import base.BaseController;
import crmxx.service.UserService;
import crmxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
       request.setAttribute("ctx",request.getContextPath());
       return "index";
    }
    @RequestMapping("/main")
    public String main(HttpServletRequest request){
       request.setAttribute("ctx",request.getContextPath());
        Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
        request.setAttribute("user",userService.queryById(userId));
       return "main";
    }
}
