
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

function chatClick() {
    if(!document.getElementById('dummyMemberId')){
        alert("로그인 후 메시지 전송이 가능합니다.")
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
            chatRoomId = chatRoom.id;
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
            showMessage(messageData.text, messageData.sender, messageData.role);
        });
    });
}

function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value;
    messageInput.value = '';
    if (stompClient && chatRoomId) {
        const messageData = {
            text: message,
            sender: currentUser.name,
            role: currentUser.role
        };
        stompClient.send(`/app/chat/${chatRoomId}`, {}, JSON.stringify(messageData));
        showMessage(messageData.text, messageData.sender, messageData.role);
    }
}
function getRelativeTime(date) {
    const now = new Date();
    const diff = now - date;
    const diffInMinutes = Math.floor(diff / 60000);
    const diffInHours = Math.floor(diff / 3600000);
    const diffInDays = Math.floor(diff / 86400000);

    if (diffInMinutes < 1) {
        return '방금 전';
    } else if (diffInMinutes < 60) {
        return `${diffInMinutes}분 전`;
    } else if (diffInHours < 24) {
        return `${diffInHours}시간 전`;
    } else {
        return `${diffInDays}일 전`;
    }
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