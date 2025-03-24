/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.*;

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
            if (action.equals("view")) {
                String id = request.getParameter("UserId");
                int UserId = Integer.parseInt(id);

                Users user = UsersDAO.getUserByID(UserId);
                Logs log = new Logs(UserId, "Xem thông tin cá nhân");
                LogDAO.addLog(log);

                request.setAttribute("userAccount", user);
                request.getRequestDispatcher("view/UserInfo.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                String id = request.getParameter("UserId");
                int UserId = Integer.parseInt(id);

                Users user = UsersDAO.getUserByID(UserId);
                Logs log = new Logs(UserId, "Thay đổi thông tin cá nhân");
                LogDAO.addLog(log);

                request.setAttribute("getUser", user);
                request.getRequestDispatcher("view/editUserInfo.jsp").forward(request, response);
            } else if (action.equals("viewAll")) {// admin
                ArrayList<Roles> ListRoles = RoleDAO.getRoles();
                request.setAttribute("ListRoles", ListRoles);

                ArrayList<Users> ListAccount = new ArrayList<Users>();
                String rID = request.getParameter("roleid");
                if (rID == null) {
                    ListAccount = UsersDAO.getUsers();
                } else {
                    int roleID = Integer.parseInt(rID);
                    ListAccount = UsersDAO.getUserByRole(roleID);
                }

                for (Users user : ListAccount) {
                    user.includeRoles();
                }
                request.setAttribute("ListAccount", ListAccount);
                request.getRequestDispatcher("view/checkAccount.jsp").forward(request, response);

            } else if (action.equals("add")) {// admin

                ArrayList<Roles> listRoles = RoleDAO.getRoles();
                request.setAttribute("listRoles", listRoles);
                request.getRequestDispatcher("view/createAcount.jsp").forward(request, response);
            } else if (action.equals("delete")) {// admin
                int accountid = Integer.parseInt(request.getParameter("UserId"));
                Users account = UsersDAO.getUserByID(accountid);
                UsersDAO.deleteAccount(account);
                ArrayList<Users> ListAccount = UsersDAO.getUsers();
                for (Users user : ListAccount) {
                    user.includeRoles();
                }
                request.setAttribute("ListAccount", ListAccount);
                request.getRequestDispatcher("view/checkAccount.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if (action.equals("edit")) {
                String id = request.getParameter("UserId");
                int UserId = Integer.parseInt(id);
                String Fullname = request.getParameter("Fullname");
                Users oldUser = UsersDAO.getUserByID(UserId);
                String Email = oldUser.getEmail();
                String Password = request.getParameter("Password");
                String Address = request.getParameter("Address");
                String Phone = request.getParameter("Phone");

                if (!UsersDAO.isValidPassword(Password)) {
                    request.setAttribute("error1", "Invalid Password");
                    Users user = UsersDAO.getUserByID(UserId);
                    request.setAttribute("getUser", user);
                    request.getRequestDispatcher("view/editUserInfo.jsp").forward(request, response);
                    return;
                }
                if (!UsersDAO.isValidPhoneNumber(Phone)) {
                    request.setAttribute("error2", "Invalid Phone Number");
                    Users user = UsersDAO.getUserByID(UserId);
                    request.setAttribute("getUser", user);
                    request.getRequestDispatcher("view/editUserInfo.jsp").forward(request, response);
                    return;
                }

                Users user = new Users(UserId, Fullname, Email, Password, Phone, Address);

                user = UsersDAO.updateUser(user);

                int userIdUpdate = user.getUserId();
                Users updatedUser = UsersDAO.getUserByID(userIdUpdate);

                HttpSession session = request.getSession();
                session.setAttribute("userAccount", updatedUser);
                Logs log = new Logs(UserId, "Thay đổi thông tin cá nhân thành công");
                LogDAO.addLog(log);

                request.getRequestDispatcher("view/UserInfo.jsp").forward(request, response);
            } else if (action.equals("add")) {
                String Email = request.getParameter("Email");
                String Fullname = request.getParameter("Fullname");
                String Password = request.getParameter("Password");
                String Phone = request.getParameter("Phone");
                String Address = request.getParameter("Address");
                String rid = request.getParameter("roleid");
                int roleid = Integer.parseInt(rid);

                request.setAttribute("Fullname", Fullname);
                request.setAttribute("password", Password);
                request.setAttribute("Email", Email);
                request.setAttribute("Phone", Phone);
                request.setAttribute("Address", Address);
                request.setAttribute("roleid", roleid);
                ArrayList<Roles> listRoles = RoleDAO.getRoles();
                request.setAttribute("listRoles", listRoles);

                if (!UsersDAO.isValidEmail(Email)) {
                    request.setAttribute("error1", "Invalid Email");
                    request.getRequestDispatcher("view/createAcount.jsp").forward(request, response);
                    return;
                }
                if (!UsersDAO.isValidPassword(Password)) {
                    request.setAttribute("error2", "Invalid Password");
                    request.getRequestDispatcher("view/createAcount.jsp").forward(request, response);
                    return;
                }
                if (!UsersDAO.isValidPhoneNumber(Phone)) {
                    request.setAttribute("error3", "Invalid Phone Number");
                    request.getRequestDispatcher("view/createAcount.jsp").forward(request, response);
                    return;
                }

                //create
                //check again
                if (Fullname != null && !Fullname.isEmpty() && Password != null && !Password.isEmpty()) {
                    if (UsersDAO.checkUserExist(Email)) {
                        request.setAttribute("errorMessage", "Email already exists. Please choose another.");
                        request.getRequestDispatcher("view/createAcount.jsp").forward(request, response);
                    } else {
                        //if station
                        if (roleid == 3) { //role = Station
                            Users userAccount = new Users(Fullname, Email, Password, roleid, Phone, Address);
                            userAccount = UsersDAO.addAccount(userAccount);

                            request.setAttribute("staff", userAccount);

                            ArrayList<InspectionStations> Liststations = InspectionStationsDAO.getStationsNoStaff();
                            request.setAttribute("Liststations", Liststations);
                            request.getRequestDispatcher("view/chooseStationForAcc.jsp").forward(request, response);
                            return;
                        }
                        Users userAccount = new Users(Fullname, Email, Password, roleid, Phone, Address);
                        UsersDAO.addAccount(userAccount);

                        ArrayList<Roles> ListRoles = RoleDAO.getRoles();
                        request.setAttribute("ListRoles", ListRoles);

                        ArrayList<Users> ListAccount = UsersDAO.getUsers();
                        for (Users user : ListAccount) {
                            user.includeRoles();
                        }
                        request.setAttribute("ListAccount", ListAccount);
                        request.getRequestDispatcher("view/checkAccount.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("view/checkAccount.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
