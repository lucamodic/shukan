<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
	<title>Welcome</title>
</head>
<body class="fondo">
	<div class="container">
			<form:form action="validar-login" method="POST"
					   modelAttribute="datosLogin" class="midclass">
				<c:if test="${not empty success}">
					<h3>
						<span>${success}</span>
					</h3>
				</c:if>
				<label class="titulos" for="email">E-mail Adress</label>
				<form:input placeholder="email@email.com" path="email" id="email" type="email"
							class="margin" />
				<label class="titulos" for="password">Password</label>
				<form:input placeholder="****************" path="password" type="password" id="password"
							class="margin" />
				<br>

				<button class="login" Type="Submit">Log in</button>
				<a class="signin" href="registrar-usuario">Sign in</a>
				<br>
				<c:if test="${not empty error}">
					<h3>
						<span id="error">${error}</span>
					</h3>
					<br>
				</c:if>
			</form:form>
			${msg}
	</div>
<script src="js/login.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>
</html>
