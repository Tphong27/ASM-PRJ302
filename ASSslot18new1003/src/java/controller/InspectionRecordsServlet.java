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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
            if (action.equals("viewInspectionDate")) { //user

                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/Inspection.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Xem toàn bộ thông tin ngày đăng kiểm xe");
                LogDAO.addLog(log);
            } else if (action.equals("viewCheckList")) {//station
                String id = request.getParameter("id");
                int userId = Integer.parseInt(id);

                InspectionStations station = InspectionStationsDAO.getInspectionStationsByUserId(userId);
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getRecordByStationId(station.getStationID());
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                    Record.includeUsers();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/CheckList.jsp").forward(request, response);

                Logs log = new Logs(userId, "Xem toàn bộ tình trạng trong hồ sơ");
                LogDAO.addLog(log);
            } else if (action.equals("viewRegisList")) {//station
<<<<<<< HEAD
//                String id = request.getParameter("id");
//                int userId = Integer.parseInt(id);
=======
>>>>>>> 9b0e173 (Cập nhật code)

                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/RegisList.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Xem danh sách chờ đăng ký");
                LogDAO.addLog(log);
            } else if (action.equals("SendRecords")) { //station
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

<<<<<<< HEAD
//                String reDate = request.getParameter("reDate");
//                Date RegistionDate = java.sql.Date.valueOf(reDate);
=======
>>>>>>> 9b0e173 (Cập nhật code)
                Map<Integer, String> errorMap = new HashMap<>();

                Date now = new Date(System.currentTimeMillis());
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
<<<<<<< HEAD
//                for (InspectionRecords lr : listRecords) {
                    if (InspectionDate.before(now)) {
//                        String error = "Date " + InspectionDate + " not valid";
                        errorMap.put(reID, "Date "+InspectionDate+" is not valid");

//                        request.setAttribute("error", error);
                        request.setAttribute("errorMap", errorMap);

//                        ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
=======
                    if (InspectionDate.before(now)) {
                        errorMap.put(reID, "Date "+InspectionDate+" is not valid");

                        request.setAttribute("errorMap", errorMap);

>>>>>>> 9b0e173 (Cập nhật code)
                        for (InspectionRecords Record : listRecords) {
                            Record.includeVehicles();
                            Record.includeInspectionStations();
                        }
                        request.setAttribute("listRecords", listRecords);
                        request.getRequestDispatcher("view/RegisList.jsp").forward(request, response);
                        return;
//                    }

                }
                //
                Vehicles vehicle = VehiclesDAO.getVehicleByVehicleId(vehicleid);
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("InspectionDate", InspectionDate);
                ArrayList<Users> listInspector = UsersDAO.getUserByRole(2);//list inspector
                request.setAttribute("listInspector", listInspector);
                request.getRequestDispatcher("view/chooseInspector.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Chọn ngày " + InspectionDate + " đo xe " + vehicle.getPlateNumber());
                LogDAO.addLog(log);
            } else if (action.equals("measureList")) {  //inspector
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/MeasureList.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Xem danh sách xe cần đăng kiểm");
                LogDAO.addLog(log);
            } else if (action.equals("measureVe")) { //inspector
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

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Thực hiện đăng kiểm xe " + vehicle.getPlateNumber());
                LogDAO.addLog(log);
            }
        }
<<<<<<< HEAD
        //
=======
>>>>>>> 9b0e173 (Cập nhật code)

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            if (action.equals("addRegistCar")) { //user
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

                String result = "Pending";
                double co2 = 0.0;
                double hc = 0.0;

                InspectionRecords record = new InspectionRecords(vehicleid, stationId, RegistrationDate, result, co2, hc);

                InspectionRecordsDAO.addRecordsforOwner(record);
                // list records
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);

                request.getRequestDispatcher("view/Inspection.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Đăng ký ngày đăng kiểm xe " + RegistrationDate);
                LogDAO.addLog(log);
            } else if (action.equals("chooseInspector")) { // station
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
                Notifications notification2 = new Notifications(inspectionId, "Bạn có một xe mới " + vehicle.getPlateNumber() + " cần kiểm định vào ngày " + InspectionDate);
                NotificationsDAO.addNotification(notification2);

<<<<<<< HEAD
                //
=======
>>>>>>> 9b0e173 (Cập nhật code)
                ArrayList<InspectionRecords> listRecords = InspectionRecordsDAO.getInspectionRecords();
                for (InspectionRecords Record : listRecords) {
                    Record.includeVehicles();
                    Record.includeInspectionStations();
                }
                request.setAttribute("listRecords", listRecords);
                request.getRequestDispatcher("view/RegisList.jsp").forward(request, response);

                HttpSession session = request.getSession();
                Users account = (Users) session.getAttribute("userAccount");
                Logs log = new Logs(account.getUserId(), "Chọn inspector đo xe " + vehicle.getPlateNumber());
                LogDAO.addLog(log);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
