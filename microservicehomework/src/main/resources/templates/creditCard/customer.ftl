<fieldset name="customer" class="customer">
    <legend>Customer</legend>
    <div class="field">
        <label>Address:</label>
        <input name="customer.address" type="text" value="${request.customer.address}">
    </div>
    <div class="field">
        <label>Date of birth:</label>
        <input name="customer.dateOfBirth" type="text" value="${request.customer.dateOfBirth}">
    </div>
    <div class="field">
        <label>Email address:</label>
        <input name="customer.emailAddress" type="email" value="${request.customer.emailAddress}">
    </div>
    <div class="field">
        <label>First name:</label>
        <input name="customer.firstName" type="text" value="${request.customer.firstName}">
    </div>
    <div class="field">
        <label>Last name:</label>
        <input name="customer.lastName" type="text" value="${request.customer.lastName}">
    </div>
</fieldset>
