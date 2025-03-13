<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your Vehicles</title>
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

            <div class="row">

                <div class="col-md-6">
                    <div class="mb-3">
                        <label class="form-label"><strong>Plate Number:</strong></label>
                        <p class="form-control-plaintext">${vehicle.plateNumber}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Brand:</strong></label>
                        <p class="form-control-plaintext">${vehicle.brand.brandName}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Model:</strong></label>
                        <p class="form-control-plaintext">${vehicle.model.modelName}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Engine Number:</strong></label>
                        <p class="form-control-plaintext">${vehicle.engineNumber}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Manufacture Year:</strong></label>
                        <p class="form-control-plaintext">${vehicle.manufactureYear}</p>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="mb-3">
                        <label class="form-label"><strong>Inspection Date: </strong></label>
                        <p class="form-control-plaintext">${vehicle.inspectionRecords.inspectionDate}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>CO2 Emission: </strong></label>
                        <p class="form-control-plaintext">${vehicle.inspectionRecords.CO2Emission}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>HC Emission:</strong></label>
                        <p class="form-control-plaintext">${vehicle.inspectionRecords.HCEmission}</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Inspection Result:</strong></label>
                        <p>
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
                        </p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Comment: </strong></label>
                        <p class="form-control-plaintext">${vehicle.inspectionRecords.comments}</p>
                    </div>
                </div>

            </div>

            <div class="container mt-2">
                <c:if test="${vehicle.inspectionRecords.result != 'Pass'}">
                    <div class="container mt-5">
                        <h3 class="text-center mb-4">Inspection Registration for Vehicles ${vehicle.plateNumber}</h3>

                        <form action="${pageContext.request.contextPath}/inspectionRecords" method="post">
                            <input type="hidden" name="action" value="addRegistCar">
                            <input type="hidden" name="vehicleid" value="${vehicle.vehicleID}">

                            <div class="mb-3">
                                <label class="form-label">Select Inspection Station</label>
                                <select name="stationId" class="form-select">
                                    <c:forEach items="${listStation}" var="ls">
                                        <option value="${ls.stationID}" 
                                                <c:if test="${ls.stationID == stationId}">selected="selected"</c:if>>
                                            ${ls.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Select Inspection Date</label>
                                <input type="date" id="RegistrationDate" name="RegistrationDate" class="form-control" value="${RegistrationDate}" required="">
                            </div>

                            <div style="color: red">${errorMessage}</div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-primary px-4">Register</button>
                            </div>
                        </form>
                    </div>
                </c:if>
                <div class="container mt-3">
                    <h3 class="text-center text-white pt-5">.....</h3>
                </div>
            </div>
        </div>
    </body>
</html>
