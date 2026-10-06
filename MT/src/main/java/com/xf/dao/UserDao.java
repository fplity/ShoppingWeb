package com.xf.dao;

import com.xf.entity.User;
import com.xf.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean login(String username, String password) {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from user where username=? and password=?";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int register(User user){
        Connection conn = DBUtil.getConnection();
        String sql ="insert into user values(?,?,?)";
        try{

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.username);
            ps.setString(2, user.password);
            ps.setString(3, user.tel);
            return ps.executeUpdate();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String tel){
        Connection conn = DBUtil.getConnection();
        String sql ="delete from user where tel=?";
        try{

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,tel);
            return ps.executeUpdate();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        UserDao dao = new UserDao();
        System.out.println(dao.login("zs", "123"));
        System.out.println(dao.login("ls", "444"));
        System.out.println(dao.login("ww", "555"));
    }
}
