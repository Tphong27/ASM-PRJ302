<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="view/Admin.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?action=viewAll">Account Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionStation?action=view">Inspection Station</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?action=searchVehicleForAdmin">Vehicles List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/brand?action=view">Brand</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/model?action=view">Model</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <h1 class="text-center">List Vehicles of ${ownername}</h1>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Plate Number</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Manufacture Year</th>
                            <th>Engine Number</th>
                            <th>Registration Date</th>
                            <th>Inspection Date</th>
                            <th>Result</th>
                            <th>CO2</th>
                            <th>HC</th>
                            <th>Comments</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lv" items="${listVehicles}">
                            <tr>
                                <td>${lv.plateNumber}</td>
                                <td>${lv.brand.brandName}</td>
                                <td>${lv.model.modelName}</td>
                                <td>${lv.manufactureYear}</td>
                                <td>${lv.engineNumber}</td>
                                <td>${lv.inspectionRecords.registrationDate}</td>
                                <td>${lv.inspectionRecords.inspectionDate}</td>
                                <td>
                                    <span class="
                                          <c:choose>
                                              <c:when test="${lv.inspectionRecords.result == 'Pass'}">text-success fw-bold</c:when>
                                              <c:when test="${lv.inspectionRecords.result == 'Fail'}">text-danger fw-bold</c:when>
                                              <c:when test="${lv.inspectionRecords.result == 'Pending'}">text-warning fw-bold</c:when>
                                              <c:when test="${lv.inspectionRecords.result == 'Testing'}">text-primary fw-bold</c:when>
                                              <c:otherwise>text-secondary</c:otherwise>
                                          </c:choose>
                                          ">
                                        ${lv.inspectionRecords.result}
                                    </span>

                                </td>
                                <td>${lv.inspectionRecords.CO2Emission}</td>
                                <td>${lv.inspectionRecords.HCEmission}</td>
                                <td>${lv.inspectionRecords.comments}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div> 
        </div>
    </body>
</html>
