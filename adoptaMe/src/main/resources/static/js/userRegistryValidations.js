console.log('imported correctly');

//person data
let user = { person : {gender: {}}}
let spans = { person: {} };
let regexNames = /^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]*)+))*$/g;
let permitRegister = [false, false, false, false, false, false, false, false, false];

$(document).ready(function() {
	
	retrieveData()
	getSpans();
});
	

const retrieveData = () => {
	user.person.name = document.getElementById("name").value;
	user.person.gender.man = document.getElementById("man").checked;
	user.person.gender.woman = document.getElementById("woman").checked;
	user.person.phoneNumber = document.getElementById("phoneNumber").value;
	user.person.surname = document.getElementById("surname").value;
	user.person.secondSurname = document.getElementById("secondSurname").value;
	user.username = document.getElementById("username").value;
	user.email = document.getElementById("email").value;
	user.password = document.getElementById("password").value;
	user.passwordConfirmation = document.getElementById("passwordConfirmation").value;
	
}

const getSpans = () => {
	spans.person.name = document.getElementById("nameSpan");
	spans.person.gender = document.getElementById("genderSpan");
	spans.person.phoneNumber = document.getElementById("phoneNumberSpan");
	spans.person.surname = document.getElementById("surnameSpan");
	spans.person.secondSurname = document.getElementById("secondSurnameSpan");
	spans.username = document.getElementById("usernameSpan");
	spans.email = document.getElementById("emailSpan");
	spans.password = document.getElementById("passwordSpan");
	spans.passwordConfirmation = document.getElementById("passwordConfirmationSpan");
	
}


const validations = {
	
	
	name : () => {	
		user.person.name = document.getElementById("name").value;	
		if(user.person.name.length < 2 || !regexNames.test(user.person.name) ){
			console.log("name if ", !regexNames.test(user.person.name));
			permitRegister[0] = false;
			spans.person.name.innerHTML = "Debe tener al menos dos letras y no puede contener numeros";
		}else{
			console.log("name else ", !regexNames.test(user.person.name));
			permitRegister[0] = true;
			spans.person.name.innerHTML = "";
		}
	},
	
	surname : () => {
		user.person.surname = document.getElementById("surname").value;
		if(user.person.surname.length < 2 || !regexNames.test(user.person.surname) ){
			console.log("surname if ", !regexNames.test(user.person.surname));
			permitRegister[1] = false;
			spans.person.surname.innerHTML = "Debe tener al menos dos letras y no puede contener numeros";
		}else{
			console.log("surname else ", !regexNames.test(user.person.surname));
			permitRegister[1] = true;
			spans.person.surname.innerHTML = "";
		}
	},
	secondSurname : () => {
		user.person.secondSurname = document.getElementById("secondSurname").value;
		if(user.person.secondSurname.length < 2 || !regexNames.test(user.person.secondSurname) ){
			console.log("secondSurname if ", !regexNames.test(user.person.secondSurname));
			permitRegister[2] = false;
			spans.person.secondSurname.innerHTML = "Debe tener al menos dos letras y no puede contener numeros";
		}else{
			console.log("secondSurname else ", !regexNames.test(user.person.secondSurname));
			permitRegister[2] = true;
			spans.person.secondSurname.innerHTML = "";
		}
	},
	
	username: () => {
		user.username = document.getElementById("username").value;
		if(user.username.length < 5){
			permitRegister[3] = false;
			spans.username.innerHTML = "Debe tener al meno 5 caracteres";
		}else{
			permitRegister[3] = true;
			spans.username.innerHTML = "";
		}
	},
	
	password : () => {
				
		user.password = document.getElementById("password").value;
		
		if(user.password.length < 8){
			permitRegister[4] = false;
			spans.password.innerHTML = "La contraseña debe contener al menos 8 caracteres";
		}else{
			permitRegister[4] = true;
			spans.password.innerHTML = "";
		}
		
	},
	
	passwordConfirmation : () => {
		
		user.passwordConfirmation = document.getElementById("passwordConfirmation").value;

		if(user.password !== user.passwordConfirmation) {
			permitRegister[5] = false;
			spans.passwordConfirmation.innerHTML = "La contraseña no coincide" 
		} else{
			permitRegister[5] = true;
			spans.passwordConfirmation.innerHTML = "";
		}
		
	},
	
	gender : () => {
		
		user.person.gender.man = document.getElementById("man").checked;
		user.person.gender.woman = document.getElementById("woman").checked;
		
		if(!(user.person.gender.woman || user.person.gender.man)){
			permitRegister[6] = false;
			spans.person.gender.innerHTML = "Este campo es obligatorio";			
		} else{
			permitRegister[6] = true;
			spans.person.gender.innerHTML = "";
		}
	},
	
	phoneNumber : () => {
		user.person.phoneNumber = document.getElementById("phoneNumber").value;		
		if(!/^[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/.test(user.person.phoneNumber) || user.person.phoneNumber.length < 14 ) {
			permitRegister[7] = false;
			spans.person.phoneNumber.innerHTML = "No es un formato valido";
		}else{
			permitRegister[7] = true;
			spans.person.phoneNumber.innerHTML = "";
		}
	},
	
	email : () => {
		user.email = document.getElementById("email").value;
		if(!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(user.email)){
			permitRegister[8] = false;
			spans.email.innerHTML = "No cumple con el formato solicitado";
		}else{
			permitRegister[8] = true;
			spans.email.innerHTML = "";
		}
	}
	
	
	
}


const registerUser = () => {	
	
	retrieveData()
	getSpans();
	
	validations.name();
	validations.surname();
	validations.secondSurname();
	validations.password();
	validations.passwordConfirmation();
	validations.gender();
	validations.username();
	validations.phoneNumber();
	validations.email();

	console.log(permitRegister);

	let go = 0;
	for(let i = 0; permitRegister.length > i; i++){
		if( permitRegister[i] ){
			console.log("se imprime ", permitRegister[i])
			go++;
		}
	}	
	console.log('This is go', go);
	
	registerForm = document.getElementById('registerForm');
	
	if(go === 9){
		registerForm.submit()
	}else{
		Swal.fire({ icon: 'error', title: 'Verifique los datos', text: '¡Los datos ingresados no cumplen con los requisitos!'});
	}
	

	
}