<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>HCom test data page</title>
    <style type="text/css">
        <#include "css/style.css">
    </style>
</head>
<body>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    <#else>
        <#list resources as resource>
            <div class="${resource.getColor()}" key="${resource.getKey()}">${resource.getValue()?upper_case}</div>
        </#list>
    </#if>
</body>
</html>
