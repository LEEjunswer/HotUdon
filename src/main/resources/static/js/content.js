
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