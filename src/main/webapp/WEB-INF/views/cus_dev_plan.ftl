<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/cus.dev.plan.js"></script>

</head>
<body style="margin: 1px">
<table id="dg" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/cusDevPlan/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="chanceSource" width="200" align="center">机会来源</th>
        <th field="customerName" width="100" align="center">客户名称</th>
        <th field="cgjl" width="100" align="center">成功几率</th>
        <th field="overview" width="200" align="center">概要</th>
        <th field="linkMan" width="100" align="center">联系人</th>
        <th field="linkPhone" width="150" align="center">联系电话</th>

        <th field="description" width="200" align="center">机会描述</th>
        <th field="createMan" width="100" align="center">创建人</th>
        <th field="createDate" width="200" align="center">创建时间</th>

        <th field="assignMan" width="200" align="center" hidden="true">指派人</th>

        <th field="assignTime" width="200" align="center" hidden="true">指派时间</th>
        <th field="devResult" width="200" align="center" formatter="formatterDevResult" styler="changeColor">客户开发状态</th>
        <th field="abc" width="200" align="center" formatter="formatterabc">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        客户名称：
        <input type="text" id="s_customerName" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()"/>
        创建人：
        <input type="text" id="s_createMan" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()"/>
        <a href="javascript:searchSaleChance()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

</body>
</html>