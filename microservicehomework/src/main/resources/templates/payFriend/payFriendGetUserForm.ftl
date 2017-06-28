<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Credit Card get User</title>
    <style type="text/css">
        <#include "../css/style.css">
    </style>
</head>
<body>
<fieldset>
    <legend>PayFriend getUser</legend>
    <form name="userRequest" action="${getUserURL}" method="post">

        <input name="email" value="${request.email}" />

        <button type="submit">Submit</button>
    </form>
</fieldset>
</body>
</html>
