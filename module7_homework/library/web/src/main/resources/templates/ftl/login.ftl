<#if error.isPresent()>
    <div class="error-message">
        The email or password you have entered is invalid, try again.
    </div>
</#if>

<form role="form" method="POST" action="/login">
    <#--input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/-->

    <label for="emailAddress">Email address:</label>
    <input id="emailAddress" name="emailAddress" required>

    <label for="password">Password:</label>
    <input id="password" name="password" type="password" required>

    <input type="submit" value="Log in">
</form>
