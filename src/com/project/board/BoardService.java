package com.project.board;

import java.text.SimpleDateFormat;
import java.util.*;

public class BoardService {
    int cnt = 1;

    public BoardService() {
    }

    LinkedHashMap<Integer, Board> boardlinkedHashMap = new LinkedHashMap<Integer, Board>();

    //등록
    public void registered(String userTitle, String userContent, String userName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String createdDate = dateFormat.format(new Date());

        boardlinkedHashMap.put(cnt, new Board(userTitle, userContent, userName, createdDate, "없음", false));

        System.out.println("\n" + userName + "님의 게시글 등록이 완료 되었습니다.\n게시글 고유번호는 " + cnt + "입니다.");
        cnt++;
    }

    //조회
    public void listed() {
        Scanner sc = new Scanner(System.in);
        int totalContent = 0;
        int limit = 3;
        int offset = 0;
        int totalMax = 0;


        //게시글 개수 세기
        for (int key : boardlinkedHashMap.keySet()) {
            if (boardlinkedHashMap.get(key).getDeleted() == false) {
                totalContent++;
            }
        }

        //false에 따른 가변 limit 범위
        for (int key : boardlinkedHashMap.keySet()) {
            if (offset < key && key <= limit) {

                if (boardlinkedHashMap.get(key).getDeleted() == true) {
                    totalMax++;
                }
            }
        }

        System.out.println("현재 페이지는 '1page'입니다.\n등록된 게시글의 수는 총 " + totalContent + " 개 입니다. ");
        for (int key : boardlinkedHashMap.keySet()) {

            if (key > offset && key <= limit + totalMax && boardlinkedHashMap.get(key).getDeleted() == false) {
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

        //게시글이 4개 이상 시 부터 실행되는 로직
        if (boardlinkedHashMap.size() > limit) {
            System.out.println("페이지 이동은 1번, 메뉴로 이동는 2번");
            int switchNumber = sc.nextInt();

            switch (switchNumber) {
                case 1:
                    System.out.print("이동 가능한 페이지 개수는 " + boardlinkedHashMap.size() / 3 + "개 입니다. \n이동하실 page를 입력하세요\n ");
                    int page = sc.nextInt();

                    totalMax = 0;
                    //false에 따른 가변 limit 범위
                    for (int key : boardlinkedHashMap.keySet()) {
                        if (page * limit - 2 <= key && key < page * limit + 1) {
                            if (boardlinkedHashMap.get(key).getDeleted() == true) {
                                totalMax++;
                            }
                        }
                    }

                    System.out.println("================================");
                    System.out.println("현재 페이지는 " + page + "입니다");

                    for (int key : boardlinkedHashMap.keySet()) {
                        if (page * limit - 2 <= key && key < page * limit + 1 + totalMax && boardlinkedHashMap.get(key).getDeleted() == false) {
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

                    //추가 페이지 이동 여부 확인
                    while (true) {
                        System.out.println("추가적인 페이지이동 1번, 취소2번");

                        if (sc.nextInt() == 1) {
                            System.out.print("이동 가능한 페이지 개수는 " + boardlinkedHashMap.size() / 3 + "개 입니다. \n이동하실 page를 입력하세요\n ");

                            page = sc.nextInt();

                            totalMax = 0;
                            //false에 따른 가변 limit 범위
                            for (int key : boardlinkedHashMap.keySet()) {
                                if (page * limit - 2 <= key && key < page * limit + 1) {

                                    if (boardlinkedHashMap.get(key).getDeleted() == true) {
                                        totalMax++;
                                    }
                                }
                            }

                            System.out.println("================================");
                            System.out.println("현재 페이지는 " + page + "입니다");

                            for (int key : boardlinkedHashMap.keySet()) {
                                if (page * limit - 2 <= key && key < page * limit + 1 + totalMax && boardlinkedHashMap.get(key).getDeleted() == false) {
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
    public void searched(String userTitle, int choice) {
        boolean flag = false;
        switch (choice) {

            //이름으로 검색
            case 1:
                for (int key : boardlinkedHashMap.keySet()) {
                    if (boardlinkedHashMap.get(key).getName().contains(userTitle) && boardlinkedHashMap.get(key).getDeleted() == false) {
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
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("등록된 작성자가 없습니다.");
                }
                break;

            case 2:
                //제목으로 검색
                for (int key : boardlinkedHashMap.keySet()) {
                    if (boardlinkedHashMap.get(key).getTitle().contains(userTitle) && boardlinkedHashMap.get(key).getDeleted() == false) {
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
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("등록된 제목이 없습니다.");
                }
                break;

            case 3:
                //내용으로 검색
                for (int key : boardlinkedHashMap.keySet()) {
                    if (boardlinkedHashMap.get(key).getContent().contains(userTitle) && boardlinkedHashMap.get(key).getDeleted() == false) {
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
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("등록된 내용이 없습니다.");
                }
                break;
            default:
                break;
        }
    }


    //수정
    public void modified(String userName, int choice) {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        switch (choice) {

            //이름으로 수정
            case 1:
                for (int key : boardlinkedHashMap.keySet()) {
                    if (boardlinkedHashMap.get(key).getName().contains(userName) && boardlinkedHashMap.get(key).getDeleted() == false) {
                        System.out.println("변경하실 이름을 입력하세요.");
                        String UserInputNewName = sc.next();

                        System.out.println("변경 1번, 취소 2번");

                        switch (sc.nextInt()) {
                            case 1:
                                String newName = nameCheck(sc, UserInputNewName);
                                String modifyTime = modificationDate();

                                boardlinkedHashMap.get(key).setName(newName);
                                boardlinkedHashMap.get(key).setUpdated(modifyTime);

                                System.out.println("수정이 완료되었습니다.\n수정일시 : " + modifyTime);
                                break;

                            default:
                                System.out.println("취소 되었습니다");
                                break;
                        }
                    }
                    flag = true;
                }
                if (!flag) {
                    System.out.println("등록된 작성자가 없습니다.");
                }
                break;

            case 2:
                //제목수정
                for (int key : boardlinkedHashMap.keySet()) {
                    if (boardlinkedHashMap.get(key).getTitle().contains(userName) && boardlinkedHashMap.get(key).getDeleted() == false) {
                        System.out.println("변경하실 제목을 입력하세요.");
                        String UserInputNewTitle = sc.next();

                        System.out.println("변경 1번, 취소 2번");

                        switch (sc.nextInt()) {
                            case 1:
                                String newTitle = nameCheck(sc, UserInputNewTitle);
                                String modifyTime = modificationDate();

                                boardlinkedHashMap.get(key).setTitle(newTitle);
                                boardlinkedHashMap.get(key).setUpdated(modifyTime);

                                System.out.println("수정이 완료되었습니다.\n수정일시 : " + modifyTime);
                                break;

                            default:
                                System.out.println("취소 되었습니다");
                                break;
                        }
                    }
                    flag = true;
                }

                if (!flag) {
                    System.out.println("등록된 제목이 없습니다.");
                }
                break;

            case 3:
                //내용으로 수정
                for (int key : boardlinkedHashMap.keySet()) {
                    if (boardlinkedHashMap.get(key).getContent().contains(userName) && boardlinkedHashMap.get(key).getDeleted() == false) {
                        System.out.println("변경하실 내용을 입력하세요.");
                        String UserInputNewContent = sc.next();

                        System.out.println("변경 1번, 취소 2번");

                        switch (sc.nextInt()) {
                            case 1:
                                String newContent = nameCheck(sc, UserInputNewContent);
                                String modifyTime = modificationDate();

                                boardlinkedHashMap.get(key).setContent(newContent);
                                boardlinkedHashMap.get(key).setUpdated(modifyTime);

                                System.out.println("수정이 완료되었습니다.\n수정일시 : " + modifyTime);
                                break;

                            default:
                                System.out.println("취소 되었습니다");
                                break;
                        }
                    }
                    flag = true;
                }

                if (!flag) {
                    System.out.println("등록된 내용이 없습니다.");
                }
                break;
            default:
                break;

        }
    }
    //삭제
    public void deleted ( int number){
        Scanner sc = new Scanner(System.in);

        if (boardlinkedHashMap.get(number) == null) {
            System.out.println("존재하지 않는 게시글입니다");
        } else {
            System.out.println("삭제 1번, 취소 2번");
            int switchNumber = sc.nextInt();
            switch (switchNumber) {
                case 1:
                    boardlinkedHashMap.get(number).setDeleted(true);
                    System.out.println(boardlinkedHashMap.get(number).getName() + "의 정보가 삭제되었습니다.");
                    break;
                default:
                    System.out.println("취소 되었습니다");
                    break;
            }
        }
    }


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

    //이름 유효성 검증
    public String nameCheck (Scanner sc, String name){
        String isKoreanCheck = "^[가-힣]*$";
        String isAlaphaCheck = "^[a-zA-Z]*$";
        if (name.matches(isKoreanCheck) || name.matches(isAlaphaCheck)) {
            return name;
        } else {
            System.out.println("올바른 형식을 입력하세요 ");
            name = sc.nextLine();

            return this.nameCheck(sc, name);
        }
    }

    //수정시간 등록을 위한 메소드
    public String modificationDate () {
        SimpleDateFormat userFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date time = new Date();
        String userModificationDate = userFormat.format(time);

        return userModificationDate;
    }
}

