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
    <title>Death</title>
</head>
<body>
    <script>var errorDeath = "";</script>
    <dialog class="modal-death modal">
        <div class="death-title">
            <c:if test="${not empty errorDeath}">
                <h3 class="title-error error">${errorDeath}</h3>
                <script>errorDeath = "${errorDeath}";</script>
            </c:if>
        </div>
        <h4 class="death-error">Your HP has reached 0 <br>
            Your account will be deleted</h4>
        <form:form class="midclass" action="gameover" method="POST" modelAttribute="goal">
            <button id="dead" class="dead"
                    Type="submit">Log out</button>
        </form:form>
    </dialog>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
    </script>
    <script src="js/death.js" type="text/javascript"></script>
</body>
</html>
