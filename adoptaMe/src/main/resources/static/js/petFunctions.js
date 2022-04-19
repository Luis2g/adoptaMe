const removeRequest = (id) => {	

	const removalForm = document.getElementById("removalForm" + id);
	
	Swal.fire({
	  title: '¿Esta seguro?',
	  text: "Su solicitud sera cancelada",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar'
	}).then((result) => {
	  if (result.isConfirmed) {
	    removalForm.submit();
	  }
	});
	
}

const postConfirmation = (id) => {
	
	const formForPosting = document.getElementById("formForPosting" + id);
	
	Swal.fire({
	  title: '¿Esta seguro?',
	  text: "La mascota sera publicada para todo el publico",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar'
	}).then((result) => {
	  if (result.isConfirmed) {
	    	formForPosting.submit();
	  }
	})
	
}

const postRejection = (id) => {
	
	const formForRejection = document.getElementById("formForRejection" + id);
	
	Swal.fire({
	  title: '¿Esta seguro?',
	  text: "La mascota no sera publicada",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar'
	}).then((result) => {
	  if (result.isConfirmed) {
	    	formForRejection.submit();
	  }
	});
	
}


const confirmBeforeRequesting = (id, petName) => {
	
	let confirmBeforeRequestingForm = document.getElementById("requestAPetForm"+id);
	
	Swal.fire({
	  title: 'Esta a punto de solicitar la adopción',
	  html: "<div class='alert alert-primary' role='alert'> <strong>Nota: </strong>La solicitud sera entregada al propietario de <strong>"+petName+"</strong>, la adopción queda sujeta a la decisión de su dueño</div>",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar'
	}).then((result) => {
	  if (result.isConfirmed) {
	    	confirmBeforeRequestingForm.submit();
	  }
	});
	
}

const confirmationUpdateRejected = () => {
	let formPetUpdateRejected = document.getElementById("formPetUpdateRejected");
	
	Swal.fire({
		  title: 'Esta a punto de solicitar una revalidación para publicar su mascota',
		  html: "<div class='alert alert-primary' role='alert'><strong>Atención: </strong> Será dirigido a un formulario para actualizar la información de su mascota, posteriormente será presentada al administrador para su revalidación.</div>",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Aceptar'
		}).then((result) => {
		  if (result.isConfirmed) {
		    	formPetUpdateRejected.submit();
		  }
		});
	
}


const confirmationBeforeCreation = () => {
	
            
            let flagName = false;
            let flagAge = false;
            let flagUnitAge = false;
            let flagType = false;
            let flagSize = false;
            let flagColor = false;
            let flagCaracter = false;
            let flagImage = false;
            let flagDescription = false;
	
	let formForPetCreation = document.getElementById("formForPetCreation");
	let formForPetErrorUpdate = document.getElementById("formForPetErrorUpdate");
	
	
        var blacklist = [",", ";", "/*", "*/", "@@", "@", "SELECT", "select", "script", "<script","Script","UPDATE",
            "update", "DELETE", "delete", "input", "button",
            "div", "html", "char", "varchar", "nvarchar", "hooks.js",
            "int", "integer", "String", "sys", "sysobjects",
            "sysobject", "puto", "puta", "pendejo", "idiota", "estupido",
            "estúpido", "estupideces", "idioteces", "pendejadas",
            "encabronarse", "cabron", "cabrón", "chingada", "verga",
            "pito", "joder", "jodido", "jodete", "imbécil", "imbecil",
            "culero", "panocha", "fuck", "dick", "asshole", "ass",
            "bitch", "son of a bitch", "pussy", "nigga", "nigger",
            "deep throat", "bbc", "cock", "motherfucker", "fucker"];
            
             var blacklist2 = ["@@", "SELECT", "select", "script", "<script","UPDATE",
            "update", "DELETE", "delete", "input", "button",
            "div", "html", "char", "varchar", "nvarchar", "hooks.js",
            "int", "integer", "String", "sys", "sysobjects",
            "sysobject", "puto", "puta", "pendejo", "idiota", "estupido",
            "estúpido", "estupideces", "idioteces", "pendejadas",
            "encabronarse", "cabron", "cabrón", "chingada", "verga",
            "pito", "joder", "jodido", "jodete", "imbécil", "imbecil",
            "culero", "panocha", "fuck", "dick", "asshole", "ass",
            "bitch", "son of a bitch", "pussy", "nigga", "nigger",
            "deep throat", "bbc", "cock", "motherfucker", "fucker"];

            let name = document.getElementById('name').value.toLowerCase();
            let age = document.getElementById('age').value.toLowerCase();
            let unitAge = document.getElementById('unitAge').value.toLowerCase();
            let type = document.getElementById('type').value.toLowerCase();
            let size = document.getElementById('size').value.toLowerCase();
            let color = document.getElementById('color').value.toLowerCase();
            let caracter = document.getElementById('caracter').value.toLowerCase();
            let description = tinymce.get('description').getContent({format : 'raw'}).toLowerCase();
            let image = document.getElementById('imagenPet').value;
            
            let updatePet = document.getElementById('updatePet');
            if (updatePet != null){
				updatePet = updatePet.value;
			}else{
				updatePet = null;
			}
			
            let updatePetRejected = document.getElementById('updatePetRejected');
            if (updatePetRejected != null){
				updatePetRejected = updatePetRejected.value;
			}else{
				updatePetRejected = null;
			}
			
            
            let updatePetAccepted = document.getElementById('updatePetAccepted');
            if (updatePetAccepted != null){
				updatePetAccepted = updatePetAccepted.value;
			}else{
				updatePetAccepted = null;
			}

            for (var i = 0; i < blacklist.length; i++) {
                if (name.includes((blacklist[i]).toLowerCase())) {                    
                    document.getElementById("nameValid").removeAttribute("hidden");
                    flagName = true;
                }
            }
            
            for (var i = 0; i < blacklist.length; i++) {
                if (age.includes(blacklist[i].toLowerCase())) {                    
                    document.getElementById("ageValid").removeAttribute("hidden");
                    flagAge = true;
                }
            }
            
            for (var i = 0; i < blacklist.length; i++) {
                if (unitAge.includes(blacklist[i].toLowerCase())) {                    
                    document.getElementById("unitAgeValid").removeAttribute("hidden");
                    flagUnitAge = true;
                }
            }
            
            for (var i = 0; i < blacklist.length; i++) {
                if (type.includes(blacklist[i].toLowerCase), 0) {                    
                    document.getElementById("typeValid").removeAttribute("hidden");
                    flagType = true;
                }
            }
            
            for (var i = 0; i < blacklist.length; i++) {
                if (size.includes(blacklist[i].toLowerCase())) {                    
                    document.getElementById("sizeValid").removeAttribute("hidden");
                    flagSize = true;
                }
            }
            
            for (var i = 0; i < blacklist.length; i++) {
                if (color.includes(blacklist[i].toLowerCase())) {                    
                    document.getElementById("colorValid").removeAttribute("hidden");
                    flagColor = true;
                }
            }
            
            for (var i = 0; i < blacklist.length; i++) {
                if (caracter.includes(blacklist[i].toLowerCase())) {                    
                    document.getElementById("caracterValid").removeAttribute("hidden");
                    flagCaracter = true;
                }
            }

            for (var i = 0; i < blacklist2.length; i++) {
                if (description.includes(blacklist2[i])) {
                    document.getElementById("descriptionValid").removeAttribute("hidden");
                    flagDescription = true;
                }
            }

            /*if(title == ""){
                document.getElementById("titleVoid").removeAttribute("hidden");
                flag = true;
            }

            if(content == '<p><br data-mce-bogus="1"></p>'){
                document.getElementById("contentVoid").removeAttribute("hidden");
                flag2 = true;
            }*/

			if(updatePet || updatePetAccepted || updatePetRejected){
				flagImage = false;
			}else{
	            if(image == ""){
	                document.getElementById("imagenValid").removeAttribute("hidden");
	                flagImage = true;
	            }
			}
			
			if(flagName || flagAge || flagUnitAge || flagType || flagSize || flagColor || flagCaracter || flagDescription || flagImage){
				
			}else{
				if(flagName == false && flagAge == false && flagUnitAge == false, flagType == false && flagSize== false && flagColor == false, flagCaracter == false && flagDescription == false && flagImage == false){
				if(updatePet){
					Swal.fire({
					  title: 'Esta a punto de actualizar una mascota',
					  html: "<div class='alert alert-primary' role='alert'><strong>Atención: </strong> La mascota será actualizada con los nuevos datos que has ingresado, (si no seleccionaste una imagen se quedará la imagen anteriormente registrada), el administrador decidirá si es apta para publicarla, mientras tanto se mantedrá en espera tu publicación.</div>",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Aceptar'
					}).then((result) => {
					  if (result.isConfirmed) {
					    	formForPetCreation.submit();
					    	formForPetErrorUpdate.submit();
					  }
					});
				}else if(updatePetRejected){
					Swal.fire({
					  title: 'Esta a punto de actualizar una mascota para su revalidación',
					  html: "<div class='alert alert-primary' role='alert'><strong>Atención: </strong> La mascota será presentada al administrador para que la mascota sea revalidada, él decidirá si es apta para ser publicada, mientras tanto se mantedrá en espera tu publicación.</div>",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Aceptar'
					}).then((result) => {
					  if (result.isConfirmed) {
					    	formForPetCreation.submit();
					    	formForPetErrorUpdate.submit();
					  }
					});
				}else if(updatePetAccepted){
					Swal.fire({
					  title: 'Esta a punto de actualizar una mascota',
					  html: "<div class='alert alert-primary' role='alert'><strong>Atención: </strong> La mascota será actualizada con los nuevos datos que has ingresado, (si no seleccionaste una imagen se quedará la imagen anteriormente registrada).</div>",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Aceptar'
					}).then((result) => {
					  if (result.isConfirmed) {
					    	formForPetCreation.submit();
					    	formForPetErrorUpdate.submit();
					  }
					});
				}else{
					Swal.fire({
						  title: 'Esta a punto de registrar una mascota',
						  html: "<div class='alert alert-primary' role='alert'><strong>Atención: </strong> La mascota será presentada al administrador quien decidirá si es apta para ser publicada, mientras tanto se mantedrá en espera tu publicación.</div>",
						  icon: 'warning',
						  showCancelButton: true,
						  confirmButtonColor: '#3085d6',
						  cancelButtonColor: '#d33',
						  confirmButtonText: 'Aceptar'
						}).then((result) => {
						  if (result.isConfirmed) {
						    	formForPetCreation.submit();
						    	formForPetErrorUpdate.submit();
						  }
						});	
					
				}
                
            }else{
            }
			}
            
	
	
}

//This function is just to show information in the volunteer view
$(document).ready(() => {
	$(".btn-justInformationVolunteer").on("click", function () {
		
		if($(this).data("username") != undefined){
			
			document.getElementById("adoptedPetImageJustInfo").setAttribute("src","/imagenesPet/"+$(this).data("image"));
			document.getElementById("adoptedPetNameJustInfo").innerHTML = $(this).data("name");
			document.getElementById("adoptedPetGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
			document.getElementById("adoptedPetDescriptionJustInfo").innerHTML = $(this).data("description");
			document.getElementById("adoptedPetSizeJustInfo").innerHTML = $(this).data("size");
			document.getElementById("adoptedPetColorJustInfo").innerHTML = $(this).data("color");
			document.getElementById("adoptedPetPersonalityJustInfo").innerHTML = $(this).data("personality");
			document.getElementById("adoptedPetAgeJustInfo").innerHTML = $(this).data("age")+" "+$(this).data("unit-age");
			document.getElementById("petAdopterUsernameJustInfo").innerHTML = $(this).data("username");
			document.getElementById("petAdopterNameJustInfo").innerHTML = $(this).data("adoptername");
			document.getElementById("petAdopterSurnameJustInfo").innerHTML = $(this).data("adoptersurname");
			document.getElementById("petAdopterSecondSurnameJustInfo").innerHTML = $(this).data("adoptersecondsurname");
			document.getElementById("petAdopterEmailJustInfo").innerHTML = $(this).data("adopteremail");
			document.getElementById("petAdopterPhoneNumberJustInfo").innerHTML = $(this).data("adopterphonenumber");
			
			
			$('#modalForAdoptedPetJustForInformation').modal('show');
		}else{
			
			document.getElementById("petImageJustInfo").setAttribute("src","/imagenesPet/"+$(this).data("image"));
			document.getElementById("petNameJustInfo").innerHTML = $(this).data("name");
			document.getElementById("petGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
			document.getElementById("petDescriptionJustInfo").innerHTML = $(this).data("description");
			document.getElementById("petSizeJustInfo").innerHTML = $(this).data("size");
			document.getElementById("petColorJustInfo").innerHTML = $(this).data("color");
			document.getElementById("petPersonalityJustInfo").innerHTML = $(this).data("personality");
			document.getElementById("petAgeJustInfo").innerHTML = $(this).data("age")+" "+$(this).data("unit-age");
			
			$('#modalForJustForInformation').modal('show');
		}
		
		
		
		
		
	
	});

//This function show information for adopter view
	$(".btn-justInformationAdopter").on("click", function () {
		document.getElementById("petImageJustInfo").setAttribute("src","/imagenesPet/"+$(this).data("image"));
		document.getElementById("petNameJustInfo").innerHTML = $(this).data("name");
		document.getElementById("petGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
		document.getElementById("petDescriptionJustInfo").innerHTML = $(this).data("description");
		document.getElementById("petSizeJustInfo").innerHTML = $(this).data("size");
		document.getElementById("petColorJustInfo").innerHTML = $(this).data("color");
		document.getElementById("petPersonalityJustInfo").innerHTML = $(this).data("personality");
		document.getElementById("petAgeJustInfo").innerHTML = $(this).data("age")+" "+$(this).data("unit-age");
		
		$('#modalForJustForInformation').modal('show');
	
	});
	
//This functions shows information about a pet in landing page
	$(".btn-justInformationEveryone").on("click", function () {
		
		let probando = document.getElementById("innerLayoutForLanding");
		
		document.getElementById("footerForAdoption").removeChild(probando);
		
		
		let layout = document.createElement("div");
		layout.setAttribute("id", "innerLayoutForLanding");
		document.getElementById("footerForAdoption").appendChild(layout);
		
		document.getElementById("petImageJustInfo").setAttribute("src","/imagenesPet/"+$(this).data("image"));
		document.getElementById("petNameJustInfo").innerHTML = $(this).data("name");
		document.getElementById("petGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
		document.getElementById("petDescriptionJustInfo").innerHTML = $(this).data("description");
		document.getElementById("petSizeJustInfo").innerHTML = $(this).data("size");
		document.getElementById("petColorJustInfo").innerHTML = $(this).data("color");
		document.getElementById("petPersonalityJustInfo").innerHTML = $(this).data("personality");
		document.getElementById("petAgeJustInfo").innerHTML = $(this).data("age")+" "+$(this).data("unit-age");
		
		if($(this).data("status") !== 'nada' && $(this).data("status") !== 'requestedByYou' && $(this).data("status") !== 'lovedIt' ){
			
		
			//formFLP = formForLandingPage
			let formFLP = document.createElement("form");
			formFLP.setAttribute("action", "/mascotas/requestAdoption");
			formFLP.setAttribute("method", "POST");	
			formFLP.setAttribute("id", "requestAPetForm" + $(this).data('petid'));
			let petIdInput = document.createElement("input");
			petIdInput.setAttribute("name", "petId");
			petIdInput.setAttribute("type", "hidden");
			petIdInput.setAttribute("value", $(this).data("petid"));
			let locationInput = document.createElement("input");
			locationInput.setAttribute("type", "hidden");
			locationInput.setAttribute("value", "landingPage");
			locationInput.setAttribute("name", "location");

			console.log($(this).data("session"));

			if($(this).data("session") === 'ROLE_ADOPTER' ){
				let buttonFLP = document.createElement("button");
				buttonFLP.setAttribute("class", "btn btn-success");
				buttonFLP.setAttribute("type", "button");
				buttonFLP.setAttribute("onClick", 'confirmBeforeRequesting(' + $(this).data('petid') +", \'" + $(this).data('name') + "\'"+ ')');
				buttonFLP.innerHTML = "Solicitar adopción";
				formFLP.appendChild(buttonFLP);

			}


			formFLP.appendChild(petIdInput);
			formFLP.appendChild(locationInput);
			
			
//			footer.appendChild(formFLP);
			document.getElementById("innerLayoutForLanding").appendChild(formFLP);
		}

		$('#modalForJustForInformation').modal('show');
		
	
	});
	
	
	$(".btn-justInformationAdmin").on("click", function () {
		
		console.log("Entra a la función");
		
		document.getElementById("adoptedPetImageJustInfo").setAttribute("src","/imagenesPet/"+$(this).data("image"));
			document.getElementById("adoptedPetNameJustInfo").innerHTML = $(this).data("name");
			document.getElementById("adoptedPetGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
			document.getElementById("adoptedPetDescriptionJustInfo").innerHTML = $(this).data("description");
			document.getElementById("adoptedPetSizeJustInfo").innerHTML = $(this).data("size");
			document.getElementById("adoptedPetColorJustInfo").innerHTML = $(this).data("color");
			document.getElementById("adoptedPetPersonalityJustInfo").innerHTML = $(this).data("personality");
			document.getElementById("adoptedPetAgeJustInfo").innerHTML = $(this).data("age")+" "+$(this).data("unit-age");
			document.getElementById("petAdopterUsernameJustInfo").innerHTML = $(this).data("username");
			document.getElementById("petAdopterNameJustInfo").innerHTML = $(this).data("adoptername");
			document.getElementById("petAdopterSurnameJustInfo").innerHTML = $(this).data("adoptersurname");
			document.getElementById("petAdopterSecondSurnameJustInfo").innerHTML = $(this).data("adoptersecondsurname");
			document.getElementById("petAdopterEmailJustInfo").innerHTML = $(this).data("adopteremail");
			document.getElementById("petAdopterPhoneNumberJustInfo").innerHTML = $(this).data("adopterphonenumber");
		
		$('#modalAdoptedPetWithOwnerInformation').modal('show');
	
	});
	
});


