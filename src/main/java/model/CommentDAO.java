package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    public CommentDAO() {
        this.conn = Connector.getConnection();
    }

    public ArrayList<Member> test() {
        String sql = "select * from Member";
        ArrayList<Member> members = new ArrayList<Member>();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()) {
                Member _member = new Member();

                _member.setId(rs.getString(1));
                _member.setPassword(rs.getString(2));
                _member.setName(rs.getString(3));

                members.add(_member);
            }

            return members;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Member> search(String name) {
        String sql = "select * from Member where name like ?";
        ArrayList<Member> members = new ArrayList<Member>();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%"+name+"%");
            rs = pstm.executeQuery();

            while(rs.next()) {
                Member _member = new Member();

                _member.setId(rs.getString(1));
                _member.setName(rs.getString(3));

                members.add(_member);
            }
            return members;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addMember(String id, String password, String name) {
        String sql = "insert into Member(id, password, name) values (?, ?, ?)";

        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, id);
            pstm.setString(2, password);
            pstm.setString(3, name);

            pstm.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}