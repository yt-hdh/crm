$(function () {
    $("#dlg").dialog({
        onclose:function () {
            $("input[name='roleName']").val("");
            $("input[name='roleRemark']").val("");
            $("input[name='id']").val("");
        }
    })
})

//搜索
function searchRoleByParams() {
    $("#dg").datagrid("load",{
        roleName:$("#roleName").val(),
    })
}
//打开用户添加对话框
function openDialog(dialogId,title) {
    $("#"+dialogId).dialog("open").dialog("setTitle",title);
}
//关闭用户添加对话框
function closeDialog(dialogId) {
    $("#"+dialogId).dialog("close");
}
//用户添加
function saveOrUpdate(formId,saveUrl,updateUrl,search) {
    var id=$("input[name='id']").val();

    var url =saveUrl;
    if(!(isEmpty(id))){
        url=updateUrl;
    }
    $("#"+formId).form("submit",{
        url:url,
        onsubmit:function () {
            return $("#"+formId).form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                closeDialog();
                search();
            }else{
                $.messager.alert("来自CRM",data.msg,"info");
            }
        }
    })
}

//修改用户
function openModifyDialog(formId,dialogId,title) {
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自CRM","请选择待修改的记录！","info");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自CRM","暂不支持批量修改！","info");
        return;
    }

    if(!(isEmpty(rows[0].roleIdsStr))){
        rows[0].userRoles=rows[0].roleIdsStr.split(",");
    }
    $("#"+formId).form("load",rows[0]);
    $("#"+dialogId).dialog("open").dialog("setTitle",title);
}

//删除角色
function deleteRole(dgId,deleteUrl,search) {
    var rows=$("#"+dgId).datagrid("getSelections");

    if(rows.length==0){
        $.messager.alert("来自CRM","请选择要删除的记录！","info");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自CRM","暂不支持批量删除！","info");
        return;
    }
    $.messager.confirm("来自CRM","确定删除选中的记录吗？",function (r) {
        if(r){
            $.ajax({
                type:"post",
                url:deleteUrl,
                data:{
                    id:rows[0].id
                },
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        search();
                    }else{
                        $.messager.alert("来自CRM",data.msg,"info");
                    }
                }
            })
        }
    })
}
