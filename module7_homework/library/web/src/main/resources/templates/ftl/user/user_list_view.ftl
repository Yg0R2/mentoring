<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/user/user_macro.ftl" as userMacro>

<@commonMacro.backToHome />

<div class="users">
    <legend>Users:</legend>

    <#list users as user>
        <@userMacro.displayUser user />
    </#list>
</div>
