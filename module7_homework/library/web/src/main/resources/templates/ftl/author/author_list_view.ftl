<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/author/author_macro.ftl" as authorMacro>

<@commonMacro.backToHome />

<div class="authors">
    <legend>Authors:</legend>

    <#list authors as author>
        <@authorMacro.displayAuthor author />
    </#list>
</div>
