<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/user.js"></script>

</head>
<body style="margin: 1px">
<table id="dg"  class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/user/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="userName" width="200" align="center">用户名称</th>
        <th field="trueName" width="100" align="center">真实名称</th>
        <th field="phone" width="100" align="center">手机号码</th>
        <th field="email" width="200" align="center">邮箱地址</th>
        <th field="createDate" width="100" align="center">创建时间</th>
        <th field="updateDate" width="150" align="center">更新时间</th>
        <th field="roleName" width="200" align="center">用户角色</th>

    </tr>
    </thead>
</table>

<#--工具栏-->
<div id="tb">
    <div>
        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>

        <a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>

        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        用户名称：<input type="text" id="userName" size="20" onkeydown="if(event.keyCode==13) searchUserByParams()"/>
        手机号码：<input type="text" id="phone" size="20" onkeydown="if(event.keyCode==13) searchUserByParams()"/>
        邮箱地址：<input type="text" id="email" size="20" onkeydown="if(event.keyCode==13) searchUserByParams()"/>

        <a href="javascript:searchUserByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
<#--用户添加对话框-->
<div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名称：</td>
                <td><input type="text" name="userName"
                           class="easyui-validatebox" required="true"/>
                    <font color="red">*</font></td>
                <td> </td>
                <td>手机号码：</td>
                <td><input type="text" name="phone" /></td>
            </tr>
            <tr>
                <td>邮箱地址：</td>
                <td><input type="text" name="email"
                           class="easyui-validatebox" required="true"/></td>
                <td> </td>
                <td>真实名称：</td>
                <td><input type="text" name="trueName"
                           class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>用户角色：</td>
                <td><input type="text" name="userRoles" panelHeight="auto" multiple="true" class="easyui-combobox" url="${ctx}/role/allRoles" valueField="id" textField="text"/></td>
            </tr>

            <input type="hidden" id="createMan" name="createMan" />
            <input type="hidden" id="id" name="id" />
        </table>
        <input type="hidden" name="id"/>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:saveOrUpdateUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>