

const validateLogin = () => {
	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	let loginForm = document.getElementById("loginForm");
	
	if(username.length !== 0 && password.length !== 0 && username !== undefined && password !== undefined ){
		loginForm.submit();
	}else{
		Swal.fire({ icon: 'error', title: 'Por favor verifique', text: 'Los datos son requeridos'});
	}
	
}