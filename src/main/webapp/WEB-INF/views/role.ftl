<html>
<head>
    <#include "common.ftl" >
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/role.js"></script>

</head>
<body style="margin: 1px">
<table id="dg"  class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/role/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <input type="hidden" id="id" name="roleId" />
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="roleName" width="200" align="center">角色名称</th>
        <th field="roleRemark" width="100" align="center">备注</th>
        <th field="createDate" width="100" align="center">创建时间</th>
        <th field="updateDate" width="150" align="center">更新时间</th>
    </tr>
    </thead>
</table>

<#--工具栏-->
<div id="tb">
    <div>
        <a href="javascript:openRoleAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>

        <a href="javascript:openRoleModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>

        <a href="javascript:deleteRole()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>

        <a href="javascript:openShou()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">授权</a>
    </div>
    <div>
        角色名称：<input type="text" id="roleName" size="20" onkeydown="if(event.keyCode==13) searchRoleByParams()"/>
        <a href="javascript:searchRoleByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
<#--角色添加对话框-->
<div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>角色名称：</td>
                <td><input type="text" name="roleName"
                           class="easyui-validatebox" required="true"/>
                    <font color="red">*</font></td>
                <td> </td>
                <td>备    注：</td>
                <td><input type="text" name="roleRemark" /></td>
            </tr>

            <input type="hidden" id="id" name="id" />
        </table>
        <input type="hidden" name="id"/>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:saveOrUpdateRole()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeRoleDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

<#--角色授权对话框-->
<div id="grant" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
     closed="true" >
    <div>
        <ul id="tree11" class="ztree"></ul>
    </div>
</div>

</body>
</html>