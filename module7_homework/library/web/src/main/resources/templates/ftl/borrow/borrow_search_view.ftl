<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<fieldset>
    <legend>Get Borrow by id</legend>

    <form id="get-borrow-detail-form" method="POST" action="/borrow">
        Borrow id:<br/>
        <input name="id" value="" required="true">

        <input type="submit" value="Submit">
    </form>
</fieldset>
