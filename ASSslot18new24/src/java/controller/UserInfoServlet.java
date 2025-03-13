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
import model.Users;

/**
 *
 * @author Admin
 */
public class UserInfoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserInfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserInfoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String id = request.getParameter("UserId");
            if (action == null) {
                out.print("loi action");
            } else if (action.equals("edit")) {
                int UserId = Integer.parseInt(id);

                Users user = UsersDAO.getUserByID(UserId);
                if (user == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                    return;
                }

                request.setAttribute("getUser", user);
                request.getRequestDispatcher("view/editUserInfo.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("UserId");
            int UserId = Integer.parseInt(id);
            String Fullname = request.getParameter("Fullname");
            Users oldUser = UsersDAO.getUserByID(UserId);
            String Email = oldUser.getEmail();
            String Password = request.getParameter("Password");
            String Address = request.getParameter("Address");
            String Phone = request.getParameter("Phone");

            Users user = new Users(UserId, Fullname, Email, Password, Phone, Address);

            user = UsersDAO.updateUser(user);

            if (user == null) {
                out.print("edit loi");
            } else {
                int userIdUpdate = user.getUserId();
                Users updatedUser = UsersDAO.getUserByID(userIdUpdate);
//                request.setAttribute("getUser", updatedUser);

                HttpSession session = request.getSession();
                session.setAttribute("userAccount", updatedUser);
                response.sendRedirect("view/UserInfo.jsp");
                return;
            }
            request.getRequestDispatcher("view/UserInfo.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
