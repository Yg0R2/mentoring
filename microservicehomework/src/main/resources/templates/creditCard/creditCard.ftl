<fieldset name="creditCard" class="credit-card">
    <legend>Credit Card</legend>
    <div class="field">
        <label>number:</label>
        <input name="creditCard.creditCardNumber" type="number" value="${request.creditCard.creditCardNumber}">
    </div>
    <div class="field">
        <label>cvc:</label>
        <input name="creditCard.cvc" type="number" value="${request.creditCard.cvc}">
    </div>
    <br />
    <fieldset name="expirationDate" class="expiration-date field">
        <legend>Expiration date</legend>
        <div class="field">
            <label>month:</label>
            <input name="creditCard.expirationDate.month" type="number" value="${request.creditCard.expirationDate.month}">
        </div>
        <div class="field">
            <label>year:</label>
            <input name="creditCard.expirationDate.year" type="number" value="${request.creditCard.expirationDate.year}">
        </div>
    </fieldset>
</fieldset>
