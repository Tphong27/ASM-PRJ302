<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center" style="height: 95vh;">
            <div class="col-md-5 login-container">
                <h3 class="text-center">Login</h3>

                <form action="${pageContext.request.contextPath}/Login" method="post">

                    <div class="mb-3">
                        <label class="form-label text-white">Email:</label>
                        <input type="text" name="Email" value="${Email}" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label text-white">Password:</label>
                        <input type="password" name="Password" class="form-control" required>
                    </div>

                    <div style="color: red">${errorMessage}</div>

                    <div class="mb-3">
                        <button type="submit" class="btn btn-custom">Login</button>
                    </div>
                </form>

                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/Register" class="text-dark">Don't have an account? Sign up now</a>
                </div>

            </div>
        </div>
        <div class="footer">
            <p>&copy; PRJ302. All rights reserved.</p>
        </div>
    </body>
</html>
