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
		<div class="comment">
			<i class="fas fa-comment-alt"></i><span id="comment" data-toggle="modal" data-target="#commentModal" onclick="displayComment()"> Commenter</span>
		</div>
	</div>
</div>

<div class="modal fade" id="commentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog .modal-dialog-scrollable modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Commentaires</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        	<button type="button" class="btnModal" data-dismiss="modal">Envoyer</button>
      </div>
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
	
	var comment = document.getElementById("comment");
	comment.innerText = "Commenter";
	comment.setAttribute("style", "color:rgb(112, 112, 112); font-weight: bold");
	for (var j = 0; j < event.comments.length; j++) {
		if (!event.comments[j].read && "<%= request.getParameter("creat")%>" != event.comments[j].creat) {
			comment.innerText = "Commentaire non lu";
			comment.setAttribute("style", "color:#A8334A; font-weight: bold");
			break;
		}
	}
}

function displayComment(){
	
}

function returnDash() {
	var pageDash = document.getElementById("dashboard").children;
	pageDash[0].style.display = "block";
	pageDash[1].style.display = "none";
}
</script>