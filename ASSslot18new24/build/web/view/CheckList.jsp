<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Owner Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="height: 100px;">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/view/Station.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.userId}&action=viewRegisList">Inspection Registration List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewCheckList">CheckList</a>
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
                <thead class="table-dark text-center">
                    <tr>
                        <th class="text-center">Plate Number</th>
                        <th class="text-center">Brand</th>
                        <th class="text-center">Model</th>
                        <th class="text-center">Manufacture Year</th>
                        <th class="text-center">Engine Number</th>
                        <th class="text-center">Registration Date</th>
                        <th class="text-center">CO2</th>
                        <th class="text-center">HC</th>
                        <th class="text-center">Result Tested</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listRecords}" var="lr">
                        <tr>
                            <td class="text-center align-middle">${lr.vehicles.plateNumber}</td>
                            <td class="text-center align-middle">${lr.vehicles.brand.brandName}</td>
                            <td class="text-center align-middle">${lr.vehicles.model.modelName}</td>
                            <td class="text-center align-middle">${lr.vehicles.manufactureYear}</td>
                            <td class="text-center align-middle">${lr.vehicles.engineNumber}</td>
                            <td class="text-center align-middle">${lr.registrationDate}</td>
                            <td class="text-center align-middle">${lr.CO2Emission}</td>
                            <td class="text-center align-middle">${lr.HCEmission}</td>
                            <td class="text-center align-middle">
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
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
