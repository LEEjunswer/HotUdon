function logout(){
    fetch("/logout", {
        method: "POST"
    })
        .then(response => {
            if (response.ok) {
                alert("로그아웃 완료");
                location.href = "/home";
            } else {
                alert("로그아웃 실패");
            }
        })
        .catch(error => {
            alert("error");
        });
}