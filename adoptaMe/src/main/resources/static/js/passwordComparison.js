
console.log("imported correctly");

const comparePasswords = () => {
	
	let oldPassword = document.getElementById("oldPassword").value;
	let newPassword = document.getElementById("newPassword").value;
	let confirmationPassword = document.getElementById("confirmationPassword").value;
	let form = document.getElementById("changePassword");
	
	if(oldPassword === "" || oldPassword === undefined ||
		newPassword === "" || newPassword === undefined ||
		confirmationPassword === "" || confirmationPassword === undefined)
	{
		swal("Por favor verifique", "¡Todas las contraseñas deben ser ingresadas!", "error");
	}else if(newPassword.length < 8 || oldPassword.length < 8 || confirmationPassword.length < 8) {
		swal("Por favor verifique", "¡Las contraseñas deben tener un minito de ocho caracteres!", "error");
	}else{
		if(newPassword === confirmationPassword){
			swal({
			  title: "Cambiar contraseña",
			  text: "¡Esta seguro que desea cambiar su contraseña",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			}).then(confirmation => {
			  if (confirmation) {
			      form.submit();
			  }
			});
			
			return;
		}
		
		swal("¡Las contraseñas no coinciden!", "¡Por favor verifique!", "error");
	}
	
	
	
	
}