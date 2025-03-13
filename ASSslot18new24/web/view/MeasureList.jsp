<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inspector Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="view/Inspector.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?action=measureList">Vehicle Emission Measurement List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <h2 class="text-center">List of vehicles registered for inspection</h2>

            <input type="text" id="searchInput" class="form-control my-3" placeholder="Search for license plate number...">

            <table class="table table-striped table-bordered">
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
                                <td><input type="text" name="co2" value="" required="" /></td>
                                <td><input type="text" name="hc" value="" required=""/></td>

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
                                <td><input type="submit" value="Confirm" /></td>
                            </tr>
                            
                        </c:if>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>
</body>
</html>