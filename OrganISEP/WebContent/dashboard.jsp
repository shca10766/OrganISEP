<div id="filtre_statut">
	<a href="#" class="active" onclick="display_myEtiq(this, 'all')">Tous les événements</a> | <a href="#" onclick="display_myEtiq(this, 'my')">Mes événements</a> | <a href="#">Brouillons</a>
</div>

<div id="dashEvents">
	<div id="filtre_events">
		<div id="filtre_date">
			Date : 
			<input type="checkbox" id="passe" name="passe" onclick="filtre_function()"><label for="passe">Passé</label>
			<input type="checkbox" id="futur" name="futur" onclick="filtre_function()" checked><label for="futur">A venir</label>
		</div>
		<div id="filtre_validation">
			Statut : 
			<input type="checkbox" id="statut_val" name="statut_val" onclick="filtre_function()" checked><label for="statut_val">Validé</label>
			<input type="checkbox" id="statut_vsr" name="statut_vsr" onclick="filtre_function()"><label for="statut_vsr">Validé sous réserve</label>
			<input type="checkbox" id="statut_cours" name="statut_cours" onclick="filtre_function()"><label for="statut_cours">En cours</label>
			<input type="checkbox" id="statut_ref" name="statut_ref" onclick="filtre_function()"><label for="statut_ref">Refusé</label>
		</div>
	</div>
	<div id="list_events">
	
	</div>
</div>

<script>

	function displayEvents(r) {
		for (var i = 0; i < r.length; i++) {
			var validation = "";
			var etiquette = document.createElement("div");
			etiquette.classList.add("etiquette");
			
			if (r[i].validation == 1) { 
				etiquette.classList.add("statut_val");
				validation = "Validé";
			}
			else if (r[i].validation == 2) { 
				etiquette.classList.add("statut_vsr");
				validation = "Validé sous réserve";
			}
			else if (r[i].validation == 3) { 
				etiquette.classList.add("statut_cours"); 
				validation = "En cours";
			}
			else { 
				etiquette.classList.add("statut_ref"); 
				validation = "Refusé";
			}
			
			if (r[i].etat == 1) { etiquette.classList.add("futur"); }
			else { etiquette.classList.add("passe"); }
			
			if ("<%= request.getParameter("creat")%>" == r[i].creat) {
				etiquette.classList.add("creat");
			}
			
			var content_img = document.createElement("div");
			content_img.classList.add("content_img");
			
			var imgEvent = document.createElement("img");
			imgEvent.setAttribute("src", r[i].image);
			imgEvent.setAttribute("width", "100%");
			
			var content_event = document.createElement("div");
			content_event.classList.add("content_event");
			
			var titre_event = document.createElement("h3");
			titre_event.classList.add("titre_event");
			titre_event.innerText = r[i].titre;
			let event = r[i];
			titre_event.addEventListener('click', function(){
			    getEvent(event);
			});
			
			var sousTitre_event = document.createElement("h5");
			sousTitre_event.classList.add("sousTitre_event");
			var myDate = new Date("\'" + r[i].date + "\'");
			sousTitre_event.innerText = "Le " +  r[i].date + " à " + r[i].time;
			
			var salle_event = document.createElement("div");
			salle_event.classList.add("salle_event");
			salle_event.innerText = "Salles : ";
			for (var j = 0; j < r[i].salles.length; j++) {
				if (j == r[i].salles.length - 1) {
					salle_event.innerText += r[i].salles[j];
				}
				else {
					salle_event.innerText += r[i].salles[j] + " | ";
				}
			}
			
			var creat_content = document.createElement("figure");
			creat_content.classList.add("creat_content");
			
			var imCreat = document.createElement("img");
			imCreat.setAttribute("src", r[i].imCreat);
			
			var nom_creat = document.createElement("figcaption");
			nom_creat.innerText = r[i].creat;
			
			var validation_content = document.createElement("div");
			validation_content.classList.add("validation_content");
			validation_content.innerText = validation;
			
			document.getElementById("list_events").appendChild(etiquette);
			
			etiquette.appendChild(content_img);
			content_img.appendChild(imgEvent);
			
			etiquette.appendChild(content_event);
			content_event.appendChild(titre_event);
			content_event.appendChild(sousTitre_event);
			content_event.appendChild(salle_event);
			
			content_event.appendChild(creat_content);
			creat_content.appendChild(imCreat);
			creat_content.appendChild(nom_creat);
			
			content_event.appendChild(validation_content);
			
			filtre_feature();
			
		}
	}
	
	function getData() {
		var xhr = getXMLHttpRequest();
		
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
				result = JSON.parse(xhr.responseText);
				displayEvents(result);
	        }
		};

		xhr.open("GET", "EventServlet?action=etiquettes", true);
		xhr.send(null);
	}
	
	function getXMLHttpRequest() {
		var xhr = null;
		
		if (window.XMLHttpRequest || window.ActiveXObject) {
			if (window.ActiveXObject) {
				try {
					xhr = new ActiveXObject("Msxml2.XMLHTTP");
				} catch(e) {
					xhr = new ActiveXObject("Microsoft.XMLHTTP");
				}
			} else {
				xhr = new XMLHttpRequest(); 
			}
		} else {
			alert("Votre navigateur ne supporte pas l'objet XMLHTTPRequest...");
			return null;
		}
		
		return xhr;
	}
	
	function filtre_feature() {
		var box_date = document.getElementById("filtre_date").getElementsByTagName("input");
		var box_statut = document.getElementById("filtre_validation").getElementsByTagName("input");
		
		var filtre_date = [];
		for (var i = 0; i < box_date.length; i++) {
			if (box_date[i].checked) {
				filtre_date.push(box_date[i].id);
			}
		}
		var filtre_statut = [];
		for (var j = 0; j < box_statut.length; j++) {
			if (box_statut[j].checked) {
				filtre_statut.push(box_statut[j].id);
			}
		}
		display_etiquette(filtre_date, filtre_statut);
	}
	
	function display_etiquette(filtre_date, filtre_statut) {
		var etiquettes = document.getElementsByClassName("etiquette");
		for (var i = 0 ; i < etiquettes.length; i++) {
			etiquettes[i].style.display = "none";
			for (var j = 0 ; j < filtre_date.length ; j++) {
				for (var k = 0; k < filtre_statut.length ; k++) {
					if (etiquettes[i].classList.contains(filtre_date[j]) && etiquettes[i].classList.contains(filtre_statut[k])) {
						etiquettes[i].style.display = "flex";
					}
				}
			}
		}
	}
	
	function display_myEtiq(e, type) {
		if (!e.classList.contains("active")) {
			var filtres = document.getElementById("filtre_statut").children;
			for (var i = 0; i < filtres.length; i++) {
				if (filtres[i].classList.contains("active")) {
					filtres[i].classList.remove("active");
				}
			}
			e.classList.add("active");
		}
		var etiquettes = document.getElementsByClassName("etiquette");
		filtre_feature();
		if (type == "my") {
			for (var j = 0; j < etiquettes.length; j++) {
				if (!etiquettes[j].classList.contains("creat")) {
					etiquettes[j].style.display = "none";
				}
			}
		}
	}
	
	function filtre_function() {
		var statut = document.getElementById("filtre_statut").children;
		console.log(statut[0].classList);
		if (statut[0].classList.contains("active")) {
			display_myEtiq(statut[0], 'all');
		}
		else {
			display_myEtiq(statut[1], 'my');
		}
	}
	
	function getEvent(event) {
		
		var pageDash = document.getElementById("dashboard").children;
		pageDash[0].style.display = "none";
		pageDash[1].style.display = "block";
		displayDetails(event);
	}
</script>

