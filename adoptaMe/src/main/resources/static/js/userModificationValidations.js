
let spans = {person: {}}
let user = {person: {}}
let permitRegister = [false, false, false, false, false];
let regexNames = /^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]*)+))*$/g;



const getSpans = () => {
	spans.person.name = document.getElementById("nameSpan");
	spans.person.phoneNumber = document.getElementById("phoneNumberSpan");
	spans.person.surname = document.getElementById("surnameSpan");
	spans.person.secondSurname = document.getElementById("secondSurnameSpan");
	spans.email = document.getElementById("emailSpan");
	
}	

$(document).ready(function() {
	getSpans();
})


const nameValidation = () => {	
	user.person.name = document.getElementById("name").value;	
	if(user.person.name.length < 2 || !regexNames.test(user.person.name) ){
		permitRegister[0] = false;
		spans.person.name.innerHTML = "Debe tener al menos dos letras y no puede contener numeros";
	}else{
		permitRegister[0] = true;
		spans.person.name.innerHTML = "";
	}
}
	
const surnameValidation = () => {
	user.person.surname = document.getElementById("surname").value;
	if(user.person.surname.length < 2 || !regexNames.test(user.person.surname) ){
		permitRegister[1] = false;
		spans.person.surname.innerHTML = "Debe tener al menos dos letras y no puede contener numeros";
	}else{
		permitRegister[1] = true;
		spans.person.surname.innerHTML = "";
	}
}
const secondSurnameValidation = () => {
	user.person.secondSurname = document.getElementById("secondSurname").value;
	if(user.person.secondSurname.length < 2 || !regexNames.test(user.person.secondSurname) ){
		permitRegister[2] = false;
		spans.person.secondSurname.innerHTML = "Debe tener al menos dos letras y no puede contener numeros";
	}else{
		permitRegister[2] = true;
		spans.person.secondSurname.innerHTML = "";
	}
}

const emailValidation = () => {
	user.email = document.getElementById("email").value;
	if(!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(user.email)){
		permitRegister[8] = false;
		spans.email.innerHTML = "No cumple con el formato solicitado";
	}else{
		permitRegister[8] = true;
		spans.email.innerHTML = "";
	}
}
	
const phoneNumberValidation = () => {
	user.person.phoneNumber = document.getElementById("phoneNumber").value;		
	if(!/^[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/.test(user.person.phoneNumber) || user.person.phoneNumber.length < 14 ) {
		permitRegister[7] = false;
		spans.person.phoneNumber.innerHTML = "No es un formato valido";
	}else{
		permitRegister[7] = true;
		spans.person.phoneNumber.innerHTML = "";
	}
}

const passwordValidation = () => {
	
	let userDataForm = document.getElementById("userDataForm");
	let inputPassword = document.getElementById("passwordIn");

	Swal.fire({
	  title: 'Ingrese contraseña para realizar la actualización',
	  html: `<input type="password" id="password" name="password" class="swal2-input" placeholder="Contraseña">`,
	  confirmButtonText: 'Validar',
	  focusConfirm: false,
	  preConfirm: () => {
	    let password = Swal.getPopup().querySelector('#password').value;
	    if (!password) {
	      Swal.showValidationMessage(`La contraseña es requerida`)
		  return;
	    }
		
		inputPassword.value = password;
		userDataForm.submit();
	  }
	})
}

const formValidation = () => {
	nameValidation();
	surnameValidation();
	secondSurnameValidation();
	emailValidation();
	phoneNumberValidation();
	
	let go = 0;
	
	for(let i = 0; permitRegister.length > i; i++){
		if(permitRegister[i]){
			go++;
		}
	}
	
	if(go === 5){
		passwordValidation();
	}else{
		Swal.fire({ icon: 'error', title: 'Por favor verifique', text: '¡Los datos ingresados no son validos!'});
	}
	
}

