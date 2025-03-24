<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Punishment Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/punishment.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="view/Police.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?action=searchVehicle">
                                <i class="bi bi-list-check"></i> Vehicles List
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
        <div class="main-content">
            <!-- Punishment Hero Section -->
            <div class="punishment-hero">
                <div class="hero-content">
                    <div class="hero-text">
                        <h1>Punishment for Officer ${userAccount.fullname}</h1>
                        <p>Review vehicle details and issue punishment as needed.</p>
                    </div>
                    <a href="#vehicle-details" class="btn-custom">View Details</a>
                </div>
            </div>

            <!-- Vehicle and Punishment Details Section -->
            <div class="container mt-5" id="vehicle-details">
                <h2 class="text-center">Vehicle and Punishment Details</h2>

                <!-- Vehicle Information Frame -->
                <div class="info-frame">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="info-item"><strong>Plate Number:</strong> ${vehicle.plateNumber}</div>
                            <div class="info-item"><strong>Brand:</strong> ${vehicle.brand.brandName}</div>
                            <div class="info-item"><strong>Model:</strong> ${vehicle.model.modelName}</div>
                            <div class="info-item"><strong>Engine Number:</strong> ${vehicle.engineNumber}</div>
                            <div class="info-item"><strong>Manufacture Year:</strong> ${vehicle.manufactureYear}</div>
                        </div>

                        <div class="col-md-4">
                            <div class="info-item"><strong>Owner:</strong> ${vehicle.users.fullname}</div>
                            <div class="info-item"><strong>Email:</strong> ${vehicle.users.email}</div>
                            <div class="info-item"><strong>Phone:</strong> ${vehicle.users.phone}</div>
                            <div class="info-item"><strong>Address:</strong> ${vehicle.users.address}</div>
                        </div>

                        <div class="col-md-4">
                            <div class="info-item"><strong>Station Name:</strong> ${vehicle.inspectionRecords.inspectionStations.name}</div>
                            <div class="info-item"><strong>Inspection Date:</strong> ${vehicle.inspectionRecords.inspectionDate}</div>
                            <div class="info-item"><strong>CO2 Emission:</strong> ${vehicle.inspectionRecords.CO2Emission}</div>
                            <div class="info-item"><strong>HC Emission:</strong> ${vehicle.inspectionRecords.HCEmission}</div>
                            <div class="info-item"><strong>Inspection Result:</strong>
                                <span class="
                                      <c:choose>
                                          <c:when test="${vehicle.inspectionRecords.result == 'Pass'}">text-success fw-bold</c:when>
                                          <c:when test="${vehicle.inspectionRecords.result == 'Fail'}">text-danger fw-bold</c:when>
                                          <c:when test="${vehicle.inspectionRecords.result == 'Pending'}">text-warning fw-bold</c:when>
                                          <c:when test="${vehicle.inspectionRecords.result == 'Testing'}">text-primary fw-bold</c:when>
                                          <c:otherwise>text-secondary</c:otherwise>
                                      </c:choose>
                                      ">
                                    ${vehicle.inspectionRecords.result}
                                </span>
                            </div>
                            <div class="info-item"><strong>Comment:</strong> ${vehicle.inspectionRecords.comments}</div>
                        </div>
                    </div>
                </div>

                <!-- Punishment Form -->
                <div class="punishment-section mt-4">
                    <h3><b>Punishment</b></h3>
                    <form action="notification" method="get">
                        <input type="hidden" name="action" value="punishment" />
                        <input type="hidden" name="ownerID" value="${vehicle.users.userId}" />
                        <input type="hidden" name="vID" value="${vehicle.vehicleID}" />
                        <div class="mb-3">
                            <label for="note" class="form-label"><strong>Violation Details:</strong></label>
                            <textarea name="note" id="note" rows="4" class="form-control punishment-textarea" placeholder="Enter violation details...">Lỗi vi phạm:</textarea>
                        </div>
                        <button type="submit" class="btn btn-danger submit-btn">Send Punishment</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>