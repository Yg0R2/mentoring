<#-- @ftlroot "../.." -->
<#import "/spring.ftl" as spring>
<#import "/ftl/book/book_macro.ftl" as bookMacro>

<#macro displayAuthor author, displayBooks=true>
    <fieldset name="author" class="author">
        <legend>${author.getFirstName()}, ${author.getLastName()}</legend>

        <div>
            Author additional data. // TODO: Add more author data
        </div>

        <#if displayBooks>
            <legend>Books:</legend>

            <#list author.getBooks()![] as book>
                <@bookMacro.displayBook book, false />
            </#list>
        </#if>
    </fieldset>
</#macro>

<#macro displayAuthorsSelect authors, bindPath>
    <@spring.bind bindPath />

    <select multiple id="${spring.status.expression}" name="${spring.status.expression}">
        <#list authors as author>
            <option value="${author.getId()}">${author.getFirstName()}, ${author.getLastName()}</option>
        </#list>
    </select>
</#macro>
