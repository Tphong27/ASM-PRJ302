<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center" style="height: 95vh;">
            <div class="col-md-5 login-container">
                <h3 class="text-center">Create new account</h3>


                <form action="${pageContext.request.contextPath}/Register" method="post">
                    <div class="mb-3">
                        <label class="form-label">Username:</label>
                        <input type="text" name="Fullname" value="${param.Fullname}" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email:</label>
                        <input type="text" name="Email" value="${param.Email}" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password:</label>
                        <input type="password" name="Password" class="form-control" required>
                    </div>


                    <div style="color: red">${errorMessage1}</div>
                    <div style="color: red">${errorMessage}</div>
                    <div style="color: green">${success}</div>

                    <div class="mb-3">
                        <button type="submit" class="btn btn-custom">Register</button>
                    </div>
                </form>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/Login" class="text-dark">Already have an account? Sign in</a>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div class="footer">
            <p>&copy; PRJ302. All rights reserved.</p>
        </div>
    </body>
</html>

