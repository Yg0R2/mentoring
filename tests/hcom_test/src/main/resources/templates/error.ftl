<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>HCom test error page</title>
    <style type="text/css">
        <#include "css/style.css">
    </style>
</head>
<body>
    <fieldset>
        <legend>${statusCode} - ${statusReasonPhrase}</legend>

        <fieldset>
            <legend>Error message:</legend>
            ${errorMessage}
        </fieldset>

        <fieldset>
            <legend>Stacktrace:</legend>
            ${stacktrace}
        </fieldset>
    </fieldset>
</body>
</html>
