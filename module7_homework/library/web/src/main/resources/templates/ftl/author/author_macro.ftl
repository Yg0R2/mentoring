<#import "/spring.ftl" as spring>

<#macro displayAuthor author>
    <fieldset name="author" class="author">
        <legend>${author.getFirstName()}, ${author.getLastName()}</legend>

        <span>
            Author additional data. // TODO: Add more author data
        </span>
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
