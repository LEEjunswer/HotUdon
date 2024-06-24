function login(event) {
    event.preventDefault();
    const loginId = document.getElementById('loginId').value;
    const password = document.getElementById('password').value;
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'loginId': loginId,
            'password': password
        })
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(result => {
                    document.getElementById('error-message').textContent = result.message;
                    throw new Error(result.message);
                });
            }
            return response.text();
        })
        .then(data => {
            alert("로그인 성공");
            window.location.href = '/home';
        })
        .catch(error => {
            alert(error.message);
        });
}