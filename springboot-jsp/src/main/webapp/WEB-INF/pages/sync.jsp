<%--
  Created by IntelliJ IDEA.
  User: qiann
  Date: 2020/6/8
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach items="${userList}" var="user">
        <li>
            <span>${user.id}</span>
            <span>${user.name}</span>
        </li>
    </c:forEach>
</ul>

</body>
</html>
