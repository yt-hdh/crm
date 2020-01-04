package crmxx.service;

import base.BaseService;
import base.constants.CrmContants;
import crmxx.db.dao.UserMapper;
import crmxx.db.dao.UserRoleMapper;
import crmxx.model.UserModel;
import crmxx.po.User;
import crmxx.po.UserRole;
import crmxx.utils.AssertUtils;
import crmxx.utils.Md5Util;
import crmxx.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@SuppressWarnings("all")
public class UserService extends BaseService<User,Integer> {

    //    手机号正则校验

    private Pattern p  = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * controller--用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel login(String userName,String userPwd){
        /**
         * 1、参数校验
         * 2、查询用户记录  根据用户名
         *       存在
         *       判断用户是否注销  is_valid=0，未注销 is_valid=1
         *       判断密码
         *
         */
//        参数合法性校验
        checkLoginParams(userName,userPwd);
//        根据用户名称查询密码
        User user=userMapper.queryUserByUserName(userName);
//        判断是否查到用户
        AssertUtils.isTrue(user==null,"该用户不存在！");
//       判断用户是否有效
        AssertUtils.isTrue(user.getIsValid()==0,"该用户已注销！");
//        判断用户密码是否正确
        AssertUtils.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"用户密码错误");
//        返回用户信息数据
        return returnUserModel(user);

    }


    /**
     * Controller--用户密码更新
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    public void updateUserPassword(Integer userId,String oldPassword,String newPassword,String confirmPassword){
        /**
         * 1、参数校验
         * （1、参数为空校验
         * （2、参数一致性校验
         * 2、根据用户id查询用户登录密码判断用户登录密码是否正确
         * 3、使用MD5UTILS将密码加密
         * 4、执行更新操作并返回更新结果
         */
//        参数校验
        checkParams(oldPassword,newPassword,confirmPassword);
//        查询用户获取去用户密码
        User user=userMapper.queryById(userId);
//
        AssertUtils.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))),"原密码不正确！");
//        对新设置的密码加密
        user.setUserPwd(Md5Util.encode(newPassword));
//        执行更新并判断是否更新成功
        AssertUtils.isTrue(userMapper.update(user)==0,"密码更新失败！");
    }

    /**
     * controller--用户信息管理   用户添加
     * @param user
     */
    public void saveUser(User user){
        AssertUtils.isTrue(StringUtils.isBlank(user.getUserName()),"请输入用户名称！");
        AssertUtils.isTrue(null!=userMapper.queryUserByUserName(user.getUserName()),"用户名已存在！");
        AssertUtils.isTrue(!(p.matcher(user.getPhone()).matches()),"手机号码格式不正确！");
        user.setUserPwd(Md5Util.encode("123456"));
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setIsValid(1);
        AssertUtils.isTrue(save(user)==0,"用户添加失败！");
//        关联角色
        relationUserRole(user.getId(),user.getUserRoles());
    }

    private void relationUserRole(Integer userId, List<Integer> userRoles) {
        int count=userRoleMapper.countUserRoleByUserId(userId);
        if (count>0) {
            AssertUtils.isTrue(userRoleMapper.deleteByUserId(userId)!=count,CrmContants.FAILED_MSG);;
        }
        if(userRoles.size()>0){
            List<UserRole> roleList= new ArrayList<>();
            userRoles.forEach(roleId->{
                UserRole userRole=new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                roleList.add(userRole);
            });
            AssertUtils.isTrue(userRoleMapper.saveBatch(roleList)!=userRoles.size(), CrmContants.FAILED_MSG);
        }
    }

    /**
     * controller--用户信息管理   用户更新
     * @param user
     */
    public void updateUser(User user){
        AssertUtils.isTrue(StringUtils.isBlank(user.getUserName()),"请输入用户名称！");
        AssertUtils.isTrue(!(p.matcher(user.getPhone()).matches()),"手机号码格式不正确！");
        User tempUser=userMapper.queryUserByUserName(user.getUserName());
        AssertUtils.isTrue(null!=tempUser && !(tempUser.getId().equals(user.getId())),"用户名已存在！");
        user.setUpdateDate(new Date());
        AssertUtils.isTrue(update(user)==0,"用户更新失败！");
        relationUserRole(user.getId(),user.getUserRoles());
    }

    /**
     * controller--用户信息管理   用户删除
     * @param userId
     */
    public void updateUser(Integer userId){
        AssertUtils.isTrue(userId==0||null==queryById(userId),"用户记录不存在！");
        int count=userRoleMapper.countUserRoleByUserId(userId);
        if(count>0){
            AssertUtils.isTrue(userRoleMapper.deleteByUserId(userId)!=count,CrmContants.FAILED_MSG);
        }
        AssertUtils.isTrue(delete(userId)==0,"用户删除失败！");

    }
    /**
     * login--返回用户数据
     * @param user
     * @return
     */
    private UserModel returnUserModel(User user) {
        UserModel userModel=new UserModel();
        userModel.setIsStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setTrueName(user.getTrueName());
        userModel.setUserName(user.getUserName());
        return userModel;
    }

    /**
     * login--校验登录参数方法
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        AssertUtils.isTrue(StringUtils.isBlank(userName),"用户名不能为空！");
        AssertUtils.isTrue(StringUtils.isBlank(userPwd),"用户明码不能为空！");
    }

    /**
     * update--校验用户更新数据
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkParams(String oldPassword, String newPassword, String confirmPassword) {
        AssertUtils.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空！");
        AssertUtils.isTrue(StringUtils.isBlank(newPassword),"请输入新密码！");
        AssertUtils.isTrue(StringUtils.isBlank(confirmPassword),"请确认密码！");
        AssertUtils.isTrue((!(newPassword.equals(confirmPassword))),"最新密码输入不一致！");
        AssertUtils.isTrue((oldPassword.equals(newPassword)),"最新密码不能与原密码一致！");
    }

}
