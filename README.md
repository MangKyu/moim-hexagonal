# 모임

## 기능 목록
- 회원 가입
  - [x] 회원가입 시에 제출한 비밀번호가 비밀번호 정책에 부합하는지 검사함
  - [x] 비밀번호 정책은 문자 + 숫자 + 특수문자 + 7자리 이상에 해당함
  - [x] 모임 주체자로 회원가입을 함
  - [x] 모임 참여자로 회원가입을 함
- 로그인
  - [x] 유효한 아이디와 비밀번호를 제출하면 인증 토큰을 발급받음
  - [x] 유효하지 않은 아이디와 비밀번호 제출 시에 에러 응답
  - [] 토큰에 사용자 역할 넣어서 API 인가 처리
  - [] Refresh 토큰 처리
- 
- 회원 정보 조회
  - [ ] 유효한 인증 토큰으로 회원 가입 시에 제출한 정보를 조회할 수 있음
  - [ ] 정보 조회 시에 비밀번호는 노출되지 않아야 함
  - [ ] 모임 주최자이면서 참여자인 경우 추가 데이터 노출
  - [ ] 모임 참여자이면서 주최자인 경우 추가 데이터 노출
  - [ ] 유효하지 않은 인증 토큰으로 정보 조회 시에 에러 응답
- 회원 정보 수정
  - [x] 회원 정보 수정 시에 제출한 비밀번호가 비밀번호 정책에 부합하는지 검사함
  - [x] 유효한 인증 토큰으로 회원 가입 시에 제출한 정보를 수정할 수 있음
  - [x] 유효하지 않은 인증 토큰으로 정보 수정 시에 에러 응답
- 추가 활동하기
  - [x] 모임 주최자는 모임 참여자로도 활동할 수 있으며 추가 정보 제출이 필요함
  - [x] 모임 참여자는 모임 주최자로도 활동할 수 있으며 추가 정보 제출이 필요함
  - [x] 유효하지 않은 인증 토큰으로 정보 수정 시에 에러 응답