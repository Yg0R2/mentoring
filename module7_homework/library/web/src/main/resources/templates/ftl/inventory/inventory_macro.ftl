<#-- @ftlroot "../.." -->
<#import "/ftl/user/user_macro.ftl" as userMacro>

<#macro displayInventory inventory>
    <fieldset name="inventory" class="inventory">
        <legend>${inventory.getId()}</legend>

        <ul>
            <li>Book title: ${inventory.getBook().getTitle()}</li>
            <li>Available copies: ${inventory.getAvailableCopiesNumber()}</li>
        </ul>
    </fieldset>
</#macro>
