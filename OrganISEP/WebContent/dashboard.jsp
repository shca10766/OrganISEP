<div id="filtre_statut">
	<a href="#" class="active">Tous les événements</a> | <a href="#">Mes évènments</a> | <a href="#">Brouillons</a>
</div>

<div id="dashEvents">
	<div id="filtre_events">
		<div id="filtre_date">
			Date : 
			<input type="checkbox" id="passe" name="passe"><label for="passe">Passé</label>
			<input type="checkbox" id="futur" name="futur" checked><label for="futur">A venir</label>
		</div>
		<div id="filtre_validation">
			Statut : 
			<input type="checkbox" id="statut_val" name="statut_val" checked><label for="statut_val">Validé</label>
			<input type="checkbox" id="statut_vsr" name="statut_vsr"><label for="statut_vsr">Validé sous réserve</label>
			<input type="checkbox" id="statut_cours" name="statut_cours"><label for="statut_cours">En cours</label>
			<input type="checkbox" id="statut_ref" name="statut_ref"><label for="statut_ref">Refusé</label>
		</div>
	</div>
	<div id="list_events">
	
	</div>
</div>

<script>

	function displayEvents(r) {
		for (var i = 0; i < r.events.length; i++) {
			var validation = "";
			var etiquette = document.createElement("div");
			etiquette.classList.add("etiquette");
			
			if (r.events[i].validation == 1) { 
				etiquette.classList.add("valide");
				validation = "Validé";
			}
			else if (r.events[i].validation == 2) { 
				etiquette.classList.add("vsr");
				validation = "Validé sous réserve";
			}
			else if (r.events[i].validation == 3) { 
				etiquette.classList.add("cours"); 
				validation = "En cours";
			}
			else { 
				etiquette.classList.add("refuse"); 
				validation = "Refusé";
			}
			
			if (r.events[i].etat == 1) { etiquette.classList.add("futur"); }
			else { etiquette.classList.add("passe"); }
			
			if ("<%= request.getParameter("creat")%>" == r.events[i].creat) {
				etiquette.classList.add("creat");
			}
			
			var content_img = document.createElement("div");
			content_img.classList.add("content_img");
			
			var imgEvent = document.createElement("img");
			imgEvent.setAttribute("src", r.events[i].image);
			imgEvent.setAttribute("width", "100%");
			
			var content_event = document.createElement("div");
			content_event.classList.add("content_event");
			
			var titre_event = document.createElement("h3");
			titre_event.classList.add("titre_event");
			titre_event.innerText = r.events[i].titre;
			
			var localCount = r.events[i]; // Using a local copy in case `count` is modified
			titre_event.onclick = function() {
				getEvent(localCount);
			};
			
			var sousTitre_event = document.createElement("h5");
			sousTitre_event.classList.add("sousTitre_event");
			sousTitre_event.innerText = "Le " +  r.events[i].date;
			
			var salle_event = document.createElement("div");
			salle_event.classList.add("salle_event");
			salle_event.innerText = "Salles : ";
			for (var j = 0; j < r.events[i].salles.length; j++) {
				if (j == r.events[i].salles.length - 1) {
					salle_event.innerText += r.events[i].salles[j];
				}
				else {
					salle_event.innerText += r.events[i].salles[j] + " | ";
				}
			}
			
			var creat_content = document.createElement("figure");
			creat_content.classList.add("creat_content");
			
			var imCreat = document.createElement("img");
			imCreat.setAttribute("src", r.events[i].imCreat);
			
			var nom_creat = document.createElement("figcaption");
			nom_creat.innerText = r.events[i].creat;
			
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
		
		for (var i = 0; i < box_date.length; i++) {
			if (box_date[i].checked && box_date[i].id == "futur") {
				//display_etiquette("futur");
			}
		}
	}
	
	function display_etiquette(filtre) {
		var etiquettes = document.getElementsByClassName("etiquette");
		for (var i = 0 ; i < etiquettes.length; i++) {
			if (etiquettes[i].classList.contains(filtre)) {
					etiquettes[i].style.display = "flex";
			}
			else {
				etiquettes[i].style.display = "none";
			}
		}
	}
	
	function getEvent(event) {
		
		var pageDash = document.getElementById("dashboard").children;
		pageDash[0].style.display = "none";
		pageDash[1].style.display = "block";
		displayDetails(event);
	}
</script>



