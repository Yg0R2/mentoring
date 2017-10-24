<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/author/author_macro.ftl" as authorMacro>

<@commonMacro.backToHome />

<#if author?has_content>
    <#if displaySuccessMessage!false>
        <div class="success-message">
            <p>New author successfully added.</p>
        </div>
    </#if>

    <@authorMacro.displayAuthor author />
<#else>
    <div class="failed-message">
        <p>Author doesn't present.</p>
    </div>
</#if>
