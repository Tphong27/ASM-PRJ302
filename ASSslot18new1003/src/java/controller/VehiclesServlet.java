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
import model.InspectionStations;
import model.*;

/**
 *
 * @author Admin
 */
public class VehiclesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VehiclesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VehiclesServlet at " + request.getContextPath() + "</h1>");
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

<<<<<<< HEAD
            if (action == null) {
                out.print("loi action");
            } else if (action.equals("view")) { //user
=======
            if (action.equals("view")) { //user
>>>>>>> 9b0e173 (Cập nhật code)
                String id = request.getParameter("id");
                int UserId = Integer.parseInt(id);

                ArrayList<Vehicles> listVehicles = VehiclesDAO.getVehicleByUserId(UserId);
                for (int i = 0; i < listVehicles.size(); i++) {
                    listVehicles.get(i).includenspectionRecords();
                    listVehicles.get(i).includeBrand();
                    listVehicles.get(i).includeModel();
                }
                Logs log = new Logs(UserId, "Xem danh sách xe");
                LogDAO.addLog(log);

                request.setAttribute("listVehicles", listVehicles);
                request.getRequestDispatcher("view/Vehicles.jsp").forward(request, response);
            } else if (action.equals("add")) { //user
                ArrayList<Brand> brands = BrandDAO.getBrands();
                ArrayList<Model> models = ModelDAO.getModels();
                request.setAttribute("brands", brands);
                request.setAttribute("models", models);
                request.getRequestDispatcher("view/addVehicles.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Thêm xe  mới vào hệ thống");
                LogDAO.addLog(log);
            } else if (action.equals("edit")) { //user
                String id = request.getParameter("id");
                int VehicleId = Integer.parseInt(id);
                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(VehicleId);
                vehicle.includeBrand();
                vehicle.includeModel();
                ArrayList<Brand> brands = BrandDAO.getBrands();
                ArrayList<Model> models = ModelDAO.getModels();

<<<<<<< HEAD
                //
=======
>>>>>>> 9b0e173 (Cập nhật code)
                request.setAttribute("brands", brands);
                request.setAttribute("models", models);
                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("view/editVehicles.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Muốn thay đổi thông tin xe");
                LogDAO.addLog(log);
            } else if (action.equals("viewAll")) { //user
                String id = request.getParameter("id");
                int VehicleId = Integer.parseInt(id);

                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(VehicleId);
                vehicle.includenspectionRecords();
                vehicle.includeBrand();
                vehicle.includeModel();
                //
                request.setAttribute("vehicle", vehicle);

                ArrayList<InspectionStations> listStation = InspectionStationsDAO.getStations();
                request.setAttribute("listStation", listStation);

                request.getRequestDispatcher("view/VehicleResult.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Xem toàn bộ thông tin xe " + vehicle.getPlateNumber());
                LogDAO.addLog(log);
            } else if (action.equals("searchVehicle")) { //police
                String pattern = request.getParameter("partOfPlate");
                ArrayList<Vehicles> ListVehicles = new ArrayList<>();
                if (pattern == null) {
                    ListVehicles = VehiclesDAO.ListAllVehicle();
                } else {
                    ListVehicles = VehiclesDAO.getVehiclesByPlateNumber(pattern);
                }

                for (Vehicles vehicle : ListVehicles) {
                    vehicle.includeBrand();
                    vehicle.includeModel();
                }
                request.setAttribute("ListVehicles", ListVehicles);
                request.getRequestDispatcher("view/checkVeForPolice.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Tìm kiếm phương tiện trong hệ thống");
                LogDAO.addLog(log);
            } else if (action.equals("viewAllVehicleDetail")) { // police
                String id = request.getParameter("id");
                int VehicleId = Integer.parseInt(id);

                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(VehicleId);
                vehicle.includenspectionRecords();
                vehicle.includeBrand();
                vehicle.includeModel();
                vehicle.includeUsers();
<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("view/punishment.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Xem toàn bộ thông tin liên quan của phương tiện");
                LogDAO.addLog(log);
            } else if (action.equals("viewVehiclesOfUser")) { // admin
                int ownerid = Integer.parseInt(request.getParameter("UserId"));
                Users owner = UsersDAO.getUserByID(ownerid);
                ArrayList<Vehicles> listVehicles = VehiclesDAO.getVehicleByUserId(ownerid);
                for (Vehicles vehicle : listVehicles) {
                    vehicle.includenspectionRecords();
                    vehicle.includeBrand();
                    vehicle.includeModel();
                    vehicle.includeUsers();
                }
                request.setAttribute("listVehicles", listVehicles);
                request.setAttribute("ownername", owner.getFullname());
                request.getRequestDispatcher("view/ViewVehicleByAdmin.jsp").forward(request, response);
            } else if (action.equals("searchVehicleForAdmin")) { //admin
                String pattern = request.getParameter("partOfPlate");
                ArrayList<Vehicles> ListVehicles = new ArrayList<>();
                if (pattern == null) {
                    ListVehicles = VehiclesDAO.ListAllVehicle();
                } else {
                    ListVehicles = VehiclesDAO.getVehiclesByPlateNumber(pattern);
                }

                for (Vehicles vehicle : ListVehicles) {
                    vehicle.includeBrand();
                    vehicle.includeModel();
                    vehicle.includenspectionRecords();
                    vehicle.includeUsers();
                }
                request.setAttribute("ListVehicles", ListVehicles);
                request.getRequestDispatcher("view/checkVeForAdmin.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");

<<<<<<< HEAD
            if (action == null) {
                out.print("loi action");

            } else if (action.equals("add")) { //user
=======
            if (action.equals("add")) { //user
>>>>>>> 9b0e173 (Cập nhật code)
                String OwenID = request.getParameter("id");
                int UserId = Integer.parseInt(OwenID);

                String PlateNumber = request.getParameter("PlateNumber");

                String Brand = request.getParameter("Brandid");
                int brandid = Integer.parseInt(Brand);

                String Model = request.getParameter("Modelid");
                int modelid = Integer.parseInt(Model);

                String ManufactureYear = request.getParameter("ManufactureYear");
                int year = Integer.parseInt(ManufactureYear);

                String EngineNumber = request.getParameter("EngineNumber");

<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                request.setAttribute("PlateNumber", PlateNumber);
                request.setAttribute("brandid", brandid);
                request.setAttribute("modelid", modelid);
                request.setAttribute("year", year);
                request.setAttribute("EngineNumber", EngineNumber);
                ArrayList<Brand> brands = BrandDAO.getBrands();
                ArrayList<Model> models = ModelDAO.getModels();
                request.setAttribute("brands", brands);
                request.setAttribute("models", models);
<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                if (!VehiclesDAO.isValidPlateNumber(PlateNumber)) {
                    request.setAttribute("error", "The Plate number ( " + PlateNumber + " ) is invalid. Try again");
                    request.getRequestDispatcher("view/addVehicles.jsp").forward(request, response);
                    return;
                }
<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                ArrayList<Vehicles> allVehicles = VehiclesDAO.ListAllVehicle();
                for (Vehicles Vehicle : allVehicles) {
                    if (Vehicle.getPlateNumber().equals(PlateNumber)) {
                        request.setAttribute("error", "The Plate number (" + PlateNumber + ") already exists. Try again");
                        request.getRequestDispatcher("view/addVehicles.jsp").forward(request, response);
                        return;
                    }
                }
                //add
                Vehicles vehicle = new Vehicles(UserId, PlateNumber, brandid, modelid, year, EngineNumber);
                vehicle = VehiclesDAO.addVehicle(vehicle);

<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                ArrayList<Vehicles> listVehicles = VehiclesDAO.getVehicleByUserId(vehicle.getOwnerID());
                for (int i = 0; i < listVehicles.size(); i++) {
                    listVehicles.get(i).includenspectionRecords();
                    listVehicles.get(i).includeBrand();
                    listVehicles.get(i).includeModel();
                }

                request.setAttribute("listVehicles", listVehicles);
                request.getRequestDispatcher("view/Vehicles.jsp").forward(request, response);

                Logs log = new Logs(UserId, "Thêm xe " + PlateNumber + " vào hệ thống thành công");
                LogDAO.addLog(log);

            } else if (action.equals("edit")) { //user
                String VehicleID = request.getParameter("vehicleid");
                int vId = Integer.parseInt(VehicleID);

                String OwenID = request.getParameter("userid");
                int UserId = Integer.parseInt(OwenID);

                String PlateNumber = request.getParameter("PlateNumber");

                String Brand = request.getParameter("Brandid");
                int brandid = Integer.parseInt(Brand);

                String Model = request.getParameter("Modelid");
                int modelid = Integer.parseInt(Model);

                String ManufactureYear = request.getParameter("ManufactureYear");
                int year = Integer.parseInt(ManufactureYear);

                String EngineNumber = request.getParameter("EngineNumber");

                ArrayList<Brand> brands = BrandDAO.getBrands();
                ArrayList<Model> models = ModelDAO.getModels();
                request.setAttribute("brands", brands);
                request.setAttribute("models", models);

<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                if (!VehiclesDAO.isValidPlateNumber(PlateNumber)) {
                    request.setAttribute("error", "The Plate number ( " + PlateNumber + " ) is invalid. Try again");
                    Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vId);
                    vehicle.includeBrand();
                    vehicle.includeModel();
                    request.setAttribute("vehicle", vehicle);
                    request.getRequestDispatcher("view/editVehicles.jsp").forward(request, response);
                    return;
                }
<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                Vehicles oldVehicle = VehiclesDAO.getVehicleByVehicleId(vId);
                ArrayList<Vehicles> allVehicles = VehiclesDAO.ListAllVehicle();
                if (!oldVehicle.getPlateNumber().equals(PlateNumber)) {
                    for (Vehicles Vehicle : allVehicles) {
                        if (Vehicle.getPlateNumber().equals(PlateNumber)) {
                            request.setAttribute("error", "The Plate number (" + PlateNumber + ") already exists. Edit again");
                            Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vId);
                            vehicle.includeBrand();
                            vehicle.includeModel();
                            request.setAttribute("vehicle", vehicle);
                            request.getRequestDispatcher("view/editVehicles.jsp").forward(request, response);
                            return;
                        }
                    }
                }
                //edit
                Vehicles vehicle = new Vehicles(vId, UserId, PlateNumber, brandid, modelid, year, EngineNumber);
                vehicle = VehiclesDAO.updateVehicles(vehicle);

                ArrayList<Vehicles> listVehicles = VehiclesDAO.getVehicleByUserId(vehicle.getOwnerID());
                for (int i = 0; i < listVehicles.size(); i++) {
                    listVehicles.get(i).includenspectionRecords();
                    listVehicles.get(i).includeBrand();
                    listVehicles.get(i).includeModel();
                }
                request.setAttribute("listVehicles", listVehicles);
                request.getRequestDispatcher("view/Vehicles.jsp").forward(request, response);
<<<<<<< HEAD
                //
=======

>>>>>>> 9b0e173 (Cập nhật code)
                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Thay đổi thông tin xe " + PlateNumber);
                LogDAO.addLog(log);
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
