<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<@commonMacro.displayErrorsIfPresent errors![] />

<form id="create-user-form" method="POST" modelAttribute="createUserForm" action="/create-user" enctype="application/json">
    <fieldset>
        <legend>Create new user</legend>

        <ul>
            <li>
                <label>First Name:</label>
                <input name="firstName" required value="">
            </li>

            <li>
                <label>Last Name:</label>
                <input name="lastName" required value="">
            </li>

            <li>
                <label>Email address:</label>
                <input name="emailAddress" required value="">
            </li>

            <li>
                <select name="userRole">
                    <#list availableUserRoles as userRole>
                        <option value="${userRole}">${userRole}</option>
                    </#list>
                </select>
            </li>
        </ul>
    </fieldset>

    <input type="submit" value="Submit">
</form>
