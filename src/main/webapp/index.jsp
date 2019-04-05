<%--
  Created by IntelliJ IDEA.
  User: CaihuA
  Date: 2019/4/5
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $("#download").click(function () {
                var birth = $("input[name='birth']").eq(0).val();
                window.location.href="/people/findPeople?birth="+birth;
            })
        })
    </script>
</head>
<body>
<input name="birth" type="date">
<button id="download">下载人员信息</button>
</body>
</html>
