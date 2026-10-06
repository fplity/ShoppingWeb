package com.xf.servlet;

import com.xf.dao.UserDao;
import com.xf.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        switch (op) {
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;

        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String tel = req.getParameter("tel");
        User user = new User();
        user.username = username;
        user.password = password;
        user.tel = tel;
        UserDao dao = new UserDao();
        int n = dao.register(user);
        PrintWriter pw = resp.getWriter();
        pw.println(n);
        pw.flush();
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


            UserDao dao = new UserDao();
            boolean flag = dao.login(username,password);
            PrintWriter pw = resp.getWriter();
            pw.println(flag);
            pw.flush();

    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tel = req.getParameter("tel");
            UserDao dao = new UserDao();
            int n = dao.delete(tel);
            PrintWriter pw = resp.getWriter();
            pw.println(n);
            pw.flush();

    }
}