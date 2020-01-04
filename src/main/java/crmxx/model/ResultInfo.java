package crmxx.model;

import base.constants.CrmContants;

public class ResultInfo {
    private Integer code= CrmContants.SUCCESS_CODE;
    private String msg=CrmContants.SUCCESS_MSG;
    private Object result;

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
