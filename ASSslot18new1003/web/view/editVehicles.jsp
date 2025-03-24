<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Vehicle</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
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
                    <ul class="navbar-nav w-100 d-flex justify-content-between">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?UserId=${userAccount.userId}&action=view">
                                <i class="bi bi-person-fill "></i> Personal Information
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
        <div class="container main-content">
            <div class="row justify-content-center mt-5">
                <div class="col-lg-8">
                    <div class="form-container">
                        <h2>🚗 Edit Vehicle</h2>
                        <form action="vehicles" method="post">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="vehicleid" value="${vehicle.vehicleID}">
                            <input type="hidden" name="userid" value="${userAccount.userId}">

                            <div class="form-group">
                                <label for="plateNumber">Plate Number</label>
                                <input type="text" name="PlateNumber" class="form-control" value="${vehicle.plateNumber}" required>
                            </div>

                            <div class="form-group">
                                <label for="brandId">Brand</label>
                                <select name="Brandid"  class="form-select">
                                    <c:forEach items="${brands}" var="br">
                                        <option value="${br.brandID}" 
                                                <c:if test="${br.brandID == vehicle.brandID}">selected="selected"</c:if>>
                                            ${br.brandName}
                                        </option>                           
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="modelId">Model</label>
                                <select name="Modelid"  class="form-select">
                                    <c:forEach items="${models}" var="md">
                                        <option value="${md.modelID}" 
                                                <c:if test="${md.modelID == vehicle.modelID}">selected="selected"</c:if>>
                                            ${md.modelName}
                                        </option>                            
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="manufactureYear">Manufacture Year</label>
                                <input type="number" name="ManufactureYear"  class="form-control" value="${vehicle.manufactureYear}" required>
                            </div>

                            <div class="form-group">
                                <label for="engineNumber">Engine Number</label>
                                <input type="text" name="EngineNumber" class="form-control" value="${vehicle.engineNumber}" required>
                            </div>

                            <div class="error-message">${error}</div>

                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary px-4">Change</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>

