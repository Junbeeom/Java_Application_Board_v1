package com.project.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BoardService {
    public BoardService() {}

    LinkedHashMap<Integer, Board> listedHashMap = new LinkedHashMap<Integer, Board>();
    LinkedHashMap<Integer, Board> deleteHashMap = new LinkedHashMap<Integer, Board>();

    //등록
    public void registered(String userTitle, String userContent, String userName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String createdTime = dateFormat.format(new Date());

        listedHashMap.put((listedHashMap.size() + 1), new Board(userTitle, userContent, userName, createdTime, "없음", "없음"));

        System.out.println("\n" + userName + "님의 게시글 등록이 완료 되었습니다.\n게시글 고유번호는 " + listedHashMap.size() + "입니다.");
    }

    //조회
    public void listed() {
        Scanner sc = new Scanner(System.in);
        int limit = 2;
        int offset;
        int cnt = 0;

        System.out.println("현재 페이지는 '1page'입니다.\n등록된 게시글의 수는 총 " + listedHashMap.size() + " 개 입니다. ");

        //조회 메소드 실행시 출력되는 기본 list
        for (int key : listedHashMap.keySet()) {
            listPrint(key);
            cnt++;

            if(cnt == limit) {
                break;
            }
        }

        //게시글이 4개부터 실행되는 로직
        if (listedHashMap.size() > limit) {
            System.out.println("페이지 이동은 1번, 메뉴로 이동는 2번");
            int switchNumber = sc.nextInt();

            switch (switchNumber) {
                case 1:
                    System.out.print("이동 가능한 페이지 개수는 " + listedHashMap.size() / limit + "개 입니다. \n이동하실 page를 입력하세요\n ");
                    int page = sc.nextInt();

                    offset = limit * page - limit;
                    cnt = 1;

                    System.out.println("================================");
                    System.out.println("현재 페이지는 " + page + "입니다");

                    for (int key : listedHashMap.keySet()) {
                        if(cnt > offset && cnt <= limit * page) {
                            listPrint(key);
                        }
                        cnt++;
                    }

                    //추가 페이지 이동 여부 확인
                    while (true) {
                        System.out.println("추가적인 페이지이동 1번, 취소2번");

                        if (sc.nextInt() == 1) {
                            System.out.print("이동 가능한 페이지 개수는 " + listedHashMap.size() / limit + "개 입니다. \n이동하실 page를 입력하세요\n ");
                            page = sc.nextInt();

                            offset = limit * page - limit;
                            cnt = 1;
                            System.out.println("================================");
                            System.out.println("현재 페이지는 " + page + "입니다");

                            for (int key : listedHashMap.keySet()) {
                                if(cnt > offset && cnt <= limit * page) {
                                    listPrint(key);
                                }
                                cnt++;
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
    public void searched(String searchValue, int searchIndex) {
        boolean flag = false;
        switch (searchIndex) {

            //이름으로 검색
            case 1:
                for (int key : listedHashMap.keySet()) {
                    if (listedHashMap.get(key).getName().contains(searchValue)) {
                        listPrint(key);
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("등록된 작성자가 없습니다.");
                }
                break;

            //제목으로 검색
            case 2:
                for (int key : listedHashMap.keySet()) {
                    if (listedHashMap.get(key).getTitle().contains(searchValue)) {
                        listPrint(key);
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("등록된 제목이 없습니다.");
                }
                break;

            //내용으로 검색
            case 3:
                for (int key : listedHashMap.keySet()) {
                    if (listedHashMap.get(key).getContent().contains(searchValue)) {
                        listPrint(key);
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
    public void modified(int number) {
        Scanner sc = new Scanner(System.in);

        if (listedHashMap.get(number) == null) {
            System.out.println("존재하지 않는 게시글입니다");
        } else {
            System.out.println("1.이름 수정\n2.제목 수정\n3.내용 수정\n4.취소는 아무키 입력");

            int modifiedIndex = sc.nextInt();
            switch (modifiedIndex) {

                //이름 수정
                case 1:
                    System.out.println("수정하실 이름을 입력하세요");
                    sc.nextLine();
                    String newName = sc.nextLine();

                    newName = nameCheck(sc, newName);
                    listedHashMap.get(number).setName(newName);

                    String updateTs = updatedTs();
                    listedHashMap.get(number).setUpdatedTs(updateTs);

                    System.out.println(listedHashMap.get(number).getName() + "님의 게시글이 수정되었습니다.\n수정일시 : "  + updateTs);
                    break;

                //제목 수정
                case 2:
                    System.out.println("수정하실 제목을 입력하세요");
                    sc.nextLine();
                    String newTitle = sc.nextLine();

                    newTitle = titleCheck(sc, newTitle);
                    listedHashMap.get(number).setTitle(newTitle);

                    updateTs = updatedTs();
                    listedHashMap.get(number).setUpdatedTs(updateTs);

                    System.out.println(listedHashMap.get(number).getName() + "님의 게시글이 수정되었습니다.\n수정일시 : "  + updateTs);
                    break;
                    
                //내용 수정
                case 3:
                    System.out.println("수정하실 내용을 입력하세요");
                    sc.nextLine();
                    String newContent = sc.nextLine();

                    newContent = contentCheck(sc, newContent);
                    listedHashMap.get(number).setContent(newContent);

                    updateTs = updatedTs();
                    listedHashMap.get(number).setUpdatedTs(updateTs);

                    System.out.println(listedHashMap.get(number).getName() + "님의 게시글이 수정되었습니다.\n수정일시 : "  + updateTs);
                    break;
                default:
                    System.out.println("취소 되었습니다");
                    break;
            }
        }
    }

    //삭제
    public void deleted(int number) {
        Scanner sc = new Scanner(System.in);

        if (listedHashMap.get(number) == null) {
            System.out.println("존재하지 않는 게시글입니다");
        } else {
            System.out.println("삭제 1번, 취소 2번");

            int deletedIndex = sc.nextInt();
            switch (deletedIndex) {
                case 1:
                    String deleteTs = deletedTs();

                    deleteHashMap.put(deleteHashMap.size() + 1, new Board(
                            listedHashMap.get(number).getTitle(),
                            listedHashMap.get(number).getContent(),
                            listedHashMap.get(number).getName(),
                            listedHashMap.get(number).getCreatedTs(),
                            listedHashMap.get(number).getUpdatedTs(),
                            deleteTs));

                    System.out.println(listedHashMap.get(number).getName() + "님의 게시글이 삭제되었습니다.\n삭제일시 : "  + deleteTs);
                    listedHashMap.remove(number);
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

    //수정시간 등록을 위한 메소드
    public String updatedTs() {
        SimpleDateFormat userFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date time = new Date();
        String userModificationDate = userFormat.format(time);

        return userModificationDate;
    }

    //삭제시간 등록을 위한 메소드
    public String deletedTs() {
        SimpleDateFormat userFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date time = new Date();
        String userDeletenDate = userFormat.format(time);

        return userDeletenDate;
    }

    //게시글 내용 출력
    public void listPrint(int key) {
        System.out.println("고유번호 : " + key);
        System.out.println("작 성 자 : " + listedHashMap.get(key).getName());
        System.out.println("제    목 : " + listedHashMap.get(key).getTitle());

        System.out.println("내    용 : ");
        StringTokenizer stk = new StringTokenizer(listedHashMap.get(key).getContent(), "\\n");
        while (stk.hasMoreTokens()) {
            System.out.println(stk.nextToken());
        }

        System.out.println("등록일시 : " + listedHashMap.get(key).getCreatedTs());
        System.out.println("수정일시 : " + listedHashMap.get(key).getUpdatedTs());
        System.out.println("==============================");
    }
}

