import DAO.BoardDAO;
import DTO.Board;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Board board;
    static List<Board> boardList;
    static BoardDAO dao = new BoardDAO();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int menuNo = 0;
        do {
            menu();
            menuNo = sc.nextInt();
            sc.nextLine();

            switch (menuNo) {
                case 0 : System.out.println("프로그램 종료 합니다."); return;
                case 1 : menuWrite(); break;
                case 2 : menuUpdate(); break;
                case 3 : menuDelete(); break;
                case 4 : menuSelect(); break;
                case 5 : menuList(); break;
                default:
                    System.out.println("0~5 사이의 숫자를 입력하세요"); break;
            }

//            System.out.println(menuNo);
        } while (true);
    }

    public static void menu() {
        System.out.println("**************** 게 시 판 ****************");
        System.out.println("************ 1. 게 시 글 쓰 기 ************");
        System.out.println("************ 2. 게 시 글 수 정 ************");
        System.out.println("************ 3. 게 시 글 삭 제 ************");
        System.out.println("************ 4. 게 시 글 조 회 ************");
        System.out.println("************ 5. 게 시 글 목 록 ************");
        System.out.println("************ 0. 게 시 판 종 료 ************");
        System.out.print(">> 메뉴 선택 : ");
    }

    public static void menuWrite(){
        System.out.println("************** [ 글 쓰 기 ] **************");
        System.out.print("제목 : ");
        String title = sc.nextLine();

        System.out.print("작성자 : ");
        String writer = sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();

//        System.out.println("title >> " + title + " / writer >> " + writer + " / content >> " + content);

        board = new Board(title, writer, content);
        dao.insert(board);
    }

    public static void menuUpdate() {
        System.out.println("************** [ 글 수 정 ] **************");

        System.out.print("게시글 번호 : ");
        int board_no = sc.nextInt();
        sc.nextLine();

        board = dao.select(board_no);

        System.out.print("제목 : ");
        String title = sc.nextLine();

        System.out.print("작성자 : ");
        String writer = sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();

        //
        board = new Board(title, writer, content);
        board.setBoard_no(board_no);
        dao.update(board);
    }

    public static void menuDelete() {
        System.out.println("************** [ 글 삭 제 ] **************");
        System.out.print("게시글 번호 : ");
        int board_no = sc.nextInt();
        sc.nextLine();

        System.out.println("게시글 번호는 " + board_no);
        dao.delete(board_no);
    }

    public static void menuSelect() {
        System.out.println("************** [ 글 조 회 ] **************");
        System.out.print("게시글 번호 : ");
        int board_no = sc.nextInt();
        sc.nextLine();

        board = dao.select(board_no);
        printDetail(board);
    }

    public static void menuList() {
        System.out.println("************** [ 글 목 록 ] **************");
        boardList = dao.selectList();
//        System.out.println(boardList);
        printAll();
    }

    public static void printAll() {
//        System.out.println("printAll");
        for(Board board : boardList) {
            print(board);
        }

    }

    public static void print(Board board) {
        if(board == null) return;

        String dateFormat = "yyyy/MM/dd hh:MM:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String regDate = sdf.format(board.getReg_date());
        String updDate = sdf.format(board.getUpd_date());
        String formatedBoardNo = String.format("%-3s", board.getBoard_no());
        String formatedTitle = String.format("%-30s", board.getTitle());
        String formatedWriter = String.format("%-20s", board.getWriter());
        String formatedRegDate = String.format("%-20s", regDate);
        String formatedUpdDate = String.format("%-20s", updDate);

        System.out.println(formatedBoardNo + "|" + formatedTitle + "|"
                + formatedWriter + "|" + formatedRegDate + "|" + formatedUpdDate);
    }

    private static void printDetail(Board board) {
        if(board == null) return;

        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String regDate = sdf.format(board.getReg_date());
        String updDate = sdf.format(board.getUpd_date());

        System.out.println("[ 게시글 번호 : " + board.getBoard_no() + " ]");
        System.out.println("제목 : " + board.getTitle());
        System.out.println("작성자 : " + board.getWriter());
        System.out.println("작성일 : " + regDate);
        System.out.println("수정일 : " + updDate);
        System.out.println("내용\n\t" + board.getContent());
    }
}