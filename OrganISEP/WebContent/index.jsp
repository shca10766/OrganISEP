<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="style.css">
		<title>Connexion | OrganISEP</title>
	</head>
	
	<body id="body_co">
		<img src="img/logoisep.png" alt="logo" height="36" width="90" id="logoisep"/>
		<div id="content_form">
			<div id="logo">
				<img src="img/logo_organisep.png" alt="logo" height="55" width="134"/>
			</div>
			<form method="post" action="/OrganISEP/LoginServlet" onsubmit="validate(event)">
				<div>
					<label for="login_email">Adresse Mail</label>
					<input type="email" id="login_email" name="login_email"/>
				</div>
				
				<div>
					<label for="login_mdp">Mot de passe</label>
					<input type="password" id="login_mdp" name="login_mdp"/>
				</div>
				
				<div>
					<span class="errorMessage"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
					<input type="submit" value="Connexion" class="btn">
				</div>
			</form>
			
			<div>
				<a href="#">Mot de passe oublié ?</a>
			</div>
		</div>
	</body>
	
	<script> 
		function validate(e) {
			var email = document.getElementById("login_email"); 
			var mdp = document.getElementById("login_mdp");
			
			email.style.borderColor = "initial";
 
 			if (email.value==null || email.value=="") { 
 				email.style.borderColor = "red";
 				e.preventDefault();
 			}
 			else if(mdp.value==null || mdp.value=="") { 
 				mdp.style.borderColor = "red";
 				e.preventDefault();
 			} 
		}
	</script>
</html>