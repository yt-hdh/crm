package crmxx.utils;

import base.exceptions.LoginException;
import base.exceptions.ParamsException;

public class AssertUtils {

    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }

    public static void isTrue(Boolean flag,Integer code,String msg){
        if(flag){
            throw new ParamsException(code,msg);
        }
    }

    public static void isTrue(Boolean flag){
        if(flag){
            throw new ParamsException();
        }
    }
    public static void isNotLogin(Boolean flag,String msg){
        if(flag){
            throw new LoginException(msg);
        }
    }
}
