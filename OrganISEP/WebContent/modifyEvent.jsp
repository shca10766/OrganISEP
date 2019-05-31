<ol class="breadcrumb" id="breadcrumb">
	<li class="breadcrumb-item link"><a onclick="returnDash()" class="sousTitre_event">Tableau de bord</a></li>
	<li class="breadcrumb-item link" id="linkEvent" class="sousTitre_event"></li>
	<li class="breadcrumb-item active sousTitre_event" id=eventTitre aria-current="page">Modification</li>
</ol>

<div id="modifyEvent">	
	<h3 id="titre"></h3>
	<form id="formCreatEvent" method="post" action="/OrganISEP/EventServlet">
		<div id="msgErrorCreat"  style="display: none;"><i class="fas fa-exclamation-triangle"></i>Les champs comportant une * sont obligatoires</div>
		<div class="form-row">
	    	<div class="form-group col-md-8">
	    		<label for="inputNom">Nom de l'événement *</label>
	    		<input type="text" class="form-control" id="inputNom">
	    	</div>
	    	<div class="form-group col-md-4">
	      		<label for="inputPart">Nombre approximatif de participants *</label>
	      		<input type="number" class="form-control" id="inputPart">
	    	</div>
	  	</div>
		<div class="form-row">
	    	<div class="form-group col-md-4">
	      		<label for="inputDate">Date *</label>
	      		<input type="date" class="form-control" id="inputDate">
	    	</div>
	    	<div class="form-group col-md-4">
	      		<label for="inputTime">Heure de début *</label>
	      		<input type="time" class="form-control" id="inputTime">
	    	</div>
	    	<div class="form-group col-md-4">
	      		<label for="inputBudget">Budget</label>
	      		<span class="iconInput"><input type="text" class="form-control" id="inputBudget"></span>
	    	</div>
	  	</div>
	  	<div class="form-row">
	    	<div class="form-group col-md-6">
	      		<label for="inputSalle">Salles</label>
	      		<div class="input-group mb-2">
	        		<div class="input-group-prepend">
	          			<div data-toggle="modal" data-target="#modalSalle" class="input-group-text"><i class="fas fa-list"></i></div>
	        		</div>
	        		<input type="text" class="form-control" id="inputSalle" placeholder="Cliquer sur l'icône pour réserver une salle" disabled>
	      		</div>
	    	</div>
	    	<div class="form-group col-md-6">
	      		<label for="inputLien">Lien de l'événement</label>
	      		<input type="text" class="form-control" id="inputLien" >
	    	</div>
	  	</div>
	  	<div id="formRess">
	  		Ressources
			<div class="form-row " id="list_ress">
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressWifi" name="ressWifi"><label for="ressWifi">Accès Wifi</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressReseaux" name="ressReseaux"><label for="ressReseaux">Fournitures Réseaux</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressGardien" name="ressGardien"><label for="ressGardien">Gardien</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressCarEtab" name="ressCarEtab"><label for="ressCarEtab">Voitures dans l'établissement</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressElec" name="ressElec"><label for="ressElec">Chauffage & Eclairage</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressPersonExt" name="ressPersonExt"><label for="ressPersonExt">Entrée autorisée aux personnes extérieures</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressChaise" name="ressChaise"><label for="ressChaise">Supplément de chaises</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="ressTable" name="ressTable"><label for="ressTable">Supplément de tables</label>
				</div>
			</div>
		</div>
		<div class="form-row">
	    	<label for="autreRess" class="col-form-label">Ajouter une ressource</label>
	    	<div class="form-group col-sm-4">
	      		<input class="form-control" id="autreRess">
	    	</div>
	    	<div class="col-sm-3">
	      		<button type="button" class="btnModal" onclick="editRess()">Ajouter</button>
	    	</div>
	  	</div>
		<div class="form-group" id="divDesc">
	    	<label for="textDesc">Description de l'événement</label>
	    	<textarea class="form-control" id="textDesc" rows="3"></textarea>
	  	</div>  	
	  	<div id="btnFormEvent">
			<button class="btnForm" id="updateEventForm">Enregistrer et Envoyer</button>
		</div>
		<button class="btnForm" id="annuler">Annuler</button>
	</form>
</div>

<div class="modal fade" id="modalSalle" tabindex="-1" role="dialog" aria-labelledby="modalSalle" aria-hidden="true">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title" id="titleModalSalle">Réservation de salles</h5>
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        		</button>
      		</div>
      		<div class="modal-body">
      			<div class="text_modal">Cliquer sur les salles que vous souhaitez réserver</div>
        		<form>
        			<div id="formSalles" class="form-row">
        			</div>
        		</form>
      		</div>
      		<div class="modal-footer">
        		<button type="button" class="btnModal" data-dismiss="modal">Valider</button>
      		</div>
    	</div>
  	</div>
</div>


<script>

$('#modalSalle').on('show.bs.modal', function (event) {
	var date = $('#inputDate').val();
    $.get('EventServlet', {
    	date : date,
    	type: 'salle',
    	action: 'salle'
    }, function(responseText) {
    	editSalles(responseText);
    });
})

function editSalles(l_salles) {
	var form = document.getElementById("formSalles");
	if (!form.classList.contains("loadData")) {
		form.classList.add("loadData");
		for (var i = 0; i < l_salles.length; i = i+2) {
			var div = document.createElement("div");
			div.classList.add("form-group");
			div.classList.add("col-md-3");
			
			var input = document.createElement("input");
			input.setAttribute("type", "checkbox");
			input.setAttribute("onchange", "displayEditedSalles()")
			input.setAttribute("name", l_salles[i] + "_" + l_salles[i]);
			input.id = l_salles[i] + "_" + l_salles[i+1];
			
			var label = document.createElement("label");
			label.setAttribute("for", l_salles[i] + "_" + l_salles[i+1]);
			label.innerText = l_salles[i];
			
			form.appendChild(div);
			div.appendChild(input);
			div.appendChild(label);
		}
	}
}

function displayEditedSalles() {
	var divInput = document.getElementById("formSalles").children;
	var salles = "";
	for (var i = 0; i < divInput.length; i++) {
		if (divInput[i].children[0].checked) {
			salles += divInput[i].children[1].innerText + " ; ";
		}
	}
	document.getElementById("inputSalle").value = salles;
}

function editRess() {
	var contentRess = document.getElementById("formRess").children[0];
	var nRess = contentRess.children.length + 1;
	var idRess = "ress" + nRess;
	var ress = document.getElementById("autreRess").value;
	document.getElementById("autreRess").value = "";
	
	var div = document.createElement("div");
	div.classList.add("form-group");
	div.classList.add("col-md-3");
	
	var input = document.createElement("input");
	input.setAttribute("type", "checkbox");
	input.id = idRess;
	input.setAttribute("name", idRess);
	
	var label = document.createElement("label");
	label.setAttribute("for", idRess);
	label.innerText = ress;
	
	contentRess.appendChild(div);
	div.appendChild(input);
	div.appendChild(label);	
}

$('#updateEventForm').click(function(event) {
	event.preventDefault();
    
    var nom = $('#inputNom').val();
    var nbrPart = $('#inputPart').val();
    var date = $('#inputDate').val();
    var time = $('#inputTime').val();
    var budget = $('#inputBudget').val();
    
    if (nom != "" && nbrPart != "" && date != "" && time != "") {
    	var allSalles = [];
        $('#formSalles > div').each(function() {
        	if($(this)[0].children[0].checked) {
        		allSalles.push($(this)[0].children[0].id);
        	}
        });
        
        var lien = $('#inputLien').val();
        

    	var allRess = [];
        $('#list_ress > div').each(function() {
        	if($(this)[0].children[0].checked) {
        		allRess.push($(this)[0].innerText);
        	}
        });

    	var desc = $('#textDesc').val();
    	
    	$.post('EventServlet?action=update', {
        	Nom : nom,
        	Nbr : nbrPart,
        	Date: date,
        	Time : time,
        	Budget: budget,
        	Salles: allSalles,
        	Lien: lien,
        	Ress: allRess,
        	Desc: desc,
        	IdCreat: "<%= request.getParameter("id")%>"
        	}, function(responseText) {
        		document.location.reload(true);
        	});
    }
	else {
		$('#msgErrorCreat').css("display", "block");
		$('html,body').animate({
            scrollTop: 0
        }, 300);
	}
    
});

function modifyEvent(event) {
	document.getElementById("linkEvent").innerText = event.titre;
	document.getElementById("linkEvent").addEventListener('click', function(){
	var pageDash = document.getElementById("dashboard").children;
		pageDash[1].style.display = "block";
		pageDash[2].style.display = "none";
	});
	
	document.getElementById("annuler").addEventListener('click', function(){
		var pageDash = document.getElementById("dashboard").children;
			pageDash[1].style.display = "block";
			pageDash[2].style.display = "none";
		});
	
	document.getElementById("titre").innerText = "Modification de l'événement " + event.titre;
	document.getElementById("inputNom").value = event.titre;
	document.getElementById("inputPart").value = event.participant;
	document.getElementById("inputDate").value = event.date;
	document.getElementById("inputTime").value = event.time;
	document.getElementById("inputBudget").value = event.budget;
	document.getElementById("textDesc").value = event.description;
	
	/*var divRess = document.getElementById("formRess").children;
	for(var i =0; i<divRess.length; i++){
		var inputRess = divRess[i].children;
		for(var j=0; j<event.ressources.length; j++){
			if(inputRess[0].innerText = event.ressources[j]){
				inputRess[0].classList.add("checked");
			}
		}
		
	}*/
	
}

</script>
