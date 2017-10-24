<#macro backToHome>
    <a href="/"><< Back to home</a>
</#macro>

<#macro displayErrorsIfPresent errors>
    <#if errors?has_content>
        <#list errors as error>
            <div class="error-message">
                ${error.toString()}<br />
            </div>
        </#list>
    </#if>
</#macro>
