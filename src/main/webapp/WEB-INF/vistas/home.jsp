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
<script>
	var errorTask = "";
	var errorHabit = "";
	var errorMission = "";
	var usuarioJson = JSON.parse('${usuarioJson}');
</script>
	<div class="rodeado">
		<div class="columnas">

			<!-- PARTE USUARIO -->

			<div class="parte-arriba p1">
				<h3 class="titulo username">${usuario.usuario}</h3>

                <!--
                <button class="pfp">
                    <input type="file" id="fotoPerfil" name="fotoPerfil"
                           accept="image/*" />
					<img src="${usuario.fotoPerfil}" class="imagenPf"/>
				</button>
				-->
				<form class="cambiar-foto-perfil" method="POST"
					  enctype="multipart/form-data">
					<div class="boxFoto">
						<div class="perfil">
							<c:choose>
								<c:when test="${not empty usuario.fotoPerfil}">
									<img id="imgPerfil" class="imagenPerfil"
										 src="<c:url value="/images/fotosPerfil/${usuario.fotoPerfil}"/>">
								</c:when>
								<c:otherwise>
									<img id="imgPerfil" class="imagenPerfil"
										 src="<c:url value="/images/fotosPerfil/default.jpg"/>">
								</c:otherwise>
							</c:choose>
							<br> <input type="file" id="fotoPerfil" name="fotoPerfil"
										accept="image/*" />
						</div>
					</div>
				</form>

			</div>

			<!-- VIDA -->

			<div class="parte-abajo">
				<h3 class="titulo">Health</h3>
				<div class="barras">
					<div class="vida-total divs-barras">
						<div id="vida-actual" class="vida-actual divs-barras">

						</div>
						<h4 class="vida-texto">${usuario.actualHealth}/${usuario.totalHealth}</h4>
					</div>
				</div>

				<!-- NIVEL -->

				<h3 class="titulo">Level ${usuario.level}</h3>
				<div class="barras">
					<div class="nivel-total divs-barras">
						<div id="nivel-actual" class="nivel-actual divs-barras">
							<h4 class="nivel-texto"></h4>
						</div>
					</div>
				</div>

				<!-- STREAK -->

				<div class="streak">
					<div class="streak-inside">
						<h3 class="titulo">Streak</h3>
						<c:if test="${usuario.streakToday}">
							<img title="Habits done!" class="streak-img" src="images/fire5.png"/>
						</c:if>
						<c:if test="${!usuario.streakToday}">
							<img title="Do your habits to keep the streak going!" class="streak-img" src="images/fire0.png"/>
						</c:if>
					</div>
					<h3 class="titulo">${usuario.streak} Days</h3>
				</div>
			</div>
		</div>
		<div class="columnas">

			<!-- TASKS -->

			<div class="parte-arriba">
				<div class="card">
					<h3 class="titulo titulo-card">Tasks</h3>
					<div class="tasks-on">
						<c:forEach items="${goals}" var="goal">
							<div class="goals">
								<c:if test="${goal.type == 'TASK' &&  goal.activado == false &&  goal.hecho == false}">
									<div class="container-goals">
										<button class="completar ${goal.id} accion"
												value="${goal.id}"><img src="images/checkPurple.png" class="icono finish"></button>
										<div class="texto-goals">
											<h4 class="goal-name ${goal.id}" id="${goal.id}">${goal.name}</h4>
										</div>
										<button class="borrar ${goal.id} accion"
												value="${goal.id}"><img src="images/trash.png" class="icono eliminate"></button>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</div>
					<div>
						<button class="btn-add add-task"> <img src="images/hand.png" class="hand"/> Add task</button>
					</div>
				</div>
			</div>

			<!-- HABITS -->

			<div class="parte-abajo">
				<div class="card">
					<h3 class="titulo titulo-card">Habits</h3>
					<div class="tasks-on">
						<c:forEach items="${goals}" var="goal">
							<div class="goals">
								<c:if test="${goal.type == 'HABIT' &&  goal.activado == false &&  goal.hecho == false}">
									<div class="container-goals">
										<button class="completar-habit ${goal.id} accion"
												value="${goal.id}"><img src="images/checkPurple.png" class="icono finish"></button>
										<div class="texto-goals">
											<h4 class="goal-name ${goal.id}" id="${goal.id}">${goal.name}</h4>
										</div>
										<button class="borrar ${goal.id} accion"
												value="${goal.id}"><img src="images/trash.png" class="icono eliminate"></button>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</div>
					<div>
						<button class="btn-add add-habit">  <img src="images/hand.png" class="hand"/> Add Habit</button>
					</div>
				</div>
			</div>
		</div>
		<div class="columnas">

			<!-- MISSIONS -->

			<div class="parte-arriba"><div class="card">
				<h3 class="titulo titulo-card">Missions</h3>
				<div class="tasks-on">
					<c:forEach items="${goals}" var="goal">
						<div class="goals">
							<c:if test="${goal.type == 'MISSION' &&  goal.activado == false &&  goal.hecho == false}">
								<div class="container-goals">
									<button class="completar ${goal.id} accion"
											value="${goal.id}"><img src="images/checkPurple.png" class="icono finish"></button>
									<div class="texto-goals">
										<h4 class="goal-name ${goal.id}" id="${goal.id}">${goal.name}</h4>
									</div>
									<button class="borrar ${goal.id} accion"
											value="${goal.id}"><img src="images/trash.png" class="icono eliminate"></button>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
				<div>
					<button class="btn-add add-mission"> <img src="images/hand.png" class="hand"/> Add Mission</button>
				</div>
			</div>
			</div>

			<!-- FINISHED -->

			<div class="parte-abajo">
					<div class="card finished-goals">
						<!-- FINISHED TASKS -->

					<h3 class="titulo titulo-card">Finished Tasks</h3>
					<div class="finished finished-tasks">
						<c:forEach items="${goals}" var="goal">

							<c:if test="${goal.type == 'TASK' && goal.activado}">
								<h4 class="goal-name task-not-finished">${goal.name}</h4>
							</c:if>
							<c:if test="${goal.type == 'TASK' && goal.hecho}">
								<h4 class="goal-name task-finished">${goal.name}</h4>
							</c:if>

						</c:forEach>
					</div>

						<!-- FINISHED  MISSIONS -->

					<h3 class="titulo titulo-card">Finished Missions</h3>
					<div class="finished finished-mission">
						<c:forEach items="${goals}" var="goal">

							<c:if test="${goal.type == 'MISSION' && goal.activado}">
								<h4 class="goal-name task-not-finished">${goal.name}</h4>
							</c:if>
							<c:if test="${goal.type == 'MISSION' && goal.hecho}">
								<h4 class="goal-name task-finished">${goal.name}</h4>
							</c:if>

						</c:forEach>
					</div>

						<!-- FINISHED  HABITS -->

						<h3 class="titulo titulo-card">Finished Habits</h3>
						<div class="finished finished-habits">
							<c:forEach items="${goals}" var="goal">
								<c:if test="${goal.type == 'HABIT' && goal.hecho}">
									<h4 class="goal-name task-finished">${goal.name}</h4>
								</c:if>

							</c:forEach>
						</div>
				</div>

			</div>
		</div>
	</div>

	<!-- ADD TASKS -->

	<dialog class="modal-tasks modal">
		<div class="title-modal">
			<h3 class="titulo titulo-card">Add Task</h3>

			<button class="close-task close">&#10006;</button>
		</div>
		<form:form class="midclass" action="save-task" method="POST" modelAttribute="goal">
			<label class="titulos" for="name">Name</label>
			<form:input placeholder="Do laundry" path="name" type="text" id="name" class="fields" />
			<label class="titulos" for="description">Description</label>
			<form:input placeholder="Do laundry and iron it" path="description" type="text" id="description" class="fields" />
			<label class="titulos" for="experience">Experience</label>
			<form:input placeholder="30" path="experience" type="number" id="experience" class="fields" />
			<label class="titulos" for="damage">Damage</label>
			<form:input placeholder="5" path="damage" type="number" id="damage" class="fields" />
			<label class="titulos" for="limitDate">How long to complete</label>
			<form:input placeholder="3 (1-7 Days)" path="limitDate" type="number" id="limitDate" class="fields" />
			<button id="btn-save-task" class="save"
					Type="submit">Save</button>
			<c:if test="${not empty errorTask}">
				<h4 class="titulos error">${errorTask}</h4>
				<script>errorTask = "${errorTask}";</script>
			</c:if>
		</form:form>
	</dialog>

	<!-- ADD MISSIONS -->

	<dialog class="modal-mission modal">
		<div class="title-modal">
			<h3 class="titulo titulo-card">Add Mission</h3>

			<button class="close-mission close">&#10006;</button>
		</div>
		<form:form class="midclass" action="save-mission" method="POST" modelAttribute="goal">
			<label class="titulos" for="name">Name</label>
			<form:input placeholder="Read a book" path="name" type="text" id="name" class="fields" />
			<label class="titulos" for="description">Description</label>
			<form:input placeholder="Read the hobbit" path="description" type="text" id="description" class="fields" />
			<label class="titulos" for="experience">Experience</label>
			<form:input placeholder="30" path="experience" type="number" id="experience" class="fields" />
			<label class="titulos" for="damage">Damage</label>
			<form:input placeholder="5" path="damage" type="number" id="damage" class="fields" />
			<label class="titulos" for="limitDate">How long to complete</label>
			<form:input placeholder="9 (8+ Days)" path="limitDate" type="number" id="limitDate" class="fields" />
			<button id="btn-save-mission" class="save"
					Type="submit">Save</button>
			<c:if test="${not empty errorMission}">
				<h4 class="titulos error">${errorMission}</h4>
				<script>errorMission = "${errorMission}";</script>
			</c:if>
		</form:form>
	</dialog>

	<!-- ADD HABIT -->

	<dialog class="modal-habit modal">
		<div class="title-modal">
			<h3 class="titulo titulo-card">Add Habit</h3>

			<button class="close-habit close">&#10006;</button>
		</div>
		<form:form class="midclass" action="save-habit" method="POST" modelAttribute="goal">
			<label class="titulos" for="name">Name</label>
			<form:input placeholder="Read" path="name" type="text" id="name" class="fields" />
			<label class="titulos" for="description">Description</label>
			<form:input placeholder="Read 10 pages" path="description" type="text" id="description" class="fields" />
			<label class="titulos" for="experience">Experience</label>
			<form:input placeholder="10" path="experience" type="number" id="experience" class="fields" />
			<label class="titulos" for="damage">Damage</label>
			<form:input placeholder="5" path="damage" type="number" id="damage" class="fields" />
			<button id="btn-save-mission" class="save"
					Type="submit">Save</button>
			<c:if test="${not empty errorHabit}">
				<h4 class="titulos error">${errorHabit}</h4>
				<script>errorHabit = "${errorHabit}";</script>
			</c:if>
		</form:form>
	</dialog>

	<dialog class="modal-nivel modal">
		<div class="nivel-title">
			<h3>You have leveled up!</h3>
			<h3>Congratulations!</h3>
		</div>
		<br>
		<button class="close-nivel close">&#10006;</button>
	</dialog>

	<!-- JAVASCRIPT -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/home.js" type="text/javascript"></script>
	<script>
        var goalsJson = JSON.parse('${goalsJson}');
	</script>
</body>
</html>