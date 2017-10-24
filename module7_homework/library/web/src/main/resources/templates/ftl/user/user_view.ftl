<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/user/user_macro.ftl" as userMacro>

<@commonMacro.backToHome />

<#if user?has_content>
    <#if displaySuccessMessage!false>
        <div class="success-message">
            <p>New user successfully added.</p>
        </div>
    </#if>

    <@userMacro.displayUser user />
<#else>
    <div class="failed-message">
        <p>User doesn't present.</p>
    </div>
</#if>
