<!doctype html>
<html lang="en">
	<head>
    	<!-- Required meta tags -->
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	 	<link rel="stylesheet" type="text/css" href="style.css">
    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
		<title><%=(request.getAttribute("action") == null) ? "OrganISEP" : request.getAttribute("action") + " | OrganISEP"%></title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"> </script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  	</head>
  	<body>
  		<% try { %>		
			<jsp:useBean id="user" beanName="user" scope="session" type="organisep.bean.LoginBean"/>
			
	    	<nav class="navbar sticky-top navbar-light">
	    		<span id="organIsep">
	    			<img src="./img/logo_organisep.png" width="100" alt="logo_Organisep">
	    		</span>
	    		<div id="infoUser">
		  			<img src="<%= user.getImage() %>" height="40" alt="logo_user">  
		  			<span id="name_user"><jsp:getProperty property="nom" name="user"/></span>
	    		</div>
			</nav>
		
			<div id="content_template">
				<!-- List group -->
				<div class="list-group" id="list_nav" role="tablist">
					  <a class="list-group-item list-group-item-action active" data-toggle="list" href="#dashboard" id="item_dashboard" role="tab"><i class="fas fa-tachometer-alt"></i> Tableau de bord</a>
					  <% if( user.getStatut() == 2 ) { %>
					  	<a class="list-group-item list-group-item-action" data-toggle="list" href="#edit" id="item_edit" role="tab"><i class="fas fa-pen"></i> Créer un événement</a>
					  <% } %>
					  <a class="list-group-item list-group-item-action" data-toggle="list" href="#settings" id="item_settings" role="tab"><i class="fas fa-cog"></i> Paramètres</a>
				</div>
				
				<!-- Tab panes -->
				<div class="tab-content">
					  <div class="tab-pane active" id="dashboard" role="tabpanel">
					  	<% if( request.getAttribute("action").equals("Tableau de bord") ) { %>
						  	<jsp:include page="dashboard.jsp" >
							  	<jsp:param name="creat" value="<%= user.getNom() %>" />
							</jsp:include>
						<% }
					  	else { %>
							<jsp:include page="event.jsp" >
							  	<jsp:param name="creat" value="<%= user.getNom() %>" />
							</jsp:include>
						<% } %>
					  </div>
					  <% if( user.getStatut() == 2 ) { %>
					  	<div class="tab-pane" id="edit" role="tabpanel">
					  		<%@include file="newEvent.jsp" %>
					  	</div>
					  <% } %>
					  <div class="tab-pane" id="settings" role="tabpanel">
					  	<%@include file="settings.jsp" %>
					  </div>
				</div>
			</div>
		
		<% } 
			
		catch (Exception e) {
			request.getRequestDispatcher("/index.jsp").include(request, response);
		} %>
		
		<script>
			window.addEventListener("load", function(event) {
		    	var action = "<%= request.getAttribute("action") %>";
		    	console.log(action);
		    	getData();
		    	
		    	if (action == "Tableau de bord" || action == "Evenement") { var result = "dashboard"; }
		    	else if (action == "Nouveau Evènement") { var result = "edit"; }
		    	else { var result = "settings"; }
		    	
		    	var listItems = document.getElementById("list_nav").children;
	    		for (var i = 0; i < listItems.length; i++) {
	    			if (!listItems[i].classList.contains("active") && listItems[i].id == "item_" + result) {
	    				listItems[i].classList.add("active");
	    				document.getElementById(result).classList.add("active");
	    			}
	    			else if (listItems[i].classList.contains("active") && listItems[i].id != "item_" + result) {
	    				listItems[i].classList.remove("active");
	    				document.getElementById(result).classList.remove("active");
	    			}
	    		}
		  	});
	  	</script>
		
  </body>
</html>