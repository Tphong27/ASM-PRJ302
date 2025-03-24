/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import dao.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NotificationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NotificationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotificationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("punishment")) {
            String id = request.getParameter("ownerID");
            int ownerID = Integer.parseInt(id);
            String vid = request.getParameter("vID");
            int vehicleid = Integer.parseInt(vid);
            String note = request.getParameter("note");
            
            Vehicles vehicles = VehiclesDAO.getVehicleByVehicleId(vehicleid);

            HttpSession session = request.getSession();
            Users account = (Users) session.getAttribute("userAccount");
            Notifications notification = new Notifications(ownerID,"Xe "+vehicles.getPlateNumber()+ " <b>"+ note +"</b> Phạt bởi " + account.getFullname());
            NotificationsDAO.addNotification(notification);
            Logs log = new Logs(account.getUserId(), "Sử phạt phương tiện giao thông");
            LogDAO.addLog(log);

            ArrayList<Vehicles> ListVehicles = VehiclesDAO.ListAllVehicle();
            for (Vehicles vehicle : ListVehicles) {
                vehicle.includeBrand();
                vehicle.includeModel();
            }
            request.setAttribute("ListVehicles", ListVehicles);
            request.getRequestDispatcher("view/checkVeForPolice.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
