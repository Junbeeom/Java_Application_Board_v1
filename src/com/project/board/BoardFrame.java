package com.project.board;

import java.util.Scanner;
// 사용하지 않는 import는 제거하기
import java.util.StringTokenizer;

public class BoardFrame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Board board = new Board();

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
                case 1:
                    System.out.println("제목을 입력하세요 : ");
                    sc.nextLine();
                    String userTitle = sc.nextLine();
                    // 아래 주석을 지우않는 이유
                    // ex) 아래 로직과 현 사용중인 로직중 뭐가 더 효율적일지 모르겠어서 일단은 유지
                    // sc.nextLine();
                    // userTitle = board.titleCheck(userTitle);

                    System.out.println("내용을 입력하세요 : 개행하려면 -붙이세요");
                    String userContent = sc.nextLine();
                    userContent = board.contentCheck(sc, userContent);

                    System.out.println("작성자 이름을 입력하세요 : ");
                    String userName = sc.nextLine() ;
                    userName = board.nameCheck(userName);
                    board.registered(userTitle, userContent, userName);
                    break;

                case 2:
                    System.out.println("제목으로 검색해서 삭제하기 1번");
                    System.out.println("작성자로 검색해서 삭제하기 2번");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("제목을 입력하세요");
                            userTitle = sc.next();

                            //유효성 검증 추가
                            board.deleted();
                            break;

                        default:
                            System.out.println("작성자의 이름을 입력하세요");
                            userName = sc.next();

                            //유효성 검증 추가
                            board.deleted();
                    }
                    break;

                case 3:
                    System.out.println(" 제목  검색하기 1번");
                    System.out.println("작성자 검색하기 2번");
                    System.out.println(" 내용  검색하기 3번");

                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("제목을 입력하세요");
                            userTitle = sc.next();

                            //유효성 검증 추가
                            board.searched();
                            break;

                        case 2:
                            System.out.println("작성자의 이름을 입력하세요");
                            userName = sc.next();

                            //유효성 검증 추가
                            board.searched();
                            break;

                        default:
                            System.out.println("내용을 입력하세요");
                            userContent = sc.next();

                            //유효성 검증 추가
                            board.searched();
                            break;
                    }
                    break;

                case 4:
                    System.out.println(" 제목  수정하기 1번");
                    System.out.println("작성자 수정하기 2번");
                    System.out.println(" 내용  수정하기 3번");

                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("제목을 입력하세요");
                            userTitle = sc.next();

                            //유효성 검증 추가
                            board.modified();
                            break;

                        case 2:
                            System.out.println("작성자의 이름을 입력하세요");
                            userName = sc.next();

                            //유효성 검증 추가
                            board.modified();
                            break;

                        default:
                            System.out.println("내용을 입력하세요");
                            userContent = sc.next();

                            //유효성 검증 추가
                            board.modified();
                            break;
                    }
                    break;

                case 5:
                    board.listed();
                    break;
                default:
                    flag = false;
            }
        }
    }
}
