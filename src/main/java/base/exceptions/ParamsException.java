package base.exceptions;

import base.constants.CrmContants;

public class ParamsException extends RuntimeException {

    private Integer code= CrmContants.FAILED_CODE;
    private String msg=CrmContants.FAILED_MSG;

    public ParamsException() {
        super(CrmContants.FAILED_MSG);
    }
    public ParamsException(Integer code) {
        super(CrmContants.FAILED_MSG);
    }
    public ParamsException(String msg) {
        super(msg);
        this.msg=msg;
    }
    public ParamsException(Integer code,String msg) {
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
