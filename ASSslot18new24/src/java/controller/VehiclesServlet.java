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
import java.util.ArrayList;
import model.InspectionStations;
import model.*;

/**
 *
 * @author Admin
 */
public class VehiclesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

//            HttpSession session = request.getSession();
//            Integer UserId = (Integer) session.getAttribute("UserId");
            String action = request.getParameter("action");

            if (action == null) {
                out.print("loi action");
            } else if (action.equals("view")) {
                String id = request.getParameter("id");
                int UserId = Integer.parseInt(id);

                ArrayList<Vehicles> listVehicles = VehiclesDAO.getVehicleByUserId(UserId);
                for (int i = 0; i < listVehicles.size(); i++) {
                    listVehicles.get(i).includenspectionRecords();
                    listVehicles.get(i).includeBrand();
                    listVehicles.get(i).includeModel();
                }
                request.setAttribute("listVehicles", listVehicles);
                request.getRequestDispatcher("view/Vehicles.jsp").forward(request, response);
            } else if (action.equals("add")) {

                ArrayList<Brand> brands = BrandDAO.getBrands();
                ArrayList<Model> models = ModelDAO.getModels();

                request.setAttribute("brands", brands);
                request.setAttribute("models", models);

                request.getRequestDispatcher("view/addVehicles.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                String id = request.getParameter("id");
                int VehicleId = Integer.parseInt(id);
                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(VehicleId);
                vehicle.includeBrand();
                vehicle.includeModel();
                //
                ArrayList<Brand> brands = BrandDAO.getBrands();
                ArrayList<Model> models = ModelDAO.getModels();
                request.setAttribute("brands", brands);
                request.setAttribute("models", models);

                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("view/editVehicles.jsp").forward(request, response);
            } else if (action.equals("viewAll")) {
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
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");

            if (action == null) {
                out.print("loi action");

            } else if (action.equals("add")) {
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

                //
                ArrayList<Vehicles> allVehicles = VehiclesDAO.ListAllVehicle();
                for (Vehicles Vehicle : allVehicles) {
                    if (Vehicle.getPlateNumber().equals(PlateNumber)) {
                        request.setAttribute("error", "The Plate number (" + PlateNumber + ") already exists. Try again");
                        //
                        request.setAttribute("PlateNumber", PlateNumber);
                        request.setAttribute("brandid", brandid);
                        request.setAttribute("modelid", modelid);
                        request.setAttribute("year", year);
                        request.setAttribute("EngineNumber", EngineNumber);
                        ArrayList<Brand> brands = BrandDAO.getBrands();
                        ArrayList<Model> models = ModelDAO.getModels();
                        request.setAttribute("brands", brands);
                        request.setAttribute("models", models);
                        request.getRequestDispatcher("view/addVehicles.jsp").forward(request, response);
                        return;
                    }
                }
                //add
                Vehicles vehicle = new Vehicles(UserId, PlateNumber, brandid, modelid, year, EngineNumber);
                vehicle = VehiclesDAO.addVehicle(vehicle);

                //
                ArrayList<Vehicles> listVehicles = VehiclesDAO.getVehicleByUserId(vehicle.getOwnerID());
                for (int i = 0; i < listVehicles.size(); i++) {
                    listVehicles.get(i).includenspectionRecords();
                    listVehicles.get(i).includeBrand();
                    listVehicles.get(i).includeModel();
                }

                request.setAttribute("listVehicles", listVehicles);
                request.getRequestDispatcher("view/Vehicles.jsp").forward(request, response);

            } else if (action.equals("edit")) {
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

                //
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
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
