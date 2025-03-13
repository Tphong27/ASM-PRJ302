<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Vehicles</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="view/Owner.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="view/UserInfo.jsp">Personal Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?id=${userAccount.userId}&action=view">Your Vehicles</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?&action=viewInspectionDate">Inspection</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Vehicle ID</th>
                        <th>Plate Number</th>
                        <th>Station</th>
                        <th>Address</th>
                        <th>Registration Date</th>
                        <th>Inspection Date</th>
                        <th>Result</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${listRecords}" var="lr">
                        <tr>
                            <c:if test="${lr.vehicles.ownerID == userAccount.userId}">
                                <td>${lr.vehicleID}</td>
                                <td>${lr.vehicles.plateNumber}</td>
                                <td>${lr.inspectionStations.name}</td>
                                <td>${lr.inspectionStations.address}</td>
                                <td>${lr.registrationDate}</td>
                                <td>${lr.inspectionDate}</td>
                                <td>
                                    <span class="
                                          <c:choose>
                                              <c:when test="${lr.result == 'Pass'}">text-success fw-bold</c:when>
                                              <c:when test="${lr.result == 'Fail'}">text-danger fw-bold</c:when>
                                              <c:when test="${lr.result == 'Pending'}">text-warning fw-bold</c:when>
                                              <c:when test="${lr.result == 'Testing'}">text-primary fw-bold</c:when>
                                              <c:otherwise>text-secondary</c:otherwise>
                                          </c:choose>
                                          ">
                                        ${lr.result}
                                    </span>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

