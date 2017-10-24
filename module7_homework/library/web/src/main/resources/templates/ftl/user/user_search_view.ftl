<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<fieldset>
    <legend>Get User by id</legend>

    <form id="get-user-detail-form" method="POST" action="/user">
        User id:<br/>
        <input name="id" value="" required="true">

        <input type="submit" value="Submit">
    </form>
</fieldset>
