<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
							<h4 class="vida-texto">${usuario.actualHealth}/${usuario.totalHealth}</h4>
						</div>
					</div>
				</div>
				<h3 class="titulo">Level ${usuario.level}</h3>
				<div class="barras">
					<div class="nivel-total divs-barras">
						<div class="nivel-actual divs-barras">
							<h4 class="nivel-texto">${usuario.actualExp}/${usuario.totalExp}</h4>
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
					<div class="tasks-on">

					</div>
					<div>
						<button class="btn-add add-task"> <img src="images/hand.png" class="hand"/> Add task</button>
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




	<dialog class="modal-tasks modal">
		<div>
			<h3 class="titulo titulo-card">Add Task</h3>
			<button class="close">X</button>
		</div>
		<form:form class="midclass" action="save-task" method="POST" modelAttribute="goal">
			<label class="titulos" for="name">Name</label>
			<form:input placeholder="Do laundry" path="name" type="text" id="name" class="margin" />
			<label class="titulos" for="description">Description</label>
			<form:input placeholder="Do laundry and iron it" path="description" type="text" id="description" class="margin" />
			<label class="titulos" for="experience">Experience</label>
			<form:input placeholder="30" path="experience" type="number" id="experience" class="margin" />
			<label class="titulos" for="damage">Damage</label>
			<form:input placeholder="5" path="damage" type="number" id="damage" class="margin" />
			<label class="titulos" for="limitDate">How long to complete</label>
			<form:input placeholder="3 (1-7 Days)" path="limitDate" type="number" id="limitDate" class="margin" />
			<button id="btn-save-task" class="save"
					Type="submit">Save</button>
			<h4>${error}</h4>
		</form:form>
	</dialog>







	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/home.js" type="text/javascript"></script>
</body>
</html>