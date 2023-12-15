package DAO;

import DTO.Board;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.LinkedList;
import java.util.List;

public class BoardDAO extends JDBCConnection {
    public int insert(Board board){
        int result = 0;
        String sql = " INSERT INTO Board(title, writer, content) "
                + " VALUES(?, ?, ?) ";

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("insertSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getWriter());
            psmt.setString(3, board.getContent());
            result = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch(SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("게시글 등록 시, 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    public List<Board> selectList() {
        LinkedList<Board> boardList = new LinkedList<>();
        String sql = " SELECT * "
                + " FROM Board "
                + " ORDER BY reg_date desc ";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Board board = new Board();
                board.setBoard_no(rs.getInt("board_no"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setReg_date(rs.getTimestamp("reg_date"));
                board.setUpd_date(rs.getTimestamp("upd_date"));
                boardList.add(board);
            }
        } catch (Exception e) {
            System.out.println("게시글 목록 조회시 예외 발생");
            e.printStackTrace();
        }

        return boardList;
    }

    public Board select(int board_no) {
        Board board = new Board();
        String sql = " SELECT * "
                + " FROM Board "
                + " WHERE board_no = ? ";

        try {
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, board_no);
            rs = psmt.executeQuery();

            if(rs.next()) {
                board.setBoard_no(rs.getInt("board_no"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setReg_date(rs.getTimestamp("reg_date"));
                board.setUpd_date(rs.getTimestamp("upd_date"));
            } else {
                System.out.println(board_no + "번 게시물은 존재하지 않습니다.");
                board = null;
            }
        } catch(Exception e) {
            System.out.println("게시글 상세 조회시 예외 발생");
            e.printStackTrace();
        }
        return board;
    }

    public int update(Board board) {
        int result = 0;
        String sql = " UPDATE Board "
                + " SET title = ? "
                + " , writer = ? "
                + " , content = ? "
                + " , upd_date = now() "
                + " WHERE board_no = ? ";

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("UpdateSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getWriter());
            psmt.setString(3, board.getContent());
            psmt.setInt(4, board.getBoard_no());

            result  = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch (SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                System.out.println("게시글 수정시 예외 발생");
                e1.printStackTrace();
            }

            e.printStackTrace();
        }

        return result;
    }

    public int delete(int board_no) {
        int result = 0;
        String sql = " DELETE FROM Board "
                + " WHERE board_no = ? ";

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("DeleteSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, board_no);

            result  = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch (SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                System.out.println("게시글 삭제시 예외 발생");
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return result;
    }
}
