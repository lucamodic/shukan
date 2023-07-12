<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
    <title>Register</title>
</head>
<body class="fondo">
    <div class="container">
        <form:form class="midclass" action="registrarme" method="POST" modelAttribute="usuario">
            <label class="titulos" for="usuario">Username</label>
            <form:input placeholder="username" path="usuario" type="text" id="nickname" class="margin" />
            <c:if test="${error.equalsIgnoreCase('Username already chosen')}">
                <h3>
                    <span>${error}</span>
                </h3>
                <br>
            </c:if>
            <label class="titulos" for="usuario">E-mail Adress</label>
            <form:input placeholder="email@email.com" path="email" type="email" id="email" class="margin" autocomplete="on" />
            <c:if test="${error.equalsIgnoreCase('Email format is incorrect') ||
                        error.equalsIgnoreCase('Email already chosen')}">
                <h3>
                    <span>${error}</span>
                </h3>
                <br>
            </c:if>
            <label class="titulos" for="usuario">Password</label>
            <form:input placeholder="*************" path="password" type="password" id="password"
                        class="margin" />
            <c:if test="${error.equalsIgnoreCase('Password must be at least 8 characters')}">
                <h3>
                    <span>${error}</span>
                </h3>
                <br>
            </c:if>
            <br>
            <button id="btn-registrarme" class="login"
                    Type="submit">Sign in</button>
            <a class="signin" href="login">Go back to Log in</a>
            <c:if test="${error.equalsIgnoreCase('All fields must be filled')}">
                <br>
                <h3>
                    <span>${error}</span>
                </h3>
                <br>
            </c:if>
        </form:form>


    </div>

    <script src="js/login.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>
</html>
