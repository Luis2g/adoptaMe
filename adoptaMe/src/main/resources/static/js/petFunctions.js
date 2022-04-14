const removeRequest = (id) => {	

	const removalForm = document.getElementById("removalForm" + id);
	
	Swal.fire({
	  title: '¿Esta seguro?',
	  text: "Su solicitud sera cancelada",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar!'
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
	  confirmButtonText: 'Aceptar!'
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
	  confirmButtonText: 'Aceptar!'
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
	  confirmButtonText: 'Aceptar!'
	}).then((result) => {
	  if (result.isConfirmed) {
	    	confirmBeforeRequestingForm.submit();
	  }
	});
	
}


const confirmationBeforeCreation = () => {
	
	let formForPetCreation = document.getElementById("formForPetCreation");
	
	Swal.fire({
	  title: 'Esta a punto de registrar una mascota',
	  html: "<div class='alert alert-primary' role='alert'><strong>Atención: </strong> La mascota sera presentada a el administrador quien decidira si es apta para se publicada, mientras tanto se mantedra en espera tu publicación</div>",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar!'
	}).then((result) => {
	  if (result.isConfirmed) {
	    	formForPetCreation.submit();
	  }
	});
}

//This function is just to show information in the volunteer view
$(document).ready(() => {
	$(".btn-justInformationVolunteer").on("click", function () {
		document.getElementById("petNameJustInfo").innerHTML = $(this).data("name");
		document.getElementById("petGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
		document.getElementById("petDescriptionJustInfo").innerHTML = $(this).data("description");
		document.getElementById("petSizeJustInfo").innerHTML = $(this).data("size");
		document.getElementById("petColorJustInfo").innerHTML = $(this).data("color");
		document.getElementById("petPersonalityJustInfo").innerHTML = $(this).data("personality");
		document.getElementById("petAgeJustInfo").innerHTML = $(this).data("age");
		
		$('#modalForJustForInformation').modal('show');
	
	});

//This function show information for adopter view
	$(".btn-justInformationAdopter").on("click", function () {
		document.getElementById("petNameJustInfo").innerHTML = $(this).data("name");
		document.getElementById("petGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
		document.getElementById("petDescriptionJustInfo").innerHTML = $(this).data("description");
		document.getElementById("petSizeJustInfo").innerHTML = $(this).data("size");
		document.getElementById("petColorJustInfo").innerHTML = $(this).data("color");
		document.getElementById("petPersonalityJustInfo").innerHTML = $(this).data("personality");
		document.getElementById("petAgeJustInfo").innerHTML = $(this).data("age");
		
		$('#modalForJustForInformation').modal('show');
	
	});
	
//This functions shows information about a pet in landing page
	$(".btn-justInformationEveryone").on("click", function () {
		document.getElementById("petNameJustInfo").innerHTML = $(this).data("name");
		document.getElementById("petGenderJustInfo").innerHTML = $(this).data("gender") === 'M' ? 'Macho' : 'Hembra';
		document.getElementById("petDescriptionJustInfo").innerHTML = $(this).data("description");
		document.getElementById("petSizeJustInfo").innerHTML = $(this).data("size");
		document.getElementById("petColorJustInfo").innerHTML = $(this).data("color");
		document.getElementById("petPersonalityJustInfo").innerHTML = $(this).data("personality");
		document.getElementById("petAgeJustInfo").innerHTML = $(this).data("age");
		
		$('#modalForJustForInformation').modal('show');
	
	});
	
	
	
});


