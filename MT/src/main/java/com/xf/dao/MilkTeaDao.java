package com.xf.dao;

import com.mysql.cj.protocol.Resultset;
import com.xf.entity.MilkTea;
import com.xf.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MilkTeaDao {
    public List<MilkTea> getAll() {
        List<MilkTea> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql="select * from milktea";

        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                MilkTea m=new MilkTea();
                m.mid=rs.getString("mid");
                m.mname=rs.getString("mname");
                m.price=rs.getDouble("price");
                m.image=rs.getString("image");
                list.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    static void main() {
        MilkTeaDao dao = new MilkTeaDao();
        List<MilkTea> list = dao.getAll();
        for (MilkTea m : list) {
            System.out.println(m.mid + m.mname + m.price + m.image);
        }
    }
}
