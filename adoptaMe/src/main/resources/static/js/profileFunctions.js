const comparePasswords = () => {
	
	let oldPassword = document.getElementById("oldPassword").value;
	let newPassword = document.getElementById("newPassword").value;
	let confirmationPassword = document.getElementById("confirmationPassword").value;
	let form = document.getElementById("changePassword");
	
	if(oldPassword === "" || oldPassword === undefined ||
		newPassword === "" || newPassword === undefined ||
		confirmationPassword === "" || confirmationPassword === undefined)
	{
		
		Swal.fire({ icon: 'error', title: 'Por favor verifique', text: '¡Todas las contraseñas deben ser ingresadas!'});
	}else if(newPassword.length < 8 || oldPassword.length < 8 || confirmationPassword.length < 8) {
		Swal.fire({ icon: 'error', title: 'Por favor verifique', text: '¡Las contraseñas deben tener un minimo de ocho caracteres!'});
	}else{
		if(newPassword === confirmationPassword){
			Swal.fire({
			  title: '¿Esta seguro que desea cambiar la contraseña?',
			  text: "Al realizar esta acción sera redireccionado al inicio de sesión",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  cancelButtonText: 'Cancelar',
			  confirmButtonText: 'Aceptar'
			}).then(result => {
			  if (result.isConfirmed) {
			    form.submit();
			  }
			})			
			return;
		}
		Swal.fire({ icon: 'error', title: '¡Por favor verifique!', text: '¡Las contraseñas no coinciden!'});
	}
}





