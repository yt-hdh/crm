
 $(function () {
//     $("#dg").datagrid({
//         rowStyler:function (index,row) {
//             if(row.devResult==0){
//                 return"background-color:yellow";
//             }else if (row.devResult==1){
//                 return"background-color:skyblue";
//             }else if(row.devResult==2){
//                 return"background-color:green";
//             }else if(row.devResult==3){
//                 return"background-color:red";
//             }else{
//                 return"background-color:blue";
//             }
//         }
//     })
     $("#dlg").dialog({
         onClose:function () {
             $("#fm").form("clear");
         }
     });
 })
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
        createMan:$("#s_createMan").val(),
        state:$("#s_state").combobox("getValue")
    })
}



//打开机会数据添加对话框
function openSaleChanceAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加机会数据");
}
//关闭机会数据添加对话框
function closeSaleChanceDialog() {
    $("#dlg").dialog("close");
}

//保存或者更新机会数据
function saveOrUpdateSaleChance() {
    var id=$("input[name='id']").val();
    var url=ctx+"/saleChance/save";
    if(!isEmpty(id)){
        url=ctx+"/saleChance/update";
    }
    $("#fm").form("submit",{
        url:url,
        onSubmit:function f() {
           return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自CRM","营销机会数据添加成功!","info");
                closeSaleChanceDialog();
                searchSaleChance();
            }else{
                $.messager.alert("来自CRM",data.msg,"info");
            }
        }
    })
}
// 机会数据修改
function openSaleChanceModifyDialog() {

    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自CRM","请选择要更新的机会数据!","info");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自CRM","暂不支持机会数据的批量更新!","info");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","更新机会数据!");
}

function deleteSaleChance() {
   var rows= $("#dg").datagrid("getSelections");
   if(rows.length==0){
       $.messager.alert("来自CRM","请选择需要删除的机会数据!","info");
       return;
   }
   var ids="ids=";
   for (var i=0;i<rows.length;i++){
       if(i<rows.length-1){
           ids+rows[i].id+"ids=";
       }else{
           ids=ids+rows[i].id;
       }
   }
   $.messager.confirm("来自CRM","确定删除选中的"+rows.length+"条记录吗？",function (r) {
        if(r){
            $.ajax({
                type:"post",
                url:ctx+"/saleChance/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        $.messager.alert("来自CRM","营销机会数据删除成功！","info");
                        searchSaleChance();
                    }else{
                        $.messager.alert("来自CRM",data.msg,"info");
                    }
                }
            })
        }
   })
}