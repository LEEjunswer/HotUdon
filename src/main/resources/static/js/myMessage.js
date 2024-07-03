let stompClient = null;
let chatRoomId = null;
let currentUserId = null;
let receiverId = null;
function chatClickList(register) {
    if (!document.getElementById('dummyMemberId')) {
        alert("로그인 후 메시지 전송이 가능합니다.");
        return false;
    }

    const memberId = document.getElementById('dummyMemberId').getAttribute('data-value');

    fetch(`/api/v1/chat?memberId=${memberId}&registerId=${register}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(chatRoom => {
            chatRoomId = chatRoom.chatRoom.id;
            currentUserId = memberId;
            connectToChatRoom(chatRoomId);
            openModal();
        })
        .catch(error => {
            console.log(error);
        });
}

function connectToChatRoom(id) {
    const socket = new SockJS('/ws/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("연결체크: " + frame);
            stompClient.subscribe('/topic/chat/' + id, function (message) {
            const messageData = JSON.parse(message.body);
            console.log(messageData+ "messsageDate");
            receiverId = messageData.receiverId;
            showMessage(messageData.text, messageData.senderId, messageData.receiverId);
        });
         fetchMessages()
    });
}
function sendMessage(receiverId) {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value;
    if(message.length === 0){
        alert("공백 메시지를 보내실 수 없습니다");
        return false;
    }
    messageInput.value = '';
    const messageData = {
        text: message,
        senderId: currentUserId,
        receiverId: receiverId, // 서버에서 설정됨
        chatRoomId: chatRoomId
    };
    fetch(`/api/v1/${chatRoomId}/chat`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(messageData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.msg) {
                console.log(data.msg);
            }
            // 메시지 화면에 추가
            showMessage(messageData.text, messageData.senderId, messageData.receiverId);
        })
        .catch(error => console.error('Error:', error));
}

function fetchMessages() {
    fetch(`/api/v1/chat/${chatRoomId}/messages`)
        .then(response => response.json())
        .then(data => {
            const messageContainer = document.getElementById('message-container');
            messageContainer.innerHTML = '';
            data.forEach(message => {
                showMessage(message.text, message.senderId, message.receiverId);
            });
        })
        .catch(error => console.error('Error:', error));
}
/*메시지창에서 닉네임이나 이런거 끌 고 올 수 있으면 넣어보자*/
function showMessage(text, senderId, receiverId) {
    const messageContainer = document.getElementById('message-container');
    const messageElement = document.createElement('div');
    const isSender = senderId === currentUserId;
    console.log(senderId + " = = " +currentUserId)
    const isReceiver = receiverId === currentUserId;
    console.log(receiverId + " = = " +currentUserId)
    console.log("Show message: ", text, senderId, receiverId);
    messageElement.classList.add('chat');
    messageElement.classList.add(isSender ? 'chat-end' : 'chat-start');

    messageElement.innerHTML = `
                <div class="chat-image avatar">
                    <div class="w-10 rounded-full">
                        <img alt="Avatar" src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg" />
                    </div>
                </div>
                <div class="chat-header">
                    ${isSender ? 'You' : '판매자'}
                    <time class="text-xs opacity-50">${new Date().toLocaleTimeString()}</time>
                </div>
                <div class="chat-bubble">${text}</div>
                <div class="chat-footer opacity-50">${isSender ? '전송됨' : '읽음'}</div>
            `;
    messageContainer.appendChild(messageElement);
    messageContainer.scrollTop = messageContainer.scrollHeight;
}


function openModal() {
    document.getElementById('chatModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('chatModal').style.display = 'none';
}