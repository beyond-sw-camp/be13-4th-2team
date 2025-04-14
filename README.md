# be13-4th-2team

# BUCAMS 


# 1. 팀원 소개
|<img src="https://avatars.githubusercontent.com/" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|:-:|
|임현조<br/>[@limhyunjo](https://github.com/limhyunjo)|홍도현<br/>[@dh0522](https://github.com/dh0522)|이창신<br/>[@always0702](https://github.com/always0702)|차경태<br/>[@ChaGyoungtae](https://github.com/ChaGyoungtae)|이덕찬<br/>[@deokChan2](https://github.com/deokChan2)|



--- 

# 2. 기술 스택

### BackEnd
<div>
  <img src="https://img.shields.io/badge/Java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT"> <br>
  
  <img src="https://img.shields.io/badge/Spring%20Boot-%236DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-%236DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/querydsl-0769AD?style=for-the-badge&logo=&logoColor=white">
</div>

### DataBase
<div>
  <img src="https://img.shields.io/badge/MariaDB-%23003545?style=for-the-badge&logo=mariadb&logoColor=white">
</div>


### Frontend

![Vue.js](https://img.shields.io/badge/vue.js-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)


### Devops
<div>
  <img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white">
  <img src="https://img.shields.io/badge/jenkins-%23d24939.svg?style=for-the-badge&logo=jenkins&logoColor=white">
  <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">   
</div>


### Tools
<div>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  <img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" alt="GitHub">  
</div>


---

# 3. 프로젝트 소개
 
* 관리자가 학생과 교수의 회원가입을 처리할 수 있습니다.

* 교수는 강의를 개설할 수 있습니다.
<br>

* 학생은 원하는 강의를 수강신청할 수 있습니다.
<br> 
<br>
<br>

## 🌞 프로젝트 개요 및 기대효과

### 개요
학생과 교수의 수강 및 강의 관리를 웹 기반으로 제공하는 통합 학사 시스템입니다.  
관리자가 사용자(학생/교수)를 등록하고 수강 신청 기간을 설정하며, 교수는 강의를 개설하고 학생은 해당 강의를 수강할 수 있습니다. 

<br>

### 기대효과

강의 개설부터 수강신청까지 전 과정을 자동화하여 행정업무 부담을 감소하고, 실시간 시간표 확인 및 중복 체크 기능을 통해 오류 없는 수강신청이 가능하며 관리자, 교수, 학생 간의 권한을 구분하여 역할 기반 시스템으로 운영이 가능합니다.

---

# 4. Backend

<details>
<summary>ERD</summary>

[ERD 링크](https://www.erdcloud.com/d/4My3utLqTpCkGicuG)

<details>
<summary>ERD 이미지</summary>

<img src="https://github.com/user-attachments/assets/1988c852-3a6a-461f-b450-76c3bb6f2377" alt="ERD 이미지" />

</details>
</details>


<details>
<summary> 테이블 정의 명세서 </summary>

  
  [명세서 링크](https://docs.google.com/spreadsheets/d/1osH4UXCV0nqnQZ1nxgPClkloW2cRYWT-h2eqFcWtyrY/edit?gid=0#gid=0) 

  <details>
<summary>테이블 정의 명세서 이미지</summary>

<img src="https://github.com/user-attachments/assets/b22f0240-b36d-4253-9b17-77a8975e2dbb"/>

</details>

</details>


<details>
<summary> 요구사항 명세서 </summary>

 <details>
   <summary> 1. 멤버  </summary>

   <img src="https://github.com/user-attachments/assets/eb659e14-b409-4627-b5d6-2bbbf58c6fd5"/>

 </details>
  <details>
   <summary> 2. 강의  </summary>

   <img src="https://github.com/user-attachments/assets/67111f71-73f7-4068-8f00-8640769364cb"/>
 </details>
  <details>
   <summary> 3. 학과  </summary>

   <img src="https://github.com/user-attachments/assets/743b7d26-f2c5-4231-a8cc-e036d3025784"/>
 </details>
  <details>
   <summary> 4. 수강한 강의  </summary>
    <img src="https://github.com/user-attachments/assets/d79b2cac-d02b-4da9-8fc6-c445c4430197"/>
 </details>
</details>

<details>
<summary> API 명세서 </summary>
 <details>
   <summary> 1. 멤버  </summary>

   <img src="https://github.com/user-attachments/assets/1412e3ac-a8b2-4631-88f1-276b1d5475b3"/>

 </details>
  <details>
   <summary> 2. 강의  </summary>

   <img src="https://github.com/user-attachments/assets/06575689-3265-46be-9ba0-f2dfc70c1e62"/>
 </details>
  <details>
   <summary> 3. 학과  </summary>

   <img src="https://github.com/user-attachments/assets/cb0e0e19-ea5c-4886-9e0b-2290452dc414"/>
 </details>
  <details>
   <summary> 4. 수강신청 내역  </summary>
    <img src="https://github.com/user-attachments/assets/e12a3adf-b5e8-40d8-bfd2-7532372e6d13"/>
 </details>

   <details>
   <summary> 5. 수강신청 기간  </summary>
    <img src="https://github.com/user-attachments/assets/6a95eb86-b719-4b40-83ab-5c3ee165b837"/>
 </details>
</details>

<details>
<summary> 세부 기능 테스트 </summary>

</details>

---

# 5. Frontend - 페이지별 구성

<details>
<summary> <b> 로그인 페이지</b> </summary>
<div markdown="1">

![로그인](https://github.com/user-attachments/assets/13cae104-ff47-4ed5-9b34-f36dcddd4787)

</div>
</details>

<details>
<summary> <b> 관리자 홈 페이지</b> </summary>
<div markdown="1">

![관리자 홈](https://github.com/user-attachments/assets/1b9dd08c-5e57-4201-93a1-e49e10665958)

</div>
</details>

<details>
<summary> <b> 관리자 페이지 </b> </summary>
<div markdown="1">

![관리자 페이지](https://github.com/user-attachments/assets/aeecb344-b4a6-44f1-931a-93b6028bb426)

</div>
</details>

<details>
<summary> <b> 개인 홈 페이지</b> </summary>
<div markdown="1">

![개인 홈](https://github.com/user-attachments/assets/d3564668-5777-4f97-b586-53d18921fe3f)

</div>
</details>

<details>
<summary> <b> 내 정보 페이지 </b> </summary>
<div markdown="1">

![내 정보 페이지](https://github.com/user-attachments/assets/b56923f8-e3b5-4bda-b2bd-63e94896b1ca)

</div>
</details>

<details>
<summary> <b> 나의 수강신청 페이지 </b> </summary>
<div markdown="1">

![나의 수강신청 페이지](https://github.com/user-attachments/assets/8f2eb6b4-f352-400a-b523-723eee571c66)

</div>
</details>

<details>
<summary> <b> 수강신청 페이지 </b> </summary>
<div markdown="1">

![수강신청 페이지](https://github.com/user-attachments/assets/9e081329-f1c2-4dc2-be8e-db6cfdd9533d)

</div>
</details>

<details>
<summary> <b> 수강신청 기간 확인 페이지</b> </summary>
<div markdown="1">

![수강신청 기간 확인](https://github.com/user-attachments/assets/7c2b1f78-c1c8-4ef0-9a3a-42d145cbc295)

</div>
</details>

<details>
<summary> <b> 학과 전체 조회 페이지</b> </summary>
<div markdown="1">

![학과 전체 조회 페이지](https://github.com/user-attachments/assets/a8c536e8-9dbc-4a5b-acbc-bc9ad0299f31)

</div>
</details>

---

<br><br>


# 6. 회고록

|   조원 이름	|  회고  	|
|---	|---	|
|   차경태 	|     |
|   이창신 	|   	|
|   홍도현 	|   	|
|   임현조 	|   	|
|   이덕찬	|   	|


<br><br><br><br><br><br>
<br><br><br><br><br><br>
