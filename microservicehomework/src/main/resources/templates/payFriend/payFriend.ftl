<fieldset name="${payFriendModelName}" class="pay-friend">
    <legend>${payFriendTitle}</legend>
    <div class="field">
        <label>email:</label>
        <input name="${payFriendModelName}.email" type="email" value="${payFriend.email}">
    </div>
    <div class="field">
        <label>name:</label>
        <input name="${payFriendModelName}.name" type="text" value="${payFriend.name}">
    </div>
    <div class="field">
        <label>userId:</label>
        <input name="${payFriendModelName}.userId" type="text" value="${payFriend.userId}">
    </div>
</fieldset>
