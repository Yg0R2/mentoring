<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<fieldset>
    <legend>Get Inventory by id</legend>

    <form id="get-inventory-detail-form" method="POST" action="/inventory">
        Inventory id:<br/>
        <input name="id" value="" required="true">

        <input type="submit" value="Submit">
    </form>
</fieldset>
