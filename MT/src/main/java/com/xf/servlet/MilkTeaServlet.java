package com.xf.servlet;

import com.alibaba.fastjson2.JSON;
import com.xf.dao.MilkTeaDao;
import com.xf.entity.MilkTea;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/milkTea")
public class MilkTeaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //1.通用设置
        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin","*");
    //2.获取操作码op
        String op = req.getParameter("op");
        //3.根据操作码执行操作
        switch (op) {
            case "getAll":
                getAll(req, resp);
                break;
        }
}
public void getAll(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        MilkTeaDao dao = new MilkTeaDao();
        List<MilkTea> list = dao.getAll();
        PrintWriter pw= resp.getWriter();
        pw.println(JSON.toJSON(list));
        pw.flush();

    }
}
