$(function () {
    $("#dlg").dialog({
        onclose:function () {
            $("input[name='userName']").val("");
            $("input[name='phone']").val("");
            $("input[name='email']").val("");
            $("input[name='trueName']").val("");
        }
    })
})

function searchUserByParams() {
    $("#dg").datagrid("load",{
        userName:$("#userName").val(),
        phone:$("#phone").val(),
        email:$("#email").val()
    })
}
//打开用户添加对话框
function openUserAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","用户添加");
}
//关闭用户添加对话框
function closeUserDialog() {
    $("#dlg").dialog("close");
}
//用户添加保存
function saveOrUpdateUser() {
    var id=$("input[name='id']").val();

    var url =ctx+"/user/save";
    if(!(isEmpty(id))){
        url=ctx+"/user/update";
    }
    $("#fm").form("submit",{
        url:url,
        onsubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                closeUserDialog();
                searchUserByParams();
            }else{
                $.messager.alert("来自CRM",data.msg,"info");
            }
        }
    })
}
//修改用户
function openUserModifyDialog() {
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自CRM","请选择待修改的记录！","info");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自CRM","暂不支持批量修改！","info");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","用户更新")
}

//删除用户
function deleteUser() {
    var rows=$("#dg").datagrid("getSelections");

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
                url:ctx+"/user/delete",
                data:{
                    userId:rows[0].id
                },
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        searchUserByParams();
                    }else{
                        $.messager.alert("来自CRM",data.msg,"info");
                    }
                }
            })
        }
    })
}
