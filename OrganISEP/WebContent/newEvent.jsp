<h3>Créer un événement</h3>
<form id="formCreatEvent">
	<div class="form-group">
    	<label for="inputNom">Nom de l'événement</label>
    	<input type="text" class="form-control" id="inputNom">
  	</div>
	<div class="form-row">
    	<div class="form-group col-md-6">
      		<label for="inputDate">Date</label>
      		<input type="date" class="form-control" id="inputDate">
    	</div>
    	<div class="form-group col-md-6">
      		<label for="inputTime">Heure de début</label>
      		<input type="time" class="form-control" id="inputTime">
    	</div>
  	</div>
  	<div class="form-row">
    	<div class="form-group col-md-6">
      		<label for="inputSalle">Salles</label>
      		<input type="text" class="form-control" id="inputSalle" data-toggle="modal" data-target="#modalSalle" placeholder="Clique ici pour réserver une salle">
    	</div>
    	<div class="form-group col-md-6">
      		<label for="inputRess">Ressources nécessaires</label>
      		<input type="text" class="form-control" id="inputRess">
    	</div>
  	</div>
</form>

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
        		<button type="button" class="btnModal" data-dismiss="modal">Annuler</button>
        		<button type="button" class="btnModal">Enregistrer (Brouillon)</button>
        		<button type="button" class="btnModal">Envoyer</button>
      		</div>
    	</div>
  	</div>
</div>

<script>

$('#modalSalle').on('show.bs.modal', function (event) {
	var date = $('#inputDate').val();
    $.get('ReservationServlet', {
    	date : date,
    	type: 'salle',
    }, function(responseText) {
    	displaySalles(responseText);
    });
})

function displaySalles(l_salles) {
	var form = document.getElementById("formSalles");
	form.innerHTML = "";
	
	for (var i = 0; i < l_salles.length; i++) {
		var div = document.createElement("div");
		div.classList.add("form-group");
		div.classList.add("col-md-3");
		
		var input = document.createElement("input");
		input.setAttribute("type", "checkbox");
		input.setAttribute("name", l_salles[i]);
		input.id = l_salles[i];
		
		var label = document.createElement("label");
		label.setAttribute("for", l_salles[i]);
		label.innerText = l_salles[i];
		
		form.appendChild(div);
		div.appendChild(input);
		div.appendChild(label);
	}
}
</script>