'use strict';

var connectingElement = document.querySelector('.connecting');
var errorElement = document.querySelector('.error');

var messageArea = document.querySelector('#messageArea');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

window.onbeforeunload = onClose;
webSocket.onclose = onClose;
function onClose() {
    var chatMessage = {
        dateTimeValue: new Date().toLocaleString(),
        sender: userName,
        type: 'LEAVE'
    };

    webSocket.send(JSON.stringify(chatMessage));
};

webSocket.onerror = function(event) {
    event.preventDefault();

    connectingElement.classList.add('hidden');
    errorElement.classList.remove('hidden');
};

webSocket.onmessage = function(chatMessage) {
    var message = JSON.parse(chatMessage.data);

    var newMessageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        newMessageElement.classList.add('eventMessage');
        message.content = message.sender + ' joined!';
    }
    else if (message.type === 'LEAVE') {
        newMessageElement.classList.add('eventMessage');
        message.content = message.sender + ' left!';
    }
    else {
        newMessageElement.classList.add('chatMessage');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);

        avatarElement.appendChild(avatarText);
        avatarElement.style.backgroundColor = getAvatarColor(message.sender);

        newMessageElement.appendChild(avatarElement);

        var userNameElement = document.createElement('span');
        var userNameText = document.createTextNode(message.sender);

        userNameElement.appendChild(userNameText);

        newMessageElement.appendChild(userNameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode("(" + message.dateTimeValue + "): " + message.content);

    textElement.appendChild(messageText);

    newMessageElement.appendChild(textElement);

    messageArea.appendChild(newMessageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
};

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);

    return colors[index];
};

function sendMessage(event) {
    event.preventDefault();

    var messageContent = messageInput.value.trim();

    if (!messageContent) {
        return;
    }

    var chatMessage = {
        dateTimeValue: new Date().toLocaleString(),
        sender: userName,
        content: messageInput.value,
        type: 'CHAT'
    };

    webSocket.send(JSON.stringify(chatMessage));

    messageInput.value = '';
};

messageForm.addEventListener('submit', sendMessage, true);
