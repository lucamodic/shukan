<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link href="css/home.css" rel="stylesheet">
	<title>Shukan</title>
</head>
<body>
	<div class="rodeado">
		<div class="columnas">
			<div class="parte-arriba p1">
				<h3 class="titulo username">Username</h3>
				<button class="pfp">
					<img src="${usuario.fotoPerfil}" class="imagenPf"/>
				</button>
			</div>
			<div class="parte-abajo">
				<h3 class="titulo">Health</h3>
				<div class="barras">
					<div class="vida-total divs-barras">
						<div class="vida-actual divs-barras">
							<h4 class="vida-texto">x/y</h4>
						</div>
					</div>
				</div>
				<h3 class="titulo">Level X</h3>
				<div class="barras">
					<div class="nivel-total divs-barras">
						<div class="nivel-actual divs-barras">
							<h4 class="nivel-texto">x/y</h4>
						</div>
					</div>
				</div>
				<div class="streak">
					<div class="streak-inside">
						<h3 class="titulo">Streak</h3>
						<img class="streak-img" src="images/firebw.png"/>
					</div>
					<h3 class="titulo">Y Days</h3>
				</div>
			</div>
		</div>
		<div class="columnas">
			<div class="parte-arriba">
				<div class="card">
					<h3 class="titulo titulo-card">Tasks</h3>
					<div>
						<button class="btn-add"> <img src="images/hand.png" class="hand"/> Add task</button>
					</div>
				</div>
			</div>
			<div class="parte-abajo">
				<div class="card">
					<h3 class="titulo titulo-card">Habits</h3>
					<div>
						<button class="btn-add">  <img src="images/hand.png" class="hand"/> Add Habit</button>
					</div>
				</div>
			</div>
		</div>
		<div class="columnas">
			<div class="parte-arriba"><div class="card">
				<h3 class="titulo titulo-card">Missions</h3>
				<div>
					<button class="btn-add">  <img src="images/hand.png" class="hand"/> Add Mission</button>
				</div>
			</div></div>
			<div class="parte-abajo"></div>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
</body>
</html>