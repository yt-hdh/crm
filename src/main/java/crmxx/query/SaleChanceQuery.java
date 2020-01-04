package crmxx.query;

import base.BaseQuery;

public class SaleChanceQuery extends BaseQuery {
    //客户名称
    private String customerName;
    //创建人
    private String CreateMan;

    //分配状态
    private Integer state;

    private String assignMan;

    public String getAssignMan() {
        return assignMan;
    }

    public void setAssignMan(String assignMan) {
        this.assignMan = assignMan;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateMan() {
        return CreateMan;
    }

    public void setCreateMan(String createMan) {
        CreateMan = createMan;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
