<div id="detail">
	<ol class="breadcrumb">
	    <li class="breadcrumb-item link"><a onclick="returnDash()" class="sousTitre_event">Tableau de bord</a></li>
	    <li class="breadcrumb-item active sousTitre_event" id=eventTitre aria-current="page"></li>
	    <li class="link" id="modif"> Modifier l'événement</li>
	</ol>
	<div class="etiquette" id="etiquette">
		<div class="content_img">
			<img id="eventIm" style="width: 100%;">
		</div>
		<div class="content_event">
			<h3 class="titre_event" id="eventTitleDetail"></h3>
			<h5 class="sousTitre_event" id="sousTitre_event"></h5>
			<div class="salle_event" id="salle_event"></div>
			<figure class="creat_content">
				<img id="creatIm">
				<figcaption id="figcaption"></figcaption>
			</figure>
			<div class="validation_content" id="validation_content"></div>
		</div>
		<div class="event_details">
			<h6 class="sousTitre_detail">Informations supplémentaires</h6>
			<div class="verticalLine"></div>
			<span>Description : </span>
			<p id="description"></p>
			<span>Ressources demandées : </span>
			<p id="ressources"></p>
		</div>
		<div class="event_details">
			<h6 class="sousTitre_detail">Coordonnées</h6>
			<div class="verticalLine"></div>
			<span>Reservation de salle : </span>
			<p id="salle"></p>
			<span>Demande de personnel : </span>
			<p id="personnel"></p>
			<span>Demande de materiel : </span>
			<p id="materiel"></p>
		</div>
	</div>
</div>

<script>
function displayDetails(event) {
	document.getElementById("eventTitre").innerText = event.titre;
	
	var etiquette = document.getElementById("etiquette")
	var validation ="";
	if (event.validation == 1) { 
		etiquette.classList.add("valide");
		validation = "Validé";
	}
	else if (event.validation == 2) { 
		etiquette.classList.add("vsr");
		validation = "Validé sous réserve";
	}
	else if (event.validation == 3) { 
		etiquette.classList.add("cours"); 
		validation = "En cours";
	}
	else { 
		etiquette.classList.add("refuse"); 
		validation = "Refusé";
	}
	
	if (event.etat == 1) { etiquette.classList.add("futur"); }
	else { etiquette.classList.add("passe"); }
	
	if ("<%= request.getParameter("creat")%>" == event.creat) {
		etiquette.classList.add("creat");
	}
	
	document.getElementById("eventIm").setAttribute("src", event.image);
	
	document.getElementById("eventTitleDetail").innerText = event.titre;
	
	document.getElementById("sousTitre_event").innerText = "Le " +  event.date;
	
	var salle_event = document.getElementById("salle_event");
	salle_event.innerText = "Salles : ";
	for (var j = 0; j < event.salles.length; j++) {
		if (j == event.salles.length - 1) {
			salle_event.innerText += event.salles[j];
		}
		else {
			salle_event.innerText += event.salles[j] + " | ";
		}
	}
	
	document.getElementById("creatIm").setAttribute("src", event.imCreat);
	
	document.getElementById("figcaption").innerText = event.creat;
	
	document.getElementById("validation_content").innerText = validation;
	
	document.getElementById("description").innerText = event.description;
}

/*function removePrecedentEvent(){
	var content = document.getElementById("detail");
	while (content.firstChild) {
		content.removeChild(content.firstChild);
	}
}*/

function returnDash() {
	var pageDash = document.getElementById("dashboard").children;
	pageDash[0].style.display = "block";
	pageDash[1].style.display = "none";
}
</script>