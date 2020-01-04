$(function () {
   $("#dg").edatagrid({
      saveUrl:ctx+"/cusDevPlan/save?saleChanceId="+$("#saleChanceId").val(),
      updateUrl:ctx+"/cusDevPlan/update?saleChanceId="+$("#saleChanceId").val(),
      destroyUrl:ctx+"/cusDevPlan/delete"
   })
});

function saveCusDevPlan() {
   $("#dg").edatagrid("saveRow");
   $("#dg").edatagrid("load");
}

function delCusDevPlan() {
   // $("#dg").edatagrid("destroy");
   // $("#dg").edatagrid("load");
   var rows=$("#dg").edatagrid("getSelections");

   if(rows.length==0){
      $.messager.alert("来自CRM","请选择待删除的记录！","info");
      return;
   }
   $.messager.confirm("来自CRM","确定要删除选中的记录",function (r) {
      if(r){
         $.ajax({
            type:"post",
            url:ctx+"/cusDevPlan/delete",
            data:{
               id:rows[0].id
            },
            dataType:"json",
            success:function (data) {
               if(data.code==200){
                  $("#dg").edatagrid("load");
               }else{
                  $.messager.alert("来自CRM",data.msg,"info");
               }
            }
         })
      }
   });
}