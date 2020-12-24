<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>0.0</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.3.1.js"></script>

    <script type="text/javascript">
            $(function () {
                var falg1=false;
                var falg2=false;
                $.get("login/findProvince",{},function (data) {
                    //[province:江西省},{},..]
                    var lis='';     //异步循环赋值
                    for (var i = 0; i < data.length; i++) {
                        var li = '<option >'+data[i]+'</option>';
                        lis += li;
                    }
                    $("#select1").html(lis);
                    falg1=true;
                })
                    //一旦第一个下列表被点击后
                document.getElementById("select1").onchange =function(){
                    var province = document.getElementById("select1").value;

                    $("#div0").removeAttr("style");//去除style属性

                    $.get("login/findSName",{province:province},function (data) {
                        var lis='';
                        for (var i = 0; i < data.length; i++) {
                            var li = '<option >' + data[i] + '</option>';

                            lis += li;
                        }
                        $("#select2").html(lis);
                        falg2= true;
                    })
                }





            })
    </script>
</head>
<body>
<form id="selectFrom" action="${pageContext.request.contextPath}/login/findSchool">
    <div >
        <select name="province" id="select1">
        </select>
    </div>
    <div  id="div0" style="display: none;">
        <select name="schoolName" id="select2">
        </select>
    </div>
    <button type="submit" class="btn btn-default">确认</button>
</form>

</body>
</html>
