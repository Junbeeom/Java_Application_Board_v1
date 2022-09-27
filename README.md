# Java_Application_Board_v1
- 개인 Project
# 1.프로젝트 개요
### 1.1 프로젝트 목적
- 순수 java를 이용한 게시판 CRUD Application 개발
### 1.2 목표 및 의의
- Class, Instance 개념 확립
- 멤버변수의 private 접근제어자 선언을 통한 캡술화 개념 이해 
- Method Signature와 Constructor Overloading 개념 이해 
- CRUD 기능 구현을 위한 Java Collection 자료구조 학습(list, set, map) 및 적용(LinkedHashMap)
# 2. 개발 환경
- IntelliJ IDEA(Ultimate Edition)Runtime version: 11.0.14.1+1-b1751.46 aarch64 VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
# 3. 사용기술
- Java 11
# 4.프로젝트 설계
<img width="403" alt="스크린샷 2022-09-27 오후 3 39 43" src="https://user-images.githubusercontent.com/103010985/192451771-ee61c01a-f4da-40af-a7fb-13ccd96aa6f9.png">

# 5.기본 기능
- 등록 registered 
- 조회 listed
- 검색 searched
- 수정 modified
- 삭제 deleted



# 6.핵심 기능
- registered, searched, modified Method 호출시 Parameter 유효성 검증 Method

유효성 검증 Method
```java
    //제목 유효성 검증
    public String titleCheck (Scanner sc, String title){
        if (title.length() <= 12) {
            return title;
        } else {
            System.out.println("제목은 12글자 이하로 입력해야 합니다.\n다시 입력하세요.");
            title = sc.nextLine();
            return this.titleCheck(sc, title);
        }
    }
    
    //이름 유효성 검증
    public String nameCheck (Scanner sc, String name){
        String isKoreanCheck = "^[가-힣]*$";
        String isAlaphaCheck = "^[a-zA-Z]*$";
        if (name.matches(isKoreanCheck) || name.matches(isAlaphaCheck)) {
            return name;
        } else {
            System.out.println("올바른 형식을 입력하세요\n한글 및 영어만 입력하세요.");
            name = sc.nextLine();

            return this.nameCheck(sc, name);
        }
    }
    
    //내용 유효성 검증
    public String contentCheck (Scanner sc, String content){
        if (content.length() <= 200) {
            return content;
        } else {
            System.out.println("내용은 200자 이하로 작성할 수 있습니다.\n글자수에 맞게 다시 작성하세요");
            content = sc.nextLine();

            return this.contentCheck(sc, content);
        }
    }
```

# 7.회고
프로젝트를 진행하며 외부 Class에서의 데이터 접근을 막기 위해 Class 멤버변수의 접근 제어자를 Private하게 관리하고 멤버 변수에 접근하기 위한 Getter와 Setter의 쓰임을 배웠다.
이러한 과정을 통해 데이터를 안전하게 보호하기 위한 OOP의 캡슐화에 대한 개념을 명확하게 이해 할 수 있었다.


조회시 return 받는 자료구조를 선택할때 고려 했던 조건이 있다. 등록할때 넣었던 자료가 출력시 순서를 유지 할 수 있는지, 수정 및 검색시 해당 게시글의 접근을 할 수 있는지에 대해 고민을 하다보니 모든 조건을 충족하는 ListHashMap을 사용 하게 되었다.



사용자가 게시글을 등록, 수정, 검색하는 시점에 입력하는 text에 대해 최소한의 검증 절차를 만들어 조건에 충족되지 않으면 다시 입력 할 수 있도록 했다. tittleCheck, contentsCheck, nameCheck Method를 구현하면서 정규화 식에 대해서도 학습 할 수 있었고, 유효성 검증 Method는 BoardService에 공통 Method로 구현함으로써 유지 보수의 용이성을 높일 수 있었다.

