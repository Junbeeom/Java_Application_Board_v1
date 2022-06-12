package com.project.board;

import java.util.Scanner;

public class BoardFrame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BoardService boardService = new BoardService();

        boolean flag = true;

        while (flag) {
            System.out.println("=================");
            System.out.println("1.게시글 작성하기");
            System.out.println("2.게시글 삭제하기");
            System.out.println("3.게시글 검색하기");
            System.out.println("4.게시글 수정하기");
            System.out.println("5.게시글 목록보기");
            System.out.println("6.    종료");
            System.out.println("=================");
            
            switch (sc.nextInt()) {
                //등록하기
                case 1:
                    System.out.println("제목을 입력하세요 : ");
                    sc.nextLine();
                    String userTitle = sc.nextLine();

                    System.out.println("내용을 입력하세요. 줄바꿈은 \n 입력하세요");
                    String userContent = sc.nextLine();
                    userContent = boardService.contentCheck(sc, userContent);

                    System.out.println("작성자 이름을 입력하세요 : ");
                    String userName = sc.nextLine() ;
                    userName = boardService.nameCheck(sc, userName);
                    boardService.registered(userTitle, userContent, userName);
                    break;
                //삭제하기
                case 2:
                    System.out.println("제목으로 검색해서 삭제하기 1번");
                    System.out.println("작성자로 검색해서 삭제하기 2번");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("제목을 입력하세요");
                            userTitle = sc.next();

                            //유효성 검증 추가
                            boardService.deleted();
                            break;

                        default:
                            System.out.println("작성자의 이름을 입력하세요");
                            userName = sc.next();

                            //유효성 검증 추가
                            boardService.deleted();
                    }
                    break;
                //검색하기
                case 3:
                    System.out.println(" 제목  검색하기 1번");
                    System.out.println("작성자 검색하기 2번");
                    System.out.println(" 내용  검색하기 3번");

                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("제목을 입력하세요");
                            userTitle = sc.next();

                            //유효성 검증 추가
                            boardService.searched();
                            break;

                        case 2:
                            System.out.println("작성자의 이름을 입력하세요");
                            userName = sc.next();

                            //유효성 검증 추가
                            boardService.searched();
                            break;

                        default:
                            System.out.println("내용을 입력하세요");
                            userContent = sc.next();

                            //유효성 검증 추가
                            boardService.searched();
                            break;
                    }
                    break;
                //수정하기
                case 4:
                    System.out.println(" 제목  수정하기 1번");
                    System.out.println("작성자 수정하기 2번");
                    System.out.println(" 내용  수정하기 3번");

                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("제목을 입력하세요");
                            userTitle = sc.next();

                            //유효성 검증 추가
                            boardService.modified();
                            break;

                        case 2:
                            System.out.println("작성자의 이름을 입력하세요");
                            userName = sc.next();

                            //유효성 검증 추가
                            boardService.modified();
                            break;

                        default:
                            System.out.println("내용을 입력하세요");
                            userContent = sc.next();

                            //유효성 검증 추가
                            boardService.modified();
                            break;
                    }
                    break;
                //조회
                case 5:
                    boardService.listed();
                    break;
                default:
                    flag = false;
            }
        }
    }
}
