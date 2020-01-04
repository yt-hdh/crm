package crmxx.service;

import base.BaseService;
import base.constants.CrmContants;
import crmxx.db.dao.RoleMapper;
import crmxx.db.dao.UserRoleMapper;
import crmxx.po.Role;
import crmxx.query.RoleQuery;
import crmxx.utils.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@SuppressWarnings("all")
public class RoleService extends BaseService<Role,Integer> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 所有角色查询
     * @return
     */
    public List<Map<String,Object>> queryAllRoles(){
        List<Map<String,Object>> result=new ArrayList<Map<String, Object>>();
        List<Role> roles=queryByParams(null);
        roles.forEach(role -> {
            Map<String,Object> map=new HashMap<>();
            map.put("id",role.getId());
            map.put("text",role.getRoleName());
            result.add(map);
        });
        return result;
    }

    /**
     * 角色添加
     * @param role
     */
    public void saveRole(Role role) {
        AssertUtils.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名不能为空！");
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setRoleName(role.getRoleName());
        List<Role> roles = queryByParams(roleQuery);
        AssertUtils.isTrue(null != roles && roles.size() > 0, "角色名已存在！");
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        role.setIsValid(1);
        AssertUtils.isTrue(save(role)==0, CrmContants.FAILED_MSG);
    }
    /**
     * 角色更新
     * @param role
     */
    public void updateRole(Role role) {
        AssertUtils.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名不能为空！");
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setRoleName(role.getRoleName());
        List<Role> roles = queryByParams(roleQuery);
        if(null!=roles && roles.size()>0){
            AssertUtils.isTrue(!(roles.get(0).getId().equals(role.getId())),"角色名已存在！");
        }
        role.setUpdateDate(new Date());
        AssertUtils.isTrue(update(role)==0, CrmContants.FAILED_MSG);
    }

    /**
     * 角色删除
     * @param role
     */
    public void deleteRoleByRoleId(Integer roleId){
        Role role=queryById(roleId);
        AssertUtils.isTrue(null==roleId||null==role,"带删除的记录不存在！");
        int count =userRoleMapper.countUserRoleByRoleId(roleId);
        if(count>0){
            AssertUtils.isTrue(userRoleMapper.deleteByRoleId(roleId)!=count,CrmContants.FAILED_MSG);
        }
        role.setIsValid(0);
        AssertUtils.isTrue(roleMapper.update(role)==0,CrmContants.FAILED_MSG);
    }
}
