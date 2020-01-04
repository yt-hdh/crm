package crmxx.controller;

import base.BaseController;
import crmxx.model.ResultInfo;
import crmxx.model.UserModel;
import crmxx.po.User;
import crmxx.query.UserQuery;
import crmxx.service.UserService;
import crmxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserContrller extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "user";
    }

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    @RequestMapping("/queryUserById")
    @ResponseBody
    public User queryUserById(Integer userId){
        return userService.queryById(userId);
    }

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        UserModel userModel=userService.login(userName,userPwd);
        return success(userModel);
    }

    /**
     * 用户密码更新
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
   public ResultInfo updatePassword(HttpServletRequest request,String oldPassword,String newPassword,String confirmPassword){
//       通过LoginUserUtils工具获取用户ID
           Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
//       调用service层用户更新方法
           userService.updateUserPassword(userId,oldPassword,newPassword,confirmPassword);
       return success("更新成功！");
   }

    /**
     * 用户退出
     * @return
     */
    @RequestMapping("/exit")
    @ResponseBody
    public ResultInfo logout(){
        return success("退出成功");
    }

    /**
     * 用户信息管理--信息展示
     * @param page
     * @param rows
     * @param userQuery
     * @return
     */

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> queryUserByParams(@RequestParam(defaultValue ="1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer rows,
                                                UserQuery userQuery){
        userQuery.setPageNum(page);
        userQuery.setPageSize(rows);
        return userService.queryParamBydata(userQuery);
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(User user){
        userService.saveUser(user);
        return success("添加成功！");
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(User user){
        userService.updateUser(user);
        return success("更新成功！");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer userId){
        userService.updateUser(userId);
        return success("删除成功！");
    }


}
