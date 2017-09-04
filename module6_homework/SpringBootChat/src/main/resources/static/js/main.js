'use strict';

var chatPage = document.querySelector('#chat-page');

var connectingElement = document.querySelector('.connecting');

var messageArea = document.querySelector('#messageArea');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');

var userNameForm = document.querySelector('#userNameForm');
var userNameInput = document.querySelector('#name');
var userNamePage = document.querySelector('#userNamePage');

var stompClient = null;

var userName = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    event.preventDefault();

    userName = userNameInput.value.trim();

    if (!userName) {
        return;
    }

    userNamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/channel/public', onMessageReceived);

    stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: userName, type: 'JOIN'}));

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    event.preventDefault();

    var messageContent = messageInput.value.trim();

    if (!messageContent || !stompClient) {
        return;
    }

    var chatMessage = {
        sender: userName,
        content: messageInput.value,
        type: 'CHAT'
    };

    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

    messageInput.value = '';
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var newMessageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        newMessageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    }
    else if (message.type === 'LEAVE') {
        newMessageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    }
    else {
        newMessageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);

        avatarElement.appendChild(avatarText);
        avatarElement.style['backround-color'] = getAvatarColor(message.sender);

        newMessageElement.appendChild(avatarElement);

        var userNameElement = document.createElement('span');
        var userNameText = document.createTextNode(message.sender);

        userNameElement.appendChild(userNameText);

        newMessageElement.appendChild(userNameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);

    textElement.appendChild(messageText);

    newMessageElement.appendChild(textElement);

    messageArea.appendChild(newMessageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);

    return colors[index];
}

userNameForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);
