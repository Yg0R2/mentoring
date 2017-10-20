<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/book/book_macro.ftl" as bookMacro>

<@commonMacro.backToHome />

<div class="books">
    <legend>Books:</legend>

    <#list books as book>
        <@bookMacro.displayBook book />
    </#list>
</div>
