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
            <div class="container mt-3">
                <h1 class="text-center">Choose Stations for ${staff.email}</h1>
                <c:choose>
                    <c:when test="${not empty Liststations}">
                        <form action="${pageContext.request.contextPath}/inspectionStation" method="post">
                            <input type="hidden" name="action" value="chooseStation" />
                            <input type="hidden" name="id" value="${staff.userId}" />
                            <div class="mb-3">
                                <select name="stationId" class="form-select">
                                    <c:forEach items="${Liststations}" var="ls">
                                        <option value="${ls.stationID}" 
                                                <c:if test="${ls.stationID == stationId}">selected="selected"</c:if>>
                                            ${ls.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="submit" value="Choose" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <h3 class="text-center" style="color: red">Not Station.</h3>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </body>
</html>
