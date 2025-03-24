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
import java.util.ArrayList;
import model.InspectionStations;
import dao.InspectionStationsDAO;
import dao.UsersDAO;
import model.Users;

/**
 *
 * @author Admin
 */
public class InspectionStationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InspectionStationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InspectionStationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("view")) {
            ArrayList<InspectionStations> stations = InspectionStationsDAO.getStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("view/StationManager.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.getRequestDispatcher("view/addStation.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            int stationId = Integer.parseInt(request.getParameter("id"));
            InspectionStations station = InspectionStationsDAO.getInspectionStationsByStationId(stationId);
            request.setAttribute("station", station);
            request.getRequestDispatcher("view/editStation.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int stationId = Integer.parseInt(request.getParameter("id"));
            InspectionStations station = InspectionStationsDAO.getInspectionStationsByStationId(stationId);
            InspectionStationsDAO.deleteStation(station);
            ArrayList<InspectionStations> stations = InspectionStationsDAO.getStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("view/StationManager.jsp").forward(request, response);
        } else if (action.equals("viewStaff")) {
            int stationId = Integer.parseInt(request.getParameter("id"));
            InspectionStations station = InspectionStationsDAO.getInspectionStationsByStationId(stationId);
            request.setAttribute("station", station);
            Users staff = UsersDAO.getUserByID(station.getUserID());
            if (staff == null) {
                request.getRequestDispatcher("view/viewStationStaf.jsp").forward(request, response);
                return;
            }
            staff.includeRoles();
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("view/viewStationStaf.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            String stationName = request.getParameter("stationName");
            String Address = request.getParameter("Address");
            String Phone = request.getParameter("Phone");
            String Email = request.getParameter("Email");

            request.setAttribute("stationName", stationName);
            request.setAttribute("Address", Address);
            request.setAttribute("Phone", Phone);
            request.setAttribute("Email", Email);

            if (!UsersDAO.isValidEmail(Email)) {
                request.setAttribute("error", "Invalid Email !!!");
                request.getRequestDispatcher("view/addStation.jsp").forward(request, response);
                return;
            }

            ArrayList<InspectionStations> stations = InspectionStationsDAO.getStations();
            for (InspectionStations station : stations) {
                if (station.getEmail().equalsIgnoreCase(Email)) {
                    request.setAttribute("error", "Email " + Email + " already exist");
                    request.getRequestDispatcher("view/addStation.jsp").forward(request, response);
                    return;
                }
                if (station.getName().equalsIgnoreCase(stationName)) {
                    request.setAttribute("error1", "Station name " + stationName + " already exists");
                    request.getRequestDispatcher("view/addStation.jsp").forward(request, response);
                    return;
                }
            }

            InspectionStations station = new InspectionStations(stationName, Address, Phone, Email);
            InspectionStationsDAO.addStation(station);
            stations = InspectionStationsDAO.getStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("view/StationManager.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            String stationName = request.getParameter("stationName");
            String Address = request.getParameter("Address");
            String Phone = request.getParameter("Phone");
            String Email = request.getParameter("Email");

            int stationId = Integer.parseInt(request.getParameter("id"));
            InspectionStations oldStation = InspectionStationsDAO.getInspectionStationsByStationId(stationId);
            ArrayList<InspectionStations> stations = InspectionStationsDAO.getStations();
            if (!UsersDAO.isValidEmail(Email)) {
                request.setAttribute("error1", "Invalid Email");
                request.setAttribute("station", oldStation);
                request.getRequestDispatcher("view/editStation.jsp").forward(request, response);
                return;
            }
            if (!oldStation.getEmail().equalsIgnoreCase(Email)) {
                for (InspectionStations station : stations) {
                    if (station.getEmail().equalsIgnoreCase(Email)) {
                        request.setAttribute("error1", "Email " + Email + " exist");
                        request.setAttribute("station", oldStation);
                        request.getRequestDispatcher("view/editStation.jsp").forward(request, response);
                        return;
                    }
                }
            }
            if (!oldStation.getName().equalsIgnoreCase(stationName)) {
                for (InspectionStations station : stations) {
                    if (station.getName().equalsIgnoreCase(stationName)) {
                        request.setAttribute("error2", "Station name " + stationName + " already exists");
                        request.setAttribute("station", oldStation);
                        request.getRequestDispatcher("view/editStation.jsp").forward(request, response);
                        return;
                    }
                }
            }

            InspectionStations station = new InspectionStations(stationId, stationName, Address, Phone, Email, stationId);
            InspectionStationsDAO.updateStation(station);

            stations = InspectionStationsDAO.getStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("view/StationManager.jsp").forward(request, response);
        } else if (action.equals("chooseStation")) {
            int staffId = Integer.parseInt(request.getParameter("id"));
            int stationId = Integer.parseInt(request.getParameter("stationId"));
            InspectionStations station = new InspectionStations(stationId, staffId);
            InspectionStationsDAO.updateStationStaff(station);

            ArrayList<Users> ListAccount = UsersDAO.getUsers();
            for (Users user : ListAccount) {
                user.includeRoles();
            }
            request.setAttribute("ListAccount", ListAccount);
            request.getRequestDispatcher("view/checkAccount.jsp").forward(request, response);
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
