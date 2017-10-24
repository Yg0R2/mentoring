<#-- @ftlroot "../.." -->
<#import "/spring.ftl" as spring>
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

<#macro displayBooksSelect books, bindPath, multiple=false>
    <@spring.bind bindPath />

    <select <#if multiple>multiple</#if> id="${spring.status.expression}" name="${spring.status.expression}">
        <#list books as book>
            <option value="${book.getId()}">${book.getTitle()}</option>
        </#list>
    </select>
</#macro>
