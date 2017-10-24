<#-- @ftlroot "../.." -->
<#import "/spring.ftl" as spring>
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/borrow/borrow_macro.ftl" as borrowMacro>

<@commonMacro.backToHome />

<@spring.bind "borrow" />
<form method="POST" action="/borrow-update" modelAttribute="updateBorrowForm">
    <input name="id" value="${borrow.getId()}" hidden>

    <legend>${borrow.getId()}</legend>

    <ul>
        <li>Book title: ${borrow.getBook().getTitle()}</li>
        <li>User Borrowed: ${borrow.getUserBorrowed().getFirstName()}, ${borrow.getUserBorrowed().getLastName()}</li>
        <li>
            <label>Return date</label>
            <input name="returnDate" type="date" value="${borrow.getReturnDate()?date}">
        </li>
        <li>
            <label>Returned</label>
            <input name="returned" type="checkbox" <#if borrow.isReturned()>checked</#if>>
        </li>
    </ul>

    <input type="submit" value="Submit">
</form>
