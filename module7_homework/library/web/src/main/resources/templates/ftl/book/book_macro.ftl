<#import "/ftl/author/author_macro.ftl" as authorMacro>

<#macro displayBook book>
    <fieldset name="book" class="book">
        <legend>${book.getTitle()}</legend>

        <legend>Authors:</legend>

        <#list book.getAuthors() as author>
            <@authorMacro.displayAuthor author />
        </#list>
    </fieldset>
</#macro>
