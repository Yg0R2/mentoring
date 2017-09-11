'use strict';

var userNamePageElement = document.querySelector('#userNamePage');
var chatPageElement = document.querySelector('#chatPage')

var userNameForm = document.querySelector('#userNameForm');
var userNameInput = document.querySelector('#name');
var userName = null;

var wsUri = "ws://" + document.location.host + getContextPath() + "/ws";
var webSocket = new WebSocket(wsUri);

function submitLogin(event) {
    event.preventDefault();

    userName = userNameInput.value.trim();
    if (!userName) {
        return;
    }

    userNamePageElement.classList.add('hidden');
    chatPageElement.classList.remove('hidden');

    var chatMessage = {
        dateTimeValue: new Date().toLocaleString(),
        sender: userName,
        type: 'JOIN'
    };

    webSocket.send(JSON.stringify(chatMessage));

    connectingElement.classList.add('hidden');
};

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.indexOf("/", 1) || pathName.length;

    return pathName.substring(0, index);
}

userNameForm.addEventListener('submit', submitLogin, true);
