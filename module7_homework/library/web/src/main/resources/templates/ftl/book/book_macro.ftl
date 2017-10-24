<#-- @ftlroot "../.." -->
<#import "/ftl/author/author_macro.ftl" as authorMacro>

<#macro displayBook book, displayAuthors=true>
    <fieldset name="book" class="book">
        <legend>${book.getTitle()}</legend>

        <div>
            Book additional data. // TODO: Add more book data
        </div>

        <#if displayAuthors>
            <legend>Authors:</legend>

            <#list book.getAuthors() as author>
                <@authorMacro.displayAuthor author, false />
            </#list>
        </#if>
    </fieldset>
</#macro>
