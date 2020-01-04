function login() {
    // 获取输入框参数
    var userName=$("input[name='username']").val();
    var userPwd=$("input[name='password']").val();
//    输入框参数校验
        if (isEmpty(userName)){
            alert("请输入用户名！");
            return;
        }
        if(isEmpty(userPwd)){
            alert("请输入用户密码!");
            return;
        }
        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data:{
                userName:userName,
                userPwd:userPwd
            },
            dataType:"json",
            success:function (result) {
                if(result.code==200){
                    $.cookie("idStr",result.result.isStr);
                    $.cookie("userName",result.result.userName);
                    $.cookie("trueName",result.result.trueName);
                    window.location.href=ctx+"/main";
                }else {
                    alert(result.msg);
                }
            }
        })

}