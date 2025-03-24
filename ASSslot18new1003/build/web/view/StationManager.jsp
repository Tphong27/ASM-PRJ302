<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Station Management</title>
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
                <h1 class="text-center">Inspection Stations</h1>

                <form action="${pageContext.request.contextPath}/inspectionStation">
                    <input type="hidden" name="action" value="add">
                    <button type="submit" class="btn btn-primary" style="background-color: green">
                        Add new station
                    </button>
                    <h3 class="text-center text-white pt-1">.....</h3>
                </form>
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Station Name</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>!!!</th>
                                <th>!!!</th>
                                <th>!!!</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="st" items="${stations}">
                                <tr>
                                    <td>${st.name}</td>
                                    <td>${st.address}</td>
                                    <td>${st.phone}</td>
                                    <td>${st.email}</td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/inspectionStation">
                                            <input type="hidden" name="action" value="edit">
                                            <input type="hidden" name="id" value="${st.stationID}">
                                            <button type="submit" class="btn btn-primary px-4">Edit</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/inspectionStation" onsubmit="return confirm('Are you sure you want to delete this station?');">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="id" value="${st.stationID}">
                                            <button type="submit" class="btn btn-danger px-4">Delete</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/inspectionStation">
                                            <input type="hidden" name="action" value="viewStaff">
                                            <input type="hidden" name="id" value="${st.stationID}">
                                            <button type="submit" class="btn btn-info px-4">View Staff</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </body>
</html>