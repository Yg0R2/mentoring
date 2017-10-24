<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<form id="create-author-form" method="POST" modelAttribute="createAuthorForm" action="/create-author" enctype="application/json">
    <fieldset>
        <legend>Create new author</legend>

        <ul>
            <li>
                <label>First Name:</label>
                <input name="firstName" required value="">
            </li>

            <li>
                <label>Last Name:</label>
                <input name="lastName" required value="">
            </li>
        </ul>
    </fieldset>

    <input type="submit" value="Submit">
</form>
