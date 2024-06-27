# HotUdon

프로젝트에 key.properties 를 만들고 
DB_USERNAME= sql아이디
DB_PASSWORD= sql 비밀번호

KAKAO_CLIENT_ID=카카오
KAKAO_CLIENT_SECRET=카카오시크릿

NAVER_CLIENT_ID=네이버
NAVER_CLIENT_SECRET=네이버시크릿

GOOGLE_CLIENT_ID=구글
GOOGLE_CLIENT_SECRET=구글시크릿

UPLOAD_PATH=상품이미지 업로드 위치
UPLOAD_PROFILE=멤버 프로필 업로드 위치

![keyProperties](https://github.com/LEEjunswer/HotUdon/assets/126369781/f20b4159-58ad-4b5d-b3cb-d2c588bfbf91)


#Oauth2를 활용한 사용자 인증:

OAuth2 서버를 설정하여 사용자 인증과 토큰 발급을 관리합니다.
사용자 로그인 시 OAuth2 서버로 인증 요청을 보내고, 사용자가 승인을 하면 인증 코드를 받아 액세스 토큰을 발급받습니다.
이 액세스 토큰을 사용하여 보호된 리소스에 접근합니다.
Interceptor를 활용한 요청 가로채기 및 처리:

모든 HTTP 요청에서 액세스 토큰을 검사하는 Interceptor를 설정했습니다.
요청 헤더에 포함된 액세스 토큰의 유효성을 검사하고, 유효하지 않은 경우 요청을 차단합니다.
유효한 토큰의 경우, 토큰에서 사용자 정보를 추출하여 컨텍스트에 저장합니다.
PassEncoder를 활용한 비밀번호 암호화:

사용자 등록 시 입력된 비밀번호를 암호화하여 저장합니다.
로그인 시, 입력된 비밀번호를 암호화된 비밀번호와 비교하여 인증합니다.
네이버, 카카오, 구글 간편 로그인:

Oauth2를 활용하여 네이버 간편 로그인, 카카오 로그인, 구글 로그인을 구현하였습니다.
사용자는 각 플랫폼의 인증 절차를 통해 간편하게 로그인할 수 있습니다.
이를 통해 다양한 로그인 옵션을 제공하여 사용자 편의성을 높였습니다.
간편 로그인 구현
네이버 간편 로그인: 네이버 OAuth2 인증을 통해 네이버 계정으로 로그인할 수 있습니다.
카카오 로그인: 카카오 OAuth2 인증을 통해 카카오 계정으로 로그인할 수 있습니다.
구글 로그인: 구글 OAuth2 인증을 통해 구글 계정으로 로그인할 수 있습니다.
