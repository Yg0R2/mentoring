<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/book/book_macro.ftl" as bookMacro>

<@commonMacro.backToHome />

<#if book?has_content>
    <#if displaySuccessMessage!false>
        <div class="success-message">
            <p>New book successfully added.</p>
        </div>
    </#if>

    <@bookMacro.displayBook book />
<#else>
    <div class="failed-message">
        <p>Book doesn't present.</p>
    </div>
</#if>
