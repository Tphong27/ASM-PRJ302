<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your Vehicles Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vehicleResult.css">
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

        <!-- Main Content -->
        <div class="container main-content mt-5">
            <h1 class="page-title">Vehicle Details</h1>
            <div class="vehicle-dashboard">
                <!-- Vehicle Overview Card -->
                <div class="vehicle-card">
                    <div class="card-header">
                        <i class="bi bi-car-front-fill"></i> Vehicle Overview
                    </div>
                    <div class="card-body">
                        <div class="info-item">
                            <span class="info-label">Plate Number:</span>
                            <span class="info-value">${vehicle.plateNumber}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Brand:</span>
                            <span class="info-value">${vehicle.brand.brandName}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Model:</span>
                            <span class="info-value">${vehicle.model.modelName}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Engine Number:</span>
                            <span class="info-value">${vehicle.engineNumber}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Manufacture Year:</span>
                            <span class="info-value">${vehicle.manufactureYear}</span>
                        </div>
                    </div>
                </div>

                <!-- Inspection Details Card -->
                <div class="vehicle-card">
                    <div class="card-header">
                        <i class="bi bi-clipboard-check"></i> Inspection Details
                    </div>
                    <div class="card-body">
                        <div class="info-item">
                            <span class="info-label">Inspection Date:</span>
                            <span class="info-value">${vehicle.inspectionRecords.inspectionDate}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">CO2 Emission:</span>
                            <span class="info-value">${vehicle.inspectionRecords.CO2Emission}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">HC Emission:</span>
                            <span class="info-value">${vehicle.inspectionRecords.HCEmission}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Inspection Result:</span>
                            <span class="info-value
                                  <c:choose>
                                      <c:when test="${vehicle.inspectionRecords.result == 'Pass'}">text-success</c:when>
                                      <c:when test="${vehicle.inspectionRecords.result == 'Fail'}">text-danger</c:when>
                                      <c:when test="${vehicle.inspectionRecords.result == 'Pending'}">text-warning</c:when>
                                      <c:when test="${vehicle.inspectionRecords.result == 'Testing'}">text-primary</c:when>
                                      <c:otherwise>text-secondary</c:otherwise>
                                  </c:choose>
                                  ">
                                ${vehicle.inspectionRecords.result}
                            </span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Comment:</span>
                            <span class="info-value">${vehicle.inspectionRecords.comments}</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Inspection Registration Form -->
            <c:if test="${vehicle.inspectionRecords.result != 'Pass'}">
                <div class="vehicle-form-container mt-5">
                    <h2 class="form-title">📅 Register Inspection for ${vehicle.plateNumber}</h2>
                    <form action="${pageContext.request.contextPath}/inspectionRecords" method="post">
                        <input type="hidden" name="action" value="addRegistCar">
                        <input type="hidden" name="vehicleid" value="${vehicle.vehicleID}">

                        <div class="form-group">
                            <label class="form-label">Inspection Station</label>
                            <select name="stationId" class="form-input">
                                <c:forEach items="${listStation}" var="ls">
                                    <option value="${ls.stationID}" 
                                            <c:if test="${ls.stationID == stationId}">selected="selected"</c:if>>
                                        ${ls.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Inspection Date</label>
                            <input type="date" name="RegistrationDate" class="form-input" value="${RegistrationDate}" required>
                        </div>

                        <c:if test="${not empty errorMessage}">
                            <div class="error-message">${errorMessage}</div>
                        </c:if>

                        <div class="form-group text-center">
                            <button type="submit" class="btn-submit">Register</button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>