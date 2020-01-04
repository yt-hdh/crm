<#include "common.ftl" >
  <script type="text/javascript">
    alert('${msg}');
    if('${uri}'=="/main"){
    	window.location.href="${ctx}/index";
    }else{
    	window.parent.location.href="${ctx}/index";
    }
    
  </script>


<#--
${msg}  || ${code}
-->
