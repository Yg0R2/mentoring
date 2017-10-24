<#-- @ftlroot "../.." -->
<#import "/spring.ftl" as spring>
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/user/user_macro.ftl" as userMacro>

<@commonMacro.backToHome />

<@spring.bind "user" />
<form method="POST" action="/user-update" modelAttribute="updateUserForm">
    <input name="id" value="${user.getId()}" hidden>

    <legend>${user.getFirstName()}, ${user.getLastName()}</legend>

    <ul>
        <li>
            <label>Active</label>
            <input name="active" type="checkbox" <#if user.isActive()>checked</#if>>
        </li>

        <li>
            <label>Email address</label>
            <input name="emailAddress" value="${user.getEmailAddress()}">
        </li>
        <li>
            <label>Role</label>
            <select name="userRole">
                <#list availableUserRoles as userRole>
                    <option value="${userRole}" <#if userRole == user.getUserRole()>selected</#if>>${userRole}</option>
                </#list>
            </select>
        </li>
    </ul>

    <input type="submit" value="Submit">
</form>
