<html>
<head>
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core.js"></script>

</head>
<body>

    <div>
        <ul id="tree11" class="ztree"></ul>
    </div>


    <script type="text/javascript">

        var ctx="${ctx}";

        $(function () {
            $.ajax({

                type:"post",
                url:ctx+"/module/queryModules",
                dataType:"json",
                success:function (result) {
                    var setting = {
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

    </script>
</body>
</html>