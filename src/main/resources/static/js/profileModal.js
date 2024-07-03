const profile = document.getElementById("profileButton");
const myModal = document.getElementById("myModal");
const modalClose = document.getElementById("modalBtn-close");
const profileUpload = document.getElementById("profile_upload");
const profileSubmit= document.getElementById("photo_submit");
const  modalCloseTwo =document.getElementById("photo_reset");
profile.addEventListener("click", ()=>{
    myModal.style.display="block";
    myModal.style.opacity="1";
    profileUpload.files[0] = null;
    disableClickOutside();
})


modalClose.addEventListener("click",() =>{
    myModal.style.display="none";
    myModal.style.opacity="0";
    profileUpload.files[0] = null;
    enableClickOutside();
})
modalCloseTwo.addEventListener("click" , ()=>{
    myModal.style.display='none';
    myModal.style.opacity='0';
    profileUpload.files[0] = null;
    enableClickOutside();
})

profileUpload.onchange =function (){
    loadFile(this);
}

function loadFile(input) {
    const file = input.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            if( document.getElementById('checkImgOne')) {
                document.getElementById('checkImgOne').src = e.target.result;
            }
            if(document.getElementById('checkImgTwo')){
                document.getElementById('checkImgTwo').src = e.target.result;
            }
        }
        reader.readAsDataURL(file);
    }

}

// 다른창 클릭 막기
function disableClickOutside() {
    document.querySelectorAll('body > *:not(#myModal)').forEach(function (element) {
        element.style.pointerEvents = "none";
    });
    myModal.style.pointerEvents = "auto";
}

function enableClickOutside() {
    document.querySelectorAll('body > *:not(#myModal)').forEach(function (element) {
        element.style.pointerEvents = "auto";
    });
}
profileSubmit.addEventListener("click",()=> {
    const file = profileUpload.files[0];
    if (file) {
        const formData = new FormData();
        formData.append('profileImage', file);
        fetch('/updateProfile', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                myModal.style.display = "none";
                myModal.style.opacity = "0";
                enableClickOutside();
                alert("성공적으로 프로필 변경이 완료되었습니다.");
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        alert('파일을 선택하세요.');
    }

});
