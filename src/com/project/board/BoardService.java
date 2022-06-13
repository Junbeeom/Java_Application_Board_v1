package com.project.board;

import java.text.SimpleDateFormat;
import java.util.*;

// 기능을 담당하고 있는 클래스임을 명시
public class BoardService {
    int cnt = 0;
    public BoardService() {}

    // lhm.get(입력받은고유번호).setName("바꿀이름");
    // lhm.get(입력받은고유번호).setDeleted(true);

    LinkedHashMap<Integer, Board> boardlinkedHashMap = new LinkedHashMap<Integer, Board>();

    //등록
    public void registered(String userTitle, String userContent, String userName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String createdDate = dateFormat.format(new Date());

        // lhm.put(고유번호, new Board(제목, 내용, 이름, 등록시간, 수정시간(null, "없음", "-"), 삭제여부(false)));
        boardlinkedHashMap.put(cnt, new Board(userTitle, userContent, userName, createdDate, "없음", false));

        System.out.println("\n" + userName + " 의 게시글 등록이 완료 되었습니다. ");
        cnt++;
    }

    //조회
    public void listed() {
        Scanner sc = new Scanner(System.in);

        System.out.println("현재 페이지는 '1page'입니다.\n등록된 게시글의 수는 총 " + boardlinkedHashMap.size() +" 개 입니다. ");
        for (int key : boardlinkedHashMap.keySet()) {
            if (key >= 0 && key < 3) {
                System.out.println("==============================");
                System.out.println("고유번호 : " + key);
                System.out.println("작 성 자 : " + boardlinkedHashMap.get(key).getName());
                System.out.println("제    목 : " + boardlinkedHashMap.get(key).getTitle());

                System.out.println("내    용 : ");
                StringTokenizer stk = new StringTokenizer(boardlinkedHashMap.get(key).getContent(), "\\n");
                while (stk.hasMoreTokens()) {
                    System.out.println(stk.nextToken());
                }

                System.out.println("등록일시 : " + boardlinkedHashMap.get(key).getCreated());
                System.out.println("수정일시 : " + boardlinkedHashMap.get(key).getUpdated());
                System.out.println("==============================");
            }
        }
        int limit = 3;

        //게시글이 3개 이상 시 부터 실행되는 로직
        if(boardlinkedHashMap.size() > limit) {
            System.out.println("페이지 이동은 1번, 메뉴로 이동는 2번");
            int switchNumber = sc.nextInt();

            switch (switchNumber) {
                case 1:
                    System.out.print("이동 가능한 페이지 개수는 " + boardlinkedHashMap.size() / 3 +   "개 입니다. \n이동하실 page를 입력하세요\n ");
                    int page = sc.nextInt();

                    int offset = (page * limit) - limit;
                    int maxSize = (page * limit);

                    System.out.println("================================");
                    System.out.println("현재 페이지는 " + page + "입니다");

                    for (int key : boardlinkedHashMap.keySet()) {
                        if(key >= offset && key < maxSize) {
                            System.out.println("고유번호 : " + key);
                            System.out.println("작 성 자 : " + boardlinkedHashMap.get(key).getName());
                            System.out.println("제    목 : " + boardlinkedHashMap.get(key).getTitle());

                            System.out.println("내    용 : ");
                            StringTokenizer stk = new StringTokenizer(boardlinkedHashMap.get(key).getContent(), "\\n");
                            while (stk.hasMoreTokens()) {
                                System.out.println(stk.nextToken());
                            }

                            System.out.println("등록일시 : " +  boardlinkedHashMap.get(key).getCreated());
                            System.out.println("수정일시 : " +  boardlinkedHashMap.get(key).getUpdated());
                            System.out.println("==============================");
                        }
                    }

                    //추가 페이지 이동 여부 확인
                    while (true) {
                        System.out.println("추가적인 페이지이동 1번, 취소2번");

                        if (sc.nextInt() == 1) {
                            System.out.print("이동 가능한 페이지 개수는 " + boardlinkedHashMap.size() / 3 +   "개 입니다. \n이동하실 page를 입력하세요\n ");
                            page = sc.nextInt();

                            offset = (page * limit) - limit;
                            maxSize = (page * limit);

                            System.out.println("================================");
                            System.out.println("현재 페이지는 " + page + "입니다");

                            for (int key : boardlinkedHashMap.keySet()) {
                                if (key >= offset && key < maxSize) {
                                    System.out.println("고유번호 : " + key);
                                    System.out.println("작 성 자 : " + boardlinkedHashMap.get(key).getName());
                                    System.out.println("제    목 : " + boardlinkedHashMap.get(key).getTitle());

                                    System.out.println("내    용 : ");
                                    StringTokenizer stk = new StringTokenizer(boardlinkedHashMap.get(key).getContent(), "\\n");
                                    while (stk.hasMoreTokens()) {
                                        System.out.println(stk.nextToken());
                                    }

                                    System.out.println("등록일시 : " + boardlinkedHashMap.get(key).getCreated());
                                    System.out.println("수정일시 : " + boardlinkedHashMap.get(key).getUpdated());
                                    System.out.println("==============================");
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    break;

                default:
                    System.out.println("취소 되었습니다");
                    break;
            }
        }
    }

    //검색
    public void searched() {

    }

    //수정
    public void modified() {

    }

    //삭제
    public void deleted() {


    }

    //제목 유효성 검증
    public String titleCheck(String title) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            if(title.length() <= 12) {
                break;
            } else {
                System.out.println("제목은 12글자 이하로 입력해야 합니다.");
                System.out.println("제목을 다시 입력하세요");
                title = sc.nextLine();
            }
        }

        return title;
    }

    //내용 유효성 검증
    public String contentCheck(Scanner sc, String content) {
        if(content.length() <= 200) {
            return content;
        } else {
            System.out.println("내용은 200자 이하로 작성할 수 있습니다.\n글자수에 맞게 다시 작성하세요");
            content = sc.nextLine();

            return this.contentCheck(sc, content);
        }
    }

    public String nameCheck(Scanner sc, String name) {
        while (true) {
            // 딱 알맞는 용도의 정규표현식으로 사용하기
            // 한글, 영어만
            // 숫자, 특수문자
            String isKoreanCheck = ".*[ㄱ-ㅎㅏ-ㅣ가-힣0-9!?()]+.*";
            String isAlaphaCheck = "^[a-zA-Z]*$";
            // 숫자와 특수문자에 대한 정규표현식 하나로만 검증을 하면은
            // !name.matches(정규표현식);
            // 위에서 content랑 비교해서 뭐가 더 좋을지 판단해서 수정
            if(name.matches(isKoreanCheck) || name.matches(isAlaphaCheck)) {
                break;
            } else {
                System.out.println("올바른 형식을 입력하세요 ");
                name = sc.nextLine();
            }
        }

        return name;
    }

    // 아래 내용은 필요없어보인다.
    //삭제 유효성 검증
    public Boolean isDeleted(String userName) {
        return true;
    }
}