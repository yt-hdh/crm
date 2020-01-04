package crmxx.db.dao;

import base.BaseMapper;
import crmxx.po.User;

public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);
}