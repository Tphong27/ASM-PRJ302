<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Measure List</title>
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
                <a class="navbar-brand" href="view/Inspector.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?action=measureList">
                                <i class="bi bi-list-check"></i> Vehicle Emission Measurement List
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

        <div class="main-content">
            <div class="container mt-5">
                <h2 class="text-center">List of vehicles registered for inspection</h2>
                <table class="table table-striped table-bordered text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>Plate Number</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Manufacture Year</th>
                            <th>Engine Number</th>
                            <th>Registration Date</th>
                            <th>CO2</th>
                            <th>HC</th>
                            <th>Result Tested</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listRecords}" var="lr">
                        <form action="${pageContext.request.contextPath}/inspectionRecords">
                            <input type="hidden" name="id" value="${lr.vehicles.vehicleID}" />
                            <input type="hidden" name="action" value="measureVe" />
                            <input type="hidden" name="inspectorID" value="${userAccount.userId}" />
                            <input type="hidden" name="stationID" value="${lr.inspectionStations.stationID}" />
                            <input type="hidden" name="recordID" value="${lr.recordID}" />
                            <input type="hidden" name="date" value="${lr.inspectionDate}" />
                            <c:if test="${lr.result == 'Testing'}">
                                <tr>
                                    <td>${lr.vehicles.plateNumber}</td>
                                    <td>${lr.vehicles.brand.brandName}</td>
                                    <td>${lr.vehicles.model.modelName}</td>
                                    <td>${lr.vehicles.manufactureYear}</td>
                                    <td>${lr.vehicles.engineNumber}</td>
                                    <td>${lr.registrationDate}</td>
                                    <td><input type="text" name="co2" class="input-field" value="" required /></td>
                                    <td><input type="text" name="hc" class="input-field" value="" required /></td>
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
                                    <td><input type="submit" value="Confirm" class="confirm-btn" /></td>
                                </tr>
                            </c:if>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>