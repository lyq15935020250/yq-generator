<!DOCTYPE html>
<html>
<head>
    <title>yq-generator官网</title>
</head>
<body>
<h1>欢迎来到yq-generator官网</h1>
<ul>
    <#-- 循环渲染导航条 -->
    <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a></li>
    </#list>
</ul>
<#-- 底部版权信息（注释部分，不会被输出）-->
<footer>
    ${currentYear} yq-generator. All rights reserved.
</footer>
</body>
</html>