let selectElement = document.getElementById("selectEmail");
let emailInput = document.getElementById("emailInput");
let emailDomain = document.getElementById('emailDomain');
let emailId = document.getElementById('emailId');
const submitButton = document.getElementById('join_submit');
let passwordStyle = document.getElementById('password');
let passwordConfirmStyle= document.getElementById('passwordCheck');
const passwordResult = document.getElementById('passwordResult');
let idCheck = 0;
let nickNameCheck = 0;
let passwordCheck = 0;

function checkPw(){
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("passwordCheck").value;

    if(password === confirmPassword){
        passwordCheck = 1;
        passwordStyle.style.borderColor = 'blue';
        passwordResult.style.color = 'blue';
        passwordResult.innerHTML="비밀번호 확인";
        passwordConfirmStyle.style.borderColor = 'blue';
    }else{
        passwordCheck = 0;
        passwordStyle.style.borderColor = 'red';
        passwordResult.innerHTML = '비밀번호가 틀립니다,';
        passwordResult.style.color = 'red';
        passwordConfirmStyle.style.borderColor = 'red';
    }

}

function nickValidCheck(){
    event.preventDefault();
    let nickname = document.getElementById("nickname").value;
    fetch("/joinFormNick?nickname=" +nickname , {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data === "suc") {
                nickNameCheck =1;
                alert("사용 가능한 닉네임입니다.")

            } else {
                nickNameCheck = 0;
                alert("이미 사용중인 닉네임입니다")
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
        });


}

// 이메일 주소의 유효성을 검사하는 정규 표현식
function isValidEmail(email) {
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}

function  idValidCheck() {
    event.preventDefault();
    const loginId = document.getElementById("loginId").value;
    const koreanPattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    const spacePattern = /\s/;
    if(koreanPattern.test(loginId) || spacePattern.test(loginId) ){
        alert("아이디는 한글을 포함 또는 공백을 사용하실 수 없습니다.");
        return;
    }
    if (loginId.length < 6 || loginId.length > 20) {
        alert("아이디는 6글자 이상 20글자 이하로 설정해야 합니다.");
        return;
    }

    fetch("/joinFormId?loginId=" +loginId, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
               console.log(data.status);
            if (data.status ==="suc") {
                idCheck =1;
                alert("사용 가능한 아이디입니다.")

            } else {
                idCheck = 0;
                alert("이미 사용중인 아이디입니다")
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
        });
}

function emailSelect() {
    if (selectElement.value.trim() === "") {
        emailDomain.style.display = 'inline';
        emailDomain.readOnly = false;
        emailDomain.value = "";
    } else {
        emailDomain.style.display = 'none';
        emailDomain.readOnly = true;
        emailDomain.value = selectElement.value;

    }
}
function formSubmitCheck(form) {
    submitButton.disabled = true;
    form.email.value = emailId.value + "@" + emailDomain.value;
    let emailCheck = form.email.value;
    if (!form.loginId.value.trim()) {
        alert("아이디를 입력해주세요");
        form.loginId.focus();
        submitButton.disabled = false;
        return;
    } else if (!form.password.value.trim()) {
        alert("비밀번호를 입력헤주세요")
        form.pw.focus();
        submitButton.disabled = false;
        return;
    } else if (!form.name.value.trim()) {
        submitButton.disabled = false;
        alert("이름을 입력해주세요")
        form.name.focus();
        return;
    } else if (!form.nickname.value.trim()) {
        alert("별명을 입력해주세요");
        form.nickname.focus();
        submitButton.disabled = false;
        return;
    } else if (!form.email.value.trim()) {
        alert("이메일을 입력해주세요");
        form.email.focus();
        submitButton.disabled = false;
        return;
    }
    if (!form.postcode.value.trim() || !form.address.value.trim() || !form.addressDetail.value.trim()) {
        alert("주소를 입력해주세요");
        form.postcode.focus();
        return;
    } else if (passwordCheck === 0) {
        alert("비밀번호확인을 입력해주세요");
        submitButton.disabled = false;
        return;
    } else if (!isValidEmail(emailCheck)) {
        alert("이메일 형식이 아닙니다. 다시 확인해주세요");
        submitButton.disabled = false;
        return;
    }
    /* emailCheck ===0  나중에 이메일 중보게크랑 인증번호 보내고 할 예정 */
    else if (idCheck === 0 || nickNameCheck === 0) {
        alert("아이디 or 닉네임 중복체크를 해주세요");
        submitButton.disabled = false;
        return;
    }
    let nickValue = document.getElementById("nickname").value;
    alert(nickValue + "님 회원가입을 축하합니다.");
    form.submit();
}
    var element_layer = document.getElementById('layer');

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function daumAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
         /*       if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;

            } else {
                document.getElementById("extraAddress").value = '';
            }*/
        }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_layer.style.display = 'none';
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';

    // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
function initLayerPosition(){
    var width = 300; //우편번호서비스가 들어갈 element의 width
    var height = 400; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}