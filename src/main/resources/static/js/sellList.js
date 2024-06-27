function stopPropagation(event) {
    event.stopPropagation();
}


function addToWishlist(event) {
    stopPropagation(event);
    if (!document.getElementById('dummyMember')) {
        alert("로그인 이후에 찜 등록이 가능합니다.");
        return false;
    }
    const member = document.getElementById('dummyMember').getAttribute('data-value');

}