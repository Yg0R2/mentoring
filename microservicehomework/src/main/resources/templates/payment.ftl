<fieldset name="payment" class="payment">
    <legend>Payment</legend>
    <div class="field">
        <label>Amount:</label>
        <input name="payment.amount" type="text" value="${request.payment.amount}">
    </div>
    <div class="field">
        <label>Currency:</label>
        <input name="payment.currency" type="text" value="${request.payment.currency}">
    </div>
</fieldset>
