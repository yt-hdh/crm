package base.exceptions;

import base.constants.CrmContants;


public class LoginException extends RuntimeException {

    private Integer code= CrmContants.NOT_LOGIN_CODE;

    private String msg=CrmContants.NOT_LOGIN_MSG;

    public LoginException(){
        super(CrmContants.NOT_LOGIN_MSG);
    }
    public LoginException(Integer code){
        super(CrmContants.NOT_LOGIN_MSG);
    }
    public LoginException(String msg){
        super(msg);
        this.msg=msg;
    }
    public LoginException(Integer code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
