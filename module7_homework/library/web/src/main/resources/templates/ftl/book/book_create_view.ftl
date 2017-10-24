<#-- @ftlroot "../.." -->
<#import "/ftl/common_macro.ftl" as commonMacro>
<#import "/ftl/author/author_macro.ftl" as authorMacro>

<@commonMacro.backToHome />

<@commonMacro.displayErrorsIfPresent errors![] />

<form id="create-book-form" method="POST" modelAttribute="createBookForm" action="/create-book" enctype="application/json">
    <fieldset>
        <legend>Create new book</legend>

        <ul>
            <li>
                <label>Title:</label>
                <input name="title" required value="">
            </li>

            <li>
                <label>Author(s):</label>
                <@authorMacro.displayAuthorsMultiSelect createBookForm.getAuthors()![] "createBookForm.authors" />
            </li>
        </ul>
    </fieldset>

    <input type="submit" value="Submit">
</form>
