package crmxx.db.dao;

import base.BaseMapper;
import crmxx.po.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

        int countUserRoleByUserId(@Param("userId") Integer userId);

        int deleteByUserId(@Param("userId") Integer userId);

        int deleteByRoleId(@Param("roleId") Integer roleId);

        int countUserRoleByRoleId(@Param("roleId") Integer userId);
}