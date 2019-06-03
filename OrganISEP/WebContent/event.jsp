<div id="detail">
	<ol class="breadcrumb">
	    <li class="breadcrumb-item link"><a onclick="returnDash()" class="sousTitre_event">Tableau de bord</a></li>
	    <li class="breadcrumb-item active sousTitre_event" id=eventTitre aria-current="page"></li>
	    <li class="link" id="modif"> Modifier l'�v�nement</li>
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
			<h6 class="sousTitre_detail">Informations suppl�mentaires</h6>
			<div class="verticalLine"></div>
			<span>Description : </span>
			<p id="description"></p>
			<span>Ressources demand�es : </span>
			<p id="ressources"></p>
			<span>Budget : </span>
			<p id="budget"></p>
			<span>Nombre de participants : </span>
			<p id="participant"></p>
		</div>

		<div class="comment">
			<i id="commentI" class="fas fa-comment-alt"></i><span id="comment" data-toggle="modal" data-target="#commentModal"> Commenter</span>
		</div>
	</div>
</div>

<div class="modal fade" id="commentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Commentaires</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="discussion">
        
      </div>
      <div class="modal-footer">
      <form id="formNewComment" method="post" action="/OrganISEP/CommentServlet" style="width: 100%">
      	<div class="form-group">
    		<textarea class="form-control" id="newComment" rows="3" placeholder="Ajouter un commentaire"></textarea>
  		</div>
  		<div class="form-group">
    		<input class="form-control" id="eventTitle" rows="3" style="display: none"></input>
  		</div>
        <button type="button" class="btnModal" data-dismiss="modal">Envoyer</button>
      </form>
      </div>
    </div>
  </div>
</div>

<script>

function displayDetails(event) {
	document.getElementById("eventTitre").innerText = event.titre;
	
	document.getElementById("modif").addEventListener('click', function(){
		var pageDash = document.getElementById("dashboard").children;
		pageDash[1].style.display = "none";
		pageDash[2].style.display = "block";
	    modifyEvent(event);
	});
	
	var etiquette = document.getElementById("etiquette")
	var validation ="";
	if (event.validation == 1) { 
		etiquette.classList.add("valide");
		validation = "Valid�";
	}
	else if (event.validation == 2) { 
		etiquette.classList.add("vsr");
		validation = "Valid� sous r�serve";
	}
	else if (event.validation == 3) { 
		etiquette.classList.add("cours"); 
		validation = "En cours";
	}
	else { 
		etiquette.classList.add("refuse"); 
		validation = "Refus�";
	}
	
	if (event.etat == 1) { etiquette.classList.add("futur"); }
	else { etiquette.classList.add("passe"); }
	
	if ("<%= request.getParameter("creatNom")%>" == event.creat) {
		etiquette.classList.add("creat");
	}
	else {
		document.getElementById("modif").style.display = "none";
	}
	
	document.getElementById("eventIm").setAttribute("src", event.image);
	
	document.getElementById("eventTitleDetail").innerText = event.titre;
	
	document.getElementById("sousTitre_event").innerText = "Le " +  event.date + " � " + event.time;
	
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
	
	var ressources = document.getElementById("ressources");
	for (var j = 0; j < event.ressources.length; j++) {
		if (j == event.ressources.length - 1) {
			ressources.innerText += event.ressources[j];
		}
		else {
			ressources.innerText += event.ressources[j] + " | ";
		}
	}
	if (event.ressources.length == 0) {
		ressources.innerText = " / ";
	}
	
	document.getElementById("budget").innerText = event.budget;
	
	document.getElementById("participant").innerText = event.participants;
	
	var comment = document.getElementById("comment");
	
	if("<%= request.getParameter("creatNom")%>" == event.creat || "<%= request.getParameter("creatStatut")%>" == 1){
		comment.innerText = "Commenter";
		comment.setAttribute("style", "color:rgb(112, 112, 112); font-weight: bold");
		for (var j = 0; j < event.comments.length; j++) {
			if (!event.comments[j].read && "<%= request.getParameter("creatNom")%>" != event.comments[j].creat) {
				comment.innerText = "Commentaire non lu";
				comment.setAttribute("style", "color:#A8334A; font-weight: bold");
				break;
			}
		}
		document.getElementById("commentI").style.display = "block";
	}
	else {
		comment.style.display = "none";
		document.getElementById("commentI").style.display = "none";
	}
	
	$('#commentModal').on('show.bs.modal', function (Event) {
	    $.get('CommentServlet', {
	    	Event : event.titre,
	    	IdCreat: "<%= request.getParameter("idCreat")%>"
	    }, function(responseText) {
	    	displayComment(event);
	    });
	})
	
}

function displayComment(event){
	var discussion = document.getElementById("discussion");
	cleanComments(discussion);
	for (var i = 0; i < event.comments.length; i++) {
		var info;
		var message;
		if ("<%= request.getParameter("creatNom")%>" == event.comments[i].creat) {
			info = document.createElement("div");
			info.classList.add("infoOut");
			info.innerText = "le : " + event.comments[i].date;
			
			message = document.createElement("div");
			message.classList.add("messageOut");
			message.innerText = event.comments[i].content;
		}
		else {
			info = document.createElement("div");
			info.classList.add("infoIn");
			info.innerText = event.comments[i].creat + ", le : " + event.comments[i].date;
			
			message = document.createElement("div");
			message.classList.add("messageIn");
			message.innerText = event.comments[i].content;
			
		}
		discussion.appendChild(info);
		discussion.appendChild(message);
	}
	var eventTitle = document.getElementById("eventTitle");
	eventTitle.value = event.titre;
	
	var eventTitle = document.getElementById("newComment");
	eventTitle.value = "";
	
	
}

function cleanComments(discussion){
	while (discussion.firstChild) {
		discussion.removeChild(discussion.firstChild);
	}
}

$('#formNewComment').click(function(event) {
	event.preventDefault();
    var newComment = $('#newComment').val();
    var eventTitre = $('#eventTitle').val();
    if (newComment != "") {
    	$.post('CommentServlet?action=send', {
        	Comment : newComment,
        	Event : eventTitre,
        	IdCreat: "<%= request.getParameter("idCreat")%>"
        	}, function(responseText) {
        		result = JSON.parse(responseText);
        		displayDetails(result);
        	});
    }
	else {
		$('#msgErrorCreat').css("display", "block");
		$('html,body').animate({
        }, 300);
	}
});

$('#commentModal').on('hidden.bs.modal', function(event) {
	event.preventDefault();
	var eventTitre = $('#eventTitle').val();
	
	$.post('CommentServlet?action=close', {
		Event : eventTitre,
    	IdCreat: "<%= request.getParameter("idCreat")%>"
    	}, function(responseText) {
    		result = JSON.parse(responseText);
    		displayDetails(result);
    	});
});
</script>