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
<ul class="ul">

</ul>
<script>
    const ul = document.querySelector('.ul');
    fetch('/findUsersByAsync', {
        'method': 'get',
        'headers': {
            'content-type': 'application/json;charset=UTF-8'
        },
    }).then(res => res.json()).then(res => { // 接受时
        let content = "";
        res.forEach((item)=>{
            content += "<li>"+
            "<span>"+item.id+"</span>"+
            " <span>"+item.name+"</span>"+
            "</li>";
        })
        ul.innerHTML = content;
    }, res => { // 拒绝时
        console.log(res.desc);
    });
</script>
</body>
</html>
