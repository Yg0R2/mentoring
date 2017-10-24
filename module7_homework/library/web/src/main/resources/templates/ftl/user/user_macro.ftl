<#-- @ftlroot "../.." -->

<#macro displayUser user>
    <fieldset name="user" class="user">
        <legend>${user.getFirstName()}, ${user.getLastName()}</legend>

        <ul>
            <li>Email address: ${user.getEmailAddress()}</li>
            <li>Role: ${user.getUserRole()}</li>
            <li>User additional data. // TODO: Add more user data</li>
        </ul>
    </fieldset>
</#macro>
