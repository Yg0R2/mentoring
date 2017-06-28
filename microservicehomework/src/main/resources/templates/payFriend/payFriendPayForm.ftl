<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>PayFriend pay</title>
    <style type="text/css">
        <#include "../css/style.css">
    </style>
</head>
<body>
<fieldset>
    <legend>PayFriend Pay</legend>
    <form name="payFriendRequest" action="${payFriendPayURL}" method="post">
        <#assign payFriendTitle="Pay Freind" />
        <#assign payFriendModelName="payFriend" />
        <#assign payFriend=request.payFriend />
        <#include "payFriend.ftl">

        <br />

        <#assign payFriendTitle="Target Friend" />
        <#assign payFriendModelName="targetFriend" />
        <#assign payFriend=request.targetFriend />
        <#include "payFriend.ftl">

        <br />

        <#include "../payment.ftl">

        <br />

        <button type="submit">Submit</button>
    </form>
</fieldset>
</body>
</html>