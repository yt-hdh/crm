function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}
function logout() {
    $.messager.confirm("来自CRM","确认退出系统",function (r) {
        if(r){
            $.ajax({
                type:"json",
                url:ctx+"/user/exit",
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        $.messager.alert("来自CRM","系统将在2秒后退出...")
                        setTimeout(function () {
                            $.removeCookie("idStr"),
                            $.removeCookie("userName"),
                            $.removeCookie("trueName")
                            window.location.href=ctx="index";
                        },2000);
                    }
                }
            });
        }
    })
}
//打开密码修改对话并修改标题
function openPasswordModifyDialog() {
    $("#dlg").dialog("open").dialog("setTitle","密码修改");
}
//对话框保存操作
function modifyPassword() {
    $("#fm").form("submit",{
        url:ctx+"/user/updatePassword",
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自CRM","密码修改成功，2秒后自动退出","info");
                setTimeout(function () {
                    $.removeCookie("idStr");
                    $.removeCookie("userName");
                    $.removeCookie("trueName");
                    window.location.href=ctx+"/index";
                },2000);
            }else{
                $.messager.alert("来自CRM",data.msg,"error");
            }
        }
    })
}
//关闭对话框
function closePasswordModifyDialog() {
    $("#dlg").dialog("close");
}
