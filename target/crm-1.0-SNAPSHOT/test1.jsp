<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/8/1 0001
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        $.ajax({
            url:"",
            data:{},
            type:"",
            dataType:"json",
            success:function (data){

            }
        })

        //时间控件
        $(".time").datetimepicker({
        minView: "month",
        language:  'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "top-left"
        });

</body>
</html>
