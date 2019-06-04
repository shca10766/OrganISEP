<ol class="breadcrumb" id="breadcrumb">
	<li class="breadcrumb-item link"><a onclick="returnDash()" class="sousTitre_event">Tableau de bord</a></li>
	<li class="breadcrumb-item link" id="linkEvent" class="sousTitre_event"></li>
	<li class="breadcrumb-item active sousTitre_event" id=eventTitre aria-current="page">Modification</li>
</ol>

<div id="modifyEvent">	
	<h3 id="titre"></h3>
	<form id="formCreatEvent" method="post" action="/OrganISEP/EventServlet">
		<div id="msgErrorEdit"  style="display: none;"><i class="fas fa-exclamation-triangle"></i>Les champs comportant une * sont obligatoires</div>
		<div class="form-row">
			<div class="form-group col-md-8" style="display: none;">
	    		<label for="editId">Id</label>
	    		<input type="number" class="form-control" id="editId">
	    	</div>
	    	<div class="form-group col-md-8">
	    		<label for="editNom">Nom de l'événement *</label>
	    		<input type="text" class="form-control" id="editNom">
	    	</div>
	    	<div class="form-group col-md-4">
	      		<label for="editPart">Nombre approximatif de participants *</label>
	      		<input type="number" class="form-control" id="editPart">
	    	</div>
	  	</div>
		<div class="form-row">
	    	<div class="form-group col-md-4">
	      		<label for="editDate" id="actualDate"></label>
	      		<input type="date" class="form-control" id="editDate">
	      		<input type="text" class="form-control" id="actualDateInput" style="display : none;">
	    	</div>
	    	<div class="form-group col-md-4">
	      		<label for="editTime">Heure de début *</label>
	      		<input type="time" class="form-control" id="editTime">
	    	</div>
	    	<div class="form-group col-md-4">
	      		<label for="editBudget">Budget</label>
	      		<span class="iconInput"><input type="text" class="form-control" id="editBudget"></span>
	    	</div>
	  	</div>
	  	<div class="form-row">
	    	<div class="form-group col-md-6">
	      		<label for="editSalle">Salles</label>
	      		<div class="input-group mb-2">
	        		<div class="input-group-prepend">
	          			<div data-toggle="modal" data-target="#modalEditSalle" class="input-group-text"><i class="fas fa-list"></i></div>
	        		</div>
	        		<input type="text" class="form-control" id="editSalle" placeholder="Cliquer sur l'icône pour réserver une salle" disabled>
	      		</div>
	    	</div>
	  	</div>
	  	<div class="form-row">
	  		<div class="form-group col-md-12 custom-file">
	    		<label for="inputEditImage" class="custom-file-label" id="inputEditImageLabel">Choisir une image</label>
	    		<input type="file" class="custom-file-input" id="inputEditImage" name="inputEditImage" onchange="modifEditLabel(this)">
	  		</div>
  		</div>
	  	<div id="formRess">
	  		Ressources
			<div class="form-row " id="edit_ress">
				<div class="form-group col-md-3">
					<input type="checkbox" id="editWifi" name="editWifi"><label for="editWifi">Accès Wifi</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editReseaux" name="editReseaux"><label for="editReseaux">Fournitures Réseaux</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editGardien" name="editGardien"><label for="editGardien">Gardien</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editCarEtab" name="editCarEtab"><label for="editCarEtab">Voitures dans l'établissement</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editElec" name="editElec"><label for="editElec">Chauffage & Eclairage</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editPersonExt" name="editPersonExt"><label for="editPersonExt">Entrée autorisée aux personnes extérieures</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editChaise" name="editChaise"><label for="editChaise">Supplément de chaises</label>
				</div>
				<div class="form-group col-md-3">
					<input type="checkbox" id="editTable" name="editTable"><label for="editTable">Supplément de tables</label>
				</div>
			</div>
		</div>
		<div class="form-row">
	    	<label for="editAutreRess" class="col-form-label">Ajouter une ressource</label>
	    	<div class="form-group col-sm-4">
	      		<input class="form-control" id="editAutreRess">
	    	</div>
	    	<div class="col-sm-3">
	      		<button type="button" class="btnModal" onclick="editRess()">Ajouter</button>
	    	</div>
	  	</div>
		<div class="form-group" id="divEditDesc">
	    	<label for="editDesc">Description de l'événement</label>
	    	<textarea class="form-control" id="editDesc" rows="3"></textarea>
	  	</div>  	
	  	<div id="btnFormEvent">
			<button class="btnForm" id="updateEventForm">Enregistrer et Envoyer</button>
		</div>
	</form>
</div>

<div class="modal fade" id="modalEditSalle" tabindex="-1" role="dialog" aria-labelledby="modalSalle" aria-hidden="true">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title" id="editTitleModalSalle">Réservation de salles</h5>
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        		</button>
      		</div>
      		<div class="modal-body">
      			<div class="text_modal">Cliquer sur les salles que vous souhaitez réserver</div>
        		<form>
        			<div id="editFormSalles" class="form-row">
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

function modifyEvent(event) {
	document.getElementById("editId").value = event.id;
	
	document.getElementById("linkEvent").innerText = event.titre;
	document.getElementById("linkEvent").addEventListener('click', function(){
		var pageDash = document.getElementById("dashboard").children;
		pageDash[1].style.display = "block";
		pageDash[2].style.display = "none";
	});
	
	document.getElementById("titre").innerText = "Modification de l'événement " + event.titre;
	document.getElementById("editNom").value = event.titre;
	document.getElementById("editPart").value = parseInt(event.participants);
	document.getElementById("actualDate").innerText = "Date : " + event.date;
	document.getElementById("actualDateInput").value = event.date;
	document.getElementById("editTime").value = event.time;
	document.getElementById("editBudget").value = event.budget;
	
	var splitImage = event.image.split('/');
	var image = splitImage[splitImage.length - 1];
	document.getElementById("inputEditImageLabel").innerText = image;
	
	var salles = "";
	for (var i = 0; i < event.salles.length; i++) {
		salles += event.salles[i] + " ; ";
	}
	document.getElementById("editSalle").value = salles;
	
	$('#modalEditSalle').on('show.bs.modal', function (Event) {
		var date = $('#editDate').val();
	    $.get('EventServlet', {
	    	date : date,
	    	type: 'salle',
	    	action: 'salle'
	    }, function(responseText) {
	    	editSalles(responseText, event);
	    });
	})
	
	document.getElementById("editDesc").value = event.description;
	
	
	for(var i=0; i<event.ressources.length; i++){
		if(event.ressources[i] == "Accès Wifi"){
			$('.form-row input[id="editWifi"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Fournitures Réseaux"){
			$('.form-row input[id="editReseaux"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Gardien"){
			$('.form-row input[id="editGardien"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Voitures dans l'établissement"){
			$('.form-row input[id="editCarEtab"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Chauffage & Eclairage"){
			$('.form-row input[id="editElec"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Entrée autorisée aux personnes extérieures"){
			$('.form-row input[id="editPersonExt"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Supplément de chaises"){
			$('.form-row input[id="editChaise"]').prop('checked', true);
		}
		else if(event.ressources[i] == "Supplément de tables"){
			$('.form-row input[id="editTable"]').prop('checked', true);
		}
		else {
			var contentRess = document.getElementById("addAutreRess");
			var nRess = contentRess.children.length + 1;
			var idRess = "ress" + event.ressources[i];
			var ress = document.getElementById("editAutreRess").value;
			document.getElementById("editAutreRess").value = "";
			
			var div = document.createElement("div");
			div.classList.add("form-group");
			div.classList.add("col-md-3");
			
			var input = document.createElement("input");
			input.setAttribute("type", "checkbox");
			input.id = idRess;
			input.setAttribute("name", idRess);
			
			var label = document.createElement("label");
			label.setAttribute("for", idRess);
			label.innerText = event.ressources[i];
			
			contentRess.appendChild(div);
			div.appendChild(input);
			div.appendChild(label);	
			$('.form-row input[id=' + idRess + ']').prop('checked', true);
		}
	}
}

function editSalles(l_salles, event) {
	var form = document.getElementById("editFormSalles");
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
			
			for(var j=0; j<event.salles.length; j++){
				if(l_salles[i] == event.salles[j]){
					$('.form-row input[id=' + input.id + ']').prop('checked', true);
					break;
				}
			}
		}
	}
}

function displayEditedSalles() {
	var divInput = document.getElementById("editFormSalles").children;
	var salles = "";
	for (var i = 0; i < divInput.length; i++) {
		if (divInput[i].children[0].checked) {
			salles += divInput[i].children[1].innerText + " ; ";
		}
	}
	document.getElementById("editSalle").value = salles;
}

function editRess() {
	var contentRess = document.getElementById("formRess").children[0];
	var nRess = contentRess.children.length + 1;
	var idRess = "ress" + nRess;
	var ress = document.getElementById("editAutreRess").value;
	document.getElementById("editAutreRess").value = "";
	
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
	
	$('.form-row input[id=' + idRess + ']').prop('checked', true);
}

$('#updateEventForm').click(function(event) {
	event.preventDefault();
    
	var id = $('#editId').val();
    var nom = $('#editNom').val();
    var nbrPart = $('#editPart').val();
    var date = $('#editDate').val();
    var actualDate = $('#actualDateInput').val();
    var time = $('#editTime').val();
    var budget = $('#editBudget').val();
    
    if (nom != "" && nbrPart != "" && time != "") {
    	var allSalles = [];
        $('#editFormSalles > div').each(function() {
        	if($(this)[0].children[0].checked) {
        		allSalles.push($(this)[0].children[0].id);
        	}
        });
        
        var lien = $('#editLien').val();
        

    	var allRess = [];
        $('#edit_ress > div').each(function() {
        	if($(this)[0].children[0].checked) {
        		allRess.push($(this)[0].innerText);
        	}
        });
        
        var splitImage = $('#inputEditImage').val().split('\\');
    	var image = splitImage[splitImage.length - 1];

    	var desc = $('#editDesc').val();
    	
    	$.post('EventServlet?action=update', {
        	IdEvent : id,
    		Nom : nom,
        	Nbr : nbrPart,
        	Date: date,
        	ActualDate : actualDate,
        	Time : time,
        	Budget: budget,
        	Salles: allSalles,
        	Ress: allRess,
        	Desc: desc,
        	Image: image,
        	}, function(responseText) {
        		var pageDash = document.getElementById("dashboard").children;
        		pageDash[1].style.display = "block";
        		pageDash[2].style.display = "none";
        		displayDetails(responseText);
        	});
    }
	else {
		$('#msgErrorEdit').css("display", "block");
		$('html,body').animate({
            scrollTop: 0
        }, 300);
	}
    
});

function modifEditLabel(e) {
	var image = e.value.split('\\');
	e.parentElement.children[0].innerText = image[image.length - 1];
}

</script>
