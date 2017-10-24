<#-- @ftlroot "../.." -->
<#import "/ftl/user/user_macro.ftl" as userMacro>

<#macro displayBorrow borrow>
    <fieldset name="borrow" class="borrow">
        <legend>${borrow.getId()}</legend>

        <ul>
            <li>Book title: ${borrow.getBook().getTitle()}</li>
            <li>User Borrowed: ${borrow.getUserBorrowed().getFirstName()}, ${borrow.getUserBorrowed().getLastName()}</li>
            <li>Return date: ${borrow.getReturnDate()?date}</li>
            <li>Returned: ${(!borrow.isOngoing())?then("yes", "no")}</li>
        </ul>
    </fieldset>
</#macro>
