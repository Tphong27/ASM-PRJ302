<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Choose Inspector</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form2.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="Station.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewRegisList">
                                <i class="bi bi-list-check"></i> Inspection Registration List
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewCheckList">
                                <i class="bi bi-clipboard-check"></i> CheckList
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

        <div class=" container main-content">
            <div class="choose-inspector-container">
                <h2 class="choose-inspector-title">Inspection Details</h2>
                <form action="${pageContext.request.contextPath}/inspectionRecords" method="post">
                    <input type="hidden" name="action" value="chooseInspector">
                    <input type="hidden" name="id" value="${reID}">
                    <input type="hidden" name="date" value="${InspectionDate}">
                    <input type="hidden" name="staid" value="${stationid}">
                    <input type="hidden" name="veid" value="${vehicleid}">

                    <div class="choose-inspector-row">
                        <div class="choose-inspector-col">
                            <label class="choose-inspector-label"><strong>Vehicle Plate:</strong></label>
                            <p class="choose-inspector-value">${vehicle.plateNumber}</p>
                        </div>
                        <div class="choose-inspector-col">
                            <label class="choose-inspector-label"><strong>Inspection Date:</strong></label>
                            <p class="choose-inspector-value">${InspectionDate}</p>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="choose-inspector-label"><strong>Select Inspector</strong></label>
                        <select name="inspectionId" class="choose-inspector-select">
                            <c:forEach items="${listInspector}" var="ls">
                                <option value="${ls.userId}">${ls.fullname}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="choose-inspector-button">Send</button>
                </form>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>