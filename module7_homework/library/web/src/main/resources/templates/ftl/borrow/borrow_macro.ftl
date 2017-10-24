<#-- @ftlroot "../.." -->
<#import "/ftl/user/user_macro.ftl" as userMacro>

<#macro displayBorrow borrow>
    <fieldset name="borrow" class="borrow">
        <legend>${borrow.getId()}</legend>

        <ul>
            <li>Book title: ${borrow.getBook().getTitle()}</li>
            <li>User Borrowed: ${borrow.getUserBorrowed().getFirstName()}, ${borrow.getUserBorrowed().getLastName()}</li>
            <li>Return date: ${borrow.getReturnDate()?date}</li>
            <li>Returned: ${borrow.isReturned()?then("yes", "no")}</li>
            <li>
                <form method="POST" action="/borrow-edit?id=${borrow.getId()}">
                    <input type="submit" value="Edit">
                </form>
            </li>
        </ul>
    </fieldset>
</#macro>
