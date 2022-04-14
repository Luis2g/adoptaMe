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