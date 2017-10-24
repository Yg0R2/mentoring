<#-- @ftlroot "../.." -->
<#import "/ftl/user/user_macro.ftl" as userMacro>

<#macro displayInventory inventory>
    <fieldset name="inventory" class="inventory">
        <legend>${inventory.getId()}</legend>

        <ul>
            <li>Book title: ${inventory.getBook().getTitle()}</li>
            <li>Available copies: ${inventory.getAvailableCopiesNumber()}</li>
            <li>
                <fieldset class="users">
                    <legend>Users borrowed a copy of this book:</legend>

                    <#list inventory.getUsersBorrowed()![] as user>
                        <@userMacro.displayUser user />
                    </#list>
                </fieldset>
            </li>
            <li>
                <fieldset class="users">
                    <legend>Users waiting for this book:</legend>

                    <#list inventory.getRequestedForBorrow()![] as user>
                        <@userMacro.displayUser user />
                    </#list>
                </fieldset>
            </li>
        </ul>
    </fieldset>
</#macro>
