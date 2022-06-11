package com.project.board;

import java.text.SimpleDateFormat;
import java.util.*;

public class Board {
    int contentCnt = 0;
    int contentCntTotal = 0;

    public Board() {}

    HashMap<String, BoardData> boardDataHashMap = new HashMap<>();
    ArrayList<BoardData> boardDataArrayList = new ArrayList<>();

    //등록
    public void registered(String userTitle, String userContent, String userName) {
        Scanner sc = new Scanner(System.in);

        userTitle = titleCheck(userTitle);
        userContent = contentCheck(userContent);
        userName = nameCheck(userName);

        //등록 일시
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date time = new Date();
        String registrationDate = dateFormat.format(time);

        boardDataHashMap.put(userTitle, new BoardData(userTitle, userContent, userName, registrationDate, "없음"));
        boardDataArrayList.add(new BoardData(userTitle, userContent, userName, registrationDate, "없음"));

        System.out.println();
        System.out.println(userName + "의 게시글 등록이 완료 되었습니다. ");
        System.out.println("등록일시 : " +  registrationDate);

        contentCnt++;
        contentCntTotal++;
        if(contentCnt > 3) {
            contentCnt = 3;
        }

    }

    //조회
    public void listed() {
        Scanner sc = new Scanner(System.in);



        System.out.println("현재 페이지는 '1p'이며, 등록된 게시글의 수는" + contentCntTotal + "개 입니다. ");

        listPrint(sc.nextInt());
//
//        if(contentCntTotal != 3 && (contentCntTotal % 3) == 0) {
//            System.out.println("이동하실 페이지 번호를 입력하세요");
//            printPage(sc.nextInt());
//        }
//        if(contentCntTotal > 3 && (contentCntTotal % 3) != 0){
//            System.out.println("이동하실 페이지 번호를 입력하세요");
//            printPageNull(sc.nextInt(), contentCntTotal);
//        }


    }

    //조회 print 메소드
    public void listPrint(int content) {
        int cnt = 0;

        int maxSize = (content * 3);
        maxSize = (maxSize <= boardDataArrayList.size()) ? maxSize : boardDataArrayList.size();

        for(int i = content * 3 - 3; i < maxSize; i++) {

            System.out.println("==============================");
            System.out.println("제    목 : " + boardDataArrayList.get(i).getTitle());

            StringTokenizer stk = new StringTokenizer(boardDataArrayList.get(i).getContent(), "\\n");
            while (stk.hasMoreTokens()) {
                if(cnt >= 1) {
                    System.out.println("           " + stk.nextToken());
                } else {
                    System.out.println("내    용 : " + stk.nextToken());
                }
                cnt ++;
            }
            System.out.println("작 성 자 : " + boardDataArrayList.get(i).getName());
            System.out.println("등록일시 : " + boardDataArrayList.get(i).getRegistrationTime());
            System.out.println("수정일시 : " + boardDataArrayList.get(i).getModificationTime());
            System.out.println("==============================");
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
    public String contentCheck(String content) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            if(content.length() <= 100) {
                break;
            } else {
                System.out.println("내용은 200자 이하로 작성할 수 있습니다.");
                System.out.println("글자수에 맞게 다시 작성하세요");
                content = sc.nextLine();
            }
        }
        return content;
    }

    public String nameCheck(String name) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String isKoreanCheck = ".*[ㄱ-ㅎㅏ-ㅣ가-힣0-9!?()]+.*";
            String isAlaphaCheck = "^[a-zA-Z]*$";
            if(name.matches(isKoreanCheck) || name.matches(isAlaphaCheck)) {
                break;
            } else {
                System.out.println("올바른 형식을 입력하세요 ");
                name = sc.nextLine();
            }
        }

        return name;
    }

    //삭제 유효성 검증
    public Boolean isDeleted(String userName) {
        return true;
    }
}
