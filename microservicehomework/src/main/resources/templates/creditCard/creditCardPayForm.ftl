<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Credit Card pay</title>
    <style type="text/css">
        <#include "../css/style.css">
    </style>
</head>
<body>
<fieldset>
    <legend>Credit Card Pay</legend>
    <form name="paymentRequest" action="${creditCardPayURL}" method="post">
        <#include "creditCard.ftl">

        <br />

        <#include "customer.ftl">

        <br />

        <#include "../payment.ftl">

        <br />

        <button type="submit">Submit</button>
    </form>
</fieldset>
</body>
</html>
