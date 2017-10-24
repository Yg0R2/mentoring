<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/book/book_macro.ftl" as bookMacro>
<#import "/ftl/user/user_macro.ftl" as userMacro>

<@commonMacro.backToHome />

<@commonMacro.displayErrorsIfPresent errors![] />

<form id="create-borrow-form" method="POST" modelAttribute="createBorrowForm" action="/create-borrow" enctype="application/json">
    <fieldset>
        <legend>Create new borrow</legend>

        <ul>
            <li>
                <label>Which book:</label><br >
                <@bookMacro.displayBooksSelect books, "createBorrowForm.book" />
            </li>

            <li>
                <label>Who want's to borrow</label>
                <@userMacro.displayUsersSelect users, "createBorrowForm.userBorrowed", true />
            </li>

            <li>
                <label>Borrow expiration date:</label>
                <input name="returnDate" type="date">
            </li>
        </ul>

    </fieldset>

    <input type="submit" value="Submit">
</form>
