<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<fieldset>
    <legend>Get Author by id</legend>

    <form id="get-author-detail-form" method="POST" action="/author">
        Author id:<br/>
        <input name="id" value="" required="true">

        <input type="submit" value="Submit">
    </form>
</fieldset>
