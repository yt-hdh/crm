
//未分配状态格式化
function formatterState(val,rowdata,rowindex) {
    if(val==0){
        return "未分配";
    }else if(val==1){
        return "已分配";
    }else{
        return "未知情况";
    }
}
//开发状态格式化
function formatterDevResult(val) {
    if(val==0){
        return "未开发";
    }else if(val==1){
        return "开发中";
    }else if (val==2) {
        return "开发成功";
    }else if (val==3) {
        return "开发失败";
    }
}
//客户开发计划字体颜色
function changeColor(val) {
    if(val==0){
        return 'color:yellow;';
    }else if (val==1){
        return 'color:skyblue;';
    }else if(val==2){
        return 'color:green;';
    }else if(val==3){
        return 'color:red;';
    }
}


//搜索功能完成
function searchSaleChance() {
    $("#dg").datagrid("load",{
        customerName:$("#s_customerName").val(),
        createMan:$("#s_createMan").val()
    })
}

function formatterabc(val,rows) {
    if(rows.devResult==0||rows.devResult==1){
        var href="javascript:openCusDevplanTab('执行开发"+rows.id+"',"+rows.id+")";
        return "<a href="+href+">执行开发</a>";
    }else{
        var href="javascript:openCusDevplanTab('查看详情"+rows.id+"',"+rows.id+")";
        return "<a href="+href+">查看详情</a>";
    }
}
function openCusDevplanTab(title,sid){
    window.parent.openTab(title,ctx+"/saleChance/querySaleChanceBySid?sid="+sid);
}



