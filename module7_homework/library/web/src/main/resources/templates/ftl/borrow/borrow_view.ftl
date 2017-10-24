<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/borrow/borrow_macro.ftl" as borrowMacro>

<@commonMacro.backToHome />

<#if borrow?has_content>
    <#if displaySuccessMessage!false>
        <div class="success-message">
            <p>New borrow successfully added.</p>
        </div>
    </#if>

    <@borrowMacro.displayBorrow borrow />
<#else>
    <div class="failed-message">
        <p>Borrow doesn't present.</p>
    </div>
</#if>
