<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>

<@commonMacro.backToHome />

<fieldset>
    <legend>Get Book by id</legend>

    <form id="get-book-by-id" method="POST" action="/book">
        Book id:<br/>
        <input name="id" value="" required="true">

        <input type="submit" value="Submit">
    </form>
</fieldset>

<fieldset>
    <legend>Get Book by title</legend>

    <form id="get-book-by-title" method="POST" action="/book">
        Book title:<br/>
        <input name="title" value="" required="true">

        <input type="submit" value="Submit">
    </form>
</fieldset>
