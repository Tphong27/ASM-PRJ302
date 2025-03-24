/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.*;
import dao.*;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String Email = request.getParameter("Email");
            String password = request.getParameter("Password");
            Users account = new Users(Email, password);

            HttpSession session = request.getSession();

            boolean loginResult = UsersDAO.checkLogin(account);

            if (loginResult) {
                account = UsersDAO.getUser(account.getEmail());

<<<<<<< HEAD
//                session.setAttribute("userAccount", account);
                if (account.getRoleID() == 1) {  //Owner
                    session.setAttribute("userAccount", account);

                    //
=======
                if (account.getRoleID() == 1) {  //Owner
                    session.setAttribute("userAccount", account);

>>>>>>> 9b0e173 (Cập nhật code)
                    ArrayList<Notifications> notifications = NotificationsDAO.getNotificationsByUserID(account.getUserId());
                    session.setAttribute("notification", notifications);

                    //log 
                    Logs log = new Logs(account.getUserId(), " Đăng nhập");
                    LogDAO.addLog(log);

                    response.sendRedirect("view/Owner.jsp");
                } else if (account.getRoleID() == 2) {     //Inspector
                    session.setAttribute("userAccount", account);

                    ArrayList<Notifications> notifications = NotificationsDAO.getNotificationsByUserID(account.getUserId());
                    session.setAttribute("notification", notifications);

                    Logs log = new Logs(account.getUserId(), " Đăng nhập");
                    LogDAO.addLog(log);

                    response.sendRedirect("view/Inspector.jsp");
                } else if (account.getRoleID() == 3) {     //Station
                    session.setAttribute("userAccount", account);

                    Logs log = new Logs(account.getUserId(), " Đăng nhập");
                    LogDAO.addLog(log);

                    response.sendRedirect("view/Station.jsp");
                } else if (account.getRoleID() == 4) {     //Police
                    session.setAttribute("userAccount", account);
                    
                    Logs log = new Logs(account.getUserId(), " Đăng nhập");
                    LogDAO.addLog(log);
                    
                    response.sendRedirect("view/Police.jsp");
                } else if (account.getRoleID() == 5) {     //Admin

                    ArrayList<Logs> logs = LogDAO.getLogs();
                    for (Logs log : logs) {
                        log.includeUsers();
                    }
                    session.setAttribute("logs", logs);

                    response.sendRedirect("view/Admin.jsp");
                }
            } else {
                request.setAttribute("Email", Email);
                request.setAttribute("password", password);
                request.setAttribute("errorMessage", "Invalid Email or Password.");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
