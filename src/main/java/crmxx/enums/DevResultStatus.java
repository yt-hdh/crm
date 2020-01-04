package crmxx.enums;

/**
 * 开发状态的枚举类
 */
public enum DevResultStatus {
    //    未开发
        UN_DEV(0),
    //    开发中
        DEVING(1),
    //    开发成功
        DEVSUCCESS(2),
    //    开发失败
        DEVFAILED(3);

//        状态属性值

    private Integer devstatus;


    DevResultStatus(Integer devstatus) {
        this.devstatus=devstatus;
    }

    public Integer getDevstatus() {
        return devstatus;
    }
}
