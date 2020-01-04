$(function () {
    $("#dlg").dialog({
        onclose:function () {
            $("input[name='roleName']").val("");
            $("input[name='roleRemark']").val("");
            $("input[name='id']").val("");
        }
    })
})

function searchRoleByParams() {
    $("#dg").datagrid("load",{
        roleName:$("#roleName").val(),
    })
}
//打开用户添加对话框
function openRoleAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","角色添加");
}
//打开授权对话框
function openShou() {
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自CRM","请选择待授权的角色！","info");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自CRM","暂不支持批量授权！","info");
        return;
    }

    $(function () {
        $.ajax({

            type:"post",
            url:ctx+"/module/queryModules",
            dataType:"json",
            success:function (result) {
                var setting = {
                    check:{
                        enable:true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };
                $.fn.zTree.init($("#tree11"),setting,result);
            }
        })
    });

    $("#grant").dialog("open").dialog("setTitle","角色授权");
}
//关闭用户添加对话框
function closeRoleDialog() {
    $("#dlg").dialog("close");
}
//用户添加
function saveOrUpdateRole() {
    var id=$("input[name='id']").val();

    var url =ctx+"/role/save";
    if(!(isEmpty(id))){
        url=ctx+"/role/update";
    }
    $("#fm").form("submit",{
        url:url,
        onsubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                closeRoleDialog();
                searchRoleByParams();
            }else{
                $.messager.alert("来自CRM",data.msg,"info");
            }
        }
    })
}
//修改用户
function openRoleModifyDialog() {
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自CRM","请选择待修改的记录！","info");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自CRM","暂不支持批量修改！","info");
        return;
    }

    // if(!(isEmpty(rows[0].roleIdsStr))){
    //     rows[0].userRoles=rows[0].roleIdsStr.split(",");
    // }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","角色更新")
}

//删除角色
function deleteRole() {
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
                url:ctx+"/role/delete",
                data:{
                    roleId:rows[0].id
                },
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        searchRoleByParams();
                    }else{
                        $.messager.alert("来自CRM",data.msg,"info");
                    }
                }
            })
        }
    })
}

