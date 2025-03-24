/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.LogDAO;
import dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Logs;
import model.Users;

/**
 *
 * @author Admin
 */
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String username = request.getParameter("Fullname");
            String password = request.getParameter("Password");
            String Email = request.getParameter("Email");

            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("Email", Email);

            if (!UsersDAO.isValidEmail(Email)) {
                request.setAttribute("error1", "Invalid Email");
                request.getRequestDispatcher("view/register.jsp").forward(request, response);
                return;
            }
            if (!UsersDAO.isValidPassword(password)) {
                request.setAttribute("error2", "Invalid Password");
                request.getRequestDispatcher("view/register.jsp").forward(request, response);
                return;
            }

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                if (UsersDAO.checkUserExist(Email)) {
                    request.setAttribute("errorMessage", "Email already exists. Please choose another.");
                    request.getRequestDispatcher("view/register.jsp").forward(request, response);
                } else {
                    Users userAccount = new Users(username, Email, password, 1, "Not yet", "Not yet");

                    UsersDAO.addAccount(userAccount);

<<<<<<< HEAD
                    //
=======
>>>>>>> 9b0e173 (Cập nhật code)
                    Users newAccount = UsersDAO.getUser(Email);
                    Logs log = new Logs(newAccount.getUserId(), "Owner " + newAccount.getFullname() + " lập tài khoản.");
                    LogDAO.addLog(log);

                    request.setAttribute("success", "Register success");
                    request.getRequestDispatcher("view/register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Account cannot be empty.");
                request.getRequestDispatcher("view/register.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
