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

<#macro displayAuthorsSelect authors, bindPath, multiple=false>
    <@spring.bind bindPath />

    <select <#if multiple>multiple</#if> id="${spring.status.expression}" name="${spring.status.expression}">
        <#if !multiple>
            <option value="" selected>Not selected</option>
        </#if>

        <#list authors as author>
            <option value="${author.getId()}">${author.getFirstName()}, ${author.getLastName()}</option>
        </#list>
    </select>
</#macro>
