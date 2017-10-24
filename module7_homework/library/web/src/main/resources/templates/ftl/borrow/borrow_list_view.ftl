<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/borrow/borrow_macro.ftl" as borrowMacro>

<@commonMacro.backToHome />

<div class="borrows">
    <legend>Borrows:</legend>

    <#list borrows as borrow>
        <@borrowMacro.displayBorrow borrow />
    </#list>
</div>
