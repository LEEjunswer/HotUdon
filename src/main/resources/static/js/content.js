
function addToWishlist(event,productId) {

    if (!document.getElementById('dummyMember')) {
        alert("로그인 이후에 찜 등록이 가능합니다.");
        return false;
    }
    var heartPath = event.target.closest('.icon-tabler-heart').querySelector('.heart-path');
    var isDibbed = heartPath.classList.contains('heart-check');

    var url = isDibbed
        ? `/notification-api/undibs?registerId=${productId}`
        : `/notification-api/dibs?registerId=${productId}`;

    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(response=>response.json())
        .then(data=>{
            if(data.msg){
                heartPath.classList.toggle('heart-check');
                alert(data.msg);
                return;
            }
            alert(data.fail);

        })
        .catch(error=>{
            console.log("오류발생",error)
        })

}
let stompClient = null;
let chatRoomId = null;
let currentUserId = null;

function chatClick() {
    if (!document.getElementById('dummyMemberId')) {
        alert("로그인 후 메시지 전송이 가능합니다.");
        return false;
    }
    const memberId = document.getElementById('dummyMemberId').getAttribute('data-value');
    const registerId = document.getElementById('dummyRegisterId').getAttribute('data-value');

    fetch(`/api/v1/chat?memberId=${memberId}&registerId=${registerId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("채팅방을 생성하지 못했습니다.");
            }
            return response.json();
        })
        .then(chatRoom => {
            console.log("chatRoom: " + JSON.stringify(chatRoom.chatRoom.id));
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
            showMessage(messageData.text, messageData.senderId, messageData.receiverId);
        });
    });
}
function sendMessage() {
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
        receiverId: null, // 서버에서 설정됨
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
            data.messages.forEach(message => {
                showMessage(message.text, message.senderId, message.receiverId);
            });
        })
        .catch(error => console.error('Error:', error));
}

function showMessage(text, senderId, receiverId) {
    const messageContainer = document.getElementById('message-container');
    const messageElement = document.createElement('div');
    const isSender = senderId === currentUserId;

    messageElement.classList.add('chat');
    messageElement.classList.add(isSender ? 'chat-end' : 'chat-start');

    messageElement.innerHTML = `
                <div class="chat-image avatar">
                    <div class="w-10 rounded-full">
                        <img alt="Avatar" src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg" />
                    </div>
                </div>
                <div class="chat-header">
                    ${isSender ? 'You' : '상대방'}
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
