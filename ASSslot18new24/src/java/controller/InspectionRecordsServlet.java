package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import dao.*;
import dao.VehiclesDAO;
import java.util.ArrayList;
import model.*;

public class InspectionRecordsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InspectionRecordsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InspectionRecordsServlet at " + request.getContextPath() + "</h1>");
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
            if (action.equals("viewInspectionDate")) {
//                String id = request.getParameter("vehicleid");
//                int vehicleid = Integer.parseInt(id);

                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);

//                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
//                int Userid = vehicle.getOwnerID();
//                request.setAttribute("Userid", Userid);
                request.getRequestDispatcher("view/Inspection.jsp").forward(request, response);
            } else if (action.equals("viewCheckList")) {//
                String id = request.getParameter("id");
                int userId = Integer.parseInt(id);

                InspectionStations station = InspectionStationsDAO.getInspectionStationsByUserId(userId);
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getRecordByStationId(station.getStationID());
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/CheckList.jsp").forward(request, response);
            } else if (action.equals("viewRegisList")) {
//                String id = request.getParameter("id");
//                int userId = Integer.parseInt(id);

                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/RegisList.jsp").forward(request, response);
            } else if (action.equals("SendRecords")) {
                String id = request.getParameter("id");
                int vehicleid = Integer.parseInt(id);

                String recordid = request.getParameter("recordid");
                int reID = Integer.parseInt(recordid);

                String date = request.getParameter("InspectionDate");
                Date InspectionDate = java.sql.Date.valueOf(date);

                String sid = request.getParameter("stationid");
                int stationid = Integer.parseInt(sid);

                request.setAttribute("vehicleid", vehicleid);
                request.setAttribute("reID", reID);
                request.setAttribute("stationid", stationid);
                request.setAttribute("InspectionDate", InspectionDate);

                String reDate = request.getParameter("reDate");
                Date RegistionDate = java.sql.Date.valueOf(reDate);
                if (InspectionDate.before(RegistionDate)) {
                    String error = "Date " + InspectionDate + " not valid";
                    request.setAttribute("error", error);
                    ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                    for (InspectionRecords Record : listRecords) {
                        Record.includeVehicles();
                        Record.includeInspectionStations();
                    }
                    request.setAttribute("listRecords", listRecords);
                    request.getRequestDispatcher("view/RegisList.jsp").forward(request, response);
                    return;
                }

                //
                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("InspectionDate", InspectionDate);
                ArrayList<Users> listInspector = UsersDAO.getUserByRole(2);//list inspector
                request.setAttribute("listInspector", listInspector);
                request.getRequestDispatcher("view/chooseInspector.jsp").forward(request, response);
            } else if (action.equals("measureList")) {
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/MeasureList.jsp").forward(request, response);
            } else if (action.equals("measureVe")) {
                String id = request.getParameter("id");
                int vehicleid = Integer.parseInt(id);

                String CO2 = request.getParameter("co2");
                double co2emission = Double.parseDouble(CO2);

                String HC = request.getParameter("hc");
                double hcemission = Double.parseDouble(HC);

                String result = "";
                double MAX_CO2 = 3.0;
                double MAX_HC = 2.0;

                if (co2emission <= MAX_CO2 && hcemission <= MAX_HC) {
                    result = "Pass";
                } else {
                    result = "Fail";
                }

                String inspectorID = request.getParameter("inspectorID");
                int inspecid = Integer.parseInt(inspectorID);

                String stationID = request.getParameter("stationID");
                int staid = Integer.parseInt(stationID);

                String recordID = request.getParameter("recordID");
                int reid = Integer.parseInt(recordID);

                String date = request.getParameter("date");
                Date inspectiondate = java.sql.Date.valueOf(date);

                InspectionRecords record = new InspectionRecords(reid, vehicleid, staid, inspecid, inspectiondate, result, co2emission, hcemission);
                InspectionRecordsDAO.updateRecords(record);

                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
                int userid = vehicle.getOwnerID();

                Notifications notification = new Notifications(userid, "Trạng thái xe của bạn: " + result);
                NotificationsDAO.addNotification(notification);

                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/MeasureList.jsp").forward(request, response);
            }
        }
        //

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            if (action.equals("addRegistCar")) {
                String id = request.getParameter("vehicleid");
                int vehicleid = Integer.parseInt(id);

                String sId = request.getParameter("stationId");
                int stationId = Integer.parseInt(sId);

                String date = request.getParameter("RegistrationDate");
                Date RegistrationDate = java.sql.Date.valueOf(date);

                // check date
                Date today = new Date(System.currentTimeMillis());
                if (RegistrationDate.before(today)) {
                    request.setAttribute("errorMessage", "Invalid registration date " + RegistrationDate + " !");

                    request.setAttribute("stationId", stationId);
                    request.setAttribute("RegistrationDate", RegistrationDate);
                    Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
                    vehicle.includenspectionRecords();
                    request.setAttribute("vehicle", vehicle);
                    ArrayList<InspectionStations> listStation = InspectionStationsDAO.getStations();
                    request.setAttribute("listStation", listStation);
                    request.getRequestDispatcher("view/VehicleResult.jsp").forward(request, response);
                    return;
                }

                int defaultInspectorId = 1;
                String result = "Pending";
                double co2 = 0.0;
                double hc = 0.0;

                InspectionRecords record = new InspectionRecords(vehicleid, stationId, defaultInspectorId, RegistrationDate, result, co2, hc);

                InspectionRecordsDAO.addRecordsforOwner(record);
                // list records
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);

                request.getRequestDispatcher("view/Inspection.jsp").forward(request, response);
            } else if (action.equals("chooseInspector")) {
                String veid = request.getParameter("veid");
                int vehicleid = Integer.parseInt(veid);

                String staid = request.getParameter("staid");
                int stationID = Integer.parseInt(staid);

                String id = request.getParameter("id");
                int recordID = Integer.parseInt(id);

                String date = request.getParameter("date");
                Date InspectionDate = java.sql.Date.valueOf(date);

                String Inspecid = request.getParameter("inspectionId");
                int inspectionId = Integer.parseInt(Inspecid);

                InspectionRecords record = new InspectionRecords(recordID, vehicleid, stationID, inspectionId, InspectionDate, "Testing", 0.00, 0.00);
                InspectionRecordsDAO.updateRecords(record);

                //notification
                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
                int userid = vehicle.getOwnerID();
                Notifications notification = new Notifications(userid, "Xe của bạn đã được lên lịch kiểm định vào ngày " + InspectionDate);
                NotificationsDAO.addNotification(notification);
                Notifications notification2 = new Notifications(inspectionId, "Bạn có một xe mới" + vehicle.getPlateNumber() + "cần kiểm định vào ngày " + InspectionDate);
                NotificationsDAO.addNotification(notification2);

                //
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/RegisList.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
