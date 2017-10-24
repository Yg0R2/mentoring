<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/book/book_macro.ftl" as bookMacro>

<@commonMacro.backToHome />

<@commonMacro.displayErrorsIfPresent errors![] />

<form id="create-inventory-form" method="POST" modelAttribute="createInventoryForm" action="/create-inventory" enctype="application/json">
    <fieldset>
        <legend>Create new inventory</legend>

        <ul>
            <li>
                <label>Book:</label>
                <@bookMacro.displayBooksSingleSelect availableBooks, "createInventoryForm.book" />
            </li>
            <li>
                <label>Available copies:</label>
                <input name="availableCopiesNumber" value="0" />
            </li>
        </ul>
    </fieldset>

    <input type="submit" value="Submit">
</form>
