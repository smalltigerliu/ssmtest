<%--
  Created by IntelliJ IDEA.
  User: zt197
  Date: 2022/3/29
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";
%>

<html>
<head>
    <title>首页-练习</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function () {
            getProviceList();
            //查询按钮的点击事件
            $("#queryCity").click(function () {
                //选中的省份
                var pid = $("#province>option:selected").val();
                $.get("city/queryCity.do", {pid:pid},
                    function (resp) {
                        if(resp.code==0) {
                            alert(resp.msg);
                        }
                        $("#cityInfo").empty();
                        $.each(resp.data, function (i,n) {
                            $("#cityInfo").append("<tr><td>"+n.id+"</td><td>"+n.name+"</td></tr>")
                        })
                    },
                    "json")
            })

            //添加按钮时间

            $("#addCity").on("click", function () {
                var pid = $("#province>option:selected").val();
                var name = $(":text").val();
                $.post("city/addCity.do", {name:name, provinceId:pid}, function (resp) {
                    if(resp.code == 0) {
                        alert(resp.msg);
                    }
                }, "json")
            })


        })

        function getProviceList() {
            $.ajax({
                url:"queryProvince.do",
                dataType:"json",
                success:function (resp) {
                    $("#province").empty();
                    $.each(resp.data,function (i,n) {
                        $("#province").append("<option value='"+n.id+"'>"+n.name+"</option>")

                    })

                }
            })

        }
    </script>
</head>
<body>
<div align="center">
    <table>
        <tr>
            <td>
                省份列表：
            </td>
            <td>
                <select id="province">
                    <option value="0">请选择...</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>城市名称</td>
            <td><input type="text" id="cityname"></td>
        </tr>
        <tr>
            <td>
                <input type="button" id="addCity" value="添加城市">
            </td>
            <td>
                <input type="button" id="queryCity" value="查询省份的城市">
            </td>
        </tr>
    </table>
    <br/>
    <div id="dataDiv">
        <table border="1">
            <thead>
            <tr>
                <td>城市id</td>
                <td>城市名称</td>
            </tr>
            </thead>
            <tbody id="cityInfo"></tbody>

        </table>

    </div>

</div>

</body>
</html>
