<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your Inspection</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vehicle.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="view/Owner.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?UserId=${userAccount.userId}&action=view">
                                <i class="bi bi-person-fill"></i> Personal Information
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?id=${userAccount.userId}&action=view">
                                <i class="bi bi-car-front-fill"></i> Your Vehicles
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?&action=viewInspectionDate">
                                <i class="bi bi-clipboard-check"></i> Inspection
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">
                                <i class="bi bi-box-arrow-right"></i> Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container main-content">
            <div class="row justify-content-center mt-5">
                <div class="col-lg-10">
                    <h2 class="text-center mb-4">📋 Your Inspection</h2>
                    <table class="table table-bordered table-hover text-center align-middle">
                        <thead class="table-dark">
                            <tr>
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
                                        <td>${lr.vehicles.plateNumber}</td>
                                        <td>${lr.inspectionStations.name}</td>
                                        <td>${lr.inspectionStations.address}</td>
                                        <td>${lr.registrationDate}</td>
                                        <td>${lr.inspectionDate}</td>
                                        <td>
                                            <span class="baged
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
            </div>
        </div>
        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>

