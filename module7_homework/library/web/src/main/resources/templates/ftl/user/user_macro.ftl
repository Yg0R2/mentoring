<#-- @ftlroot "../.." -->
<#import "/spring.ftl" as spring>

<#macro displayUser user>
    <fieldset name="user" class="user">
        <legend>${user.getFirstName()}, ${user.getLastName()}</legend>

        <ul>
            <li>Active: ${user.isActive()?then("yes", "no")}</li>
            <li>Email address: ${user.getEmailAddress()}</li>
            <li>Role: ${user.getUserRole()}</li>
            <li>User additional data. // TODO: Add more user data</li>
            <li>
                <form method="POST" action="/user-edit?id=${user.getId()}">
                    <input type="submit" value="Edit">
                </form>
            </li>
        </ul>
    </fieldset>
</#macro>

<#macro displayUsersSelect users, bindPath, multiple=false>
    <@spring.bind bindPath />

    <select <#if multiple>multiple</#if> id="${spring.status.expression}" name="${spring.status.expression}">
        <#if !multiple>
            <option value="" selected>Not selected</option>
        </#if>

        <#list users as user>
            <option value="${user.getId()}">${user.getFirstName()}, ${user.getLastName()}</option>
        </#list>
    </select>
</#macro>