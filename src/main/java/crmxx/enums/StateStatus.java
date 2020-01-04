package crmxx.enums;

public enum StateStatus {

//    已分配

    STATED(1),

//    未分配

    UN_STATED(0);

    private Integer state;


    StateStatus(Integer state) {
        this.state=state;
    }

    public Integer getState(){
        return state;
    }
}

