$(document).ready(() => {
	

	$(".btn-information").on("click", function () {
		let json = $(this).data("info");
		
		let probando = document.getElementById("innerLayout");
		document.getElementById("forms").removeChild(probando)
		
		let layout = document.createElement("div");
		layout.setAttribute("id", "innerLayout")
		document.getElementById("forms").appendChild(layout);
		
		document.getElementById("petName").innerHTML = json.pet.name;
		document.getElementById("petType").innerHTML = json.pet.type;
		document.getElementById("petAge").innerHTML = json.pet.age;
		document.getElementById("petColor").innerHTML = json.pet.color.name;
		document.getElementById("petDescription").innerHTML = json.pet.description;
		
		let forms = document.getElementById("innerLayout");
		
		for(let i = 0; json.users.length > i; i++){
			json.users[i].password = "";
			
			var form = document.createElement("form");
			form.setAttribute("id", "submitForm" + json.users[i].username);
			
			let divRow = document.createElement("div");
			divRow.setAttribute("class", "row");
			
			
			
			//for name
			
			let h51 = document.createElement("h5");
			h51.innerHTML = json.users[i].person.gender === 'H' ? "Candidato:" : "Candidata"
			let p1 = document.createElement("p");
			p1.innerHTML = json.users[i].person.name + ' ' + json.users[i].person.surname + ' ' + json.users[i].person.secondSurname;
			let divCol1 = document.createElement("div");
			divCol1.setAttribute("class", "col-3");
			divCol1.appendChild(h51);
			divCol1.appendChild(p1);
			
			// for phoneNumber
			
			let h52 = document.createElement("h5");
			h52.innerHTML = 'Número telefonico';
			let p2 = document.createElement("p");
			p2.innerHTML = json.users[i].person.phoneNumber;
			let divCol2 = document.createElement("div");
			divCol2.setAttribute("class", "col-3");
			divCol2.appendChild(h52);
			divCol2.appendChild(p2);

			// for email
	
			let h53 = document.createElement("h5");
			h53.innerHTML = 'Correo electronico';
			let p3 = document.createElement("p");
			p3.innerHTML = json.users[i].email;
			let divCol3 = document.createElement("div");
			divCol3.setAttribute("class", "col-3");
			divCol3.appendChild(h53);
			divCol3.appendChild(p3);
			
			
			//for adoption button
			let button = document.createElement("button");
			button.setAttribute("class", "btn btn-success");
			button.setAttribute("type", "button")
			button.setAttribute("onClick", "confirmAdoption('"+ json.users[i].username +"');")
			button.innerHTML = "Confirmar adopción";
			
			let divCol4 = document.createElement("div");
			divCol4.setAttribute("class", "col-3");
			divCol4.appendChild(button);
			
			divRow.appendChild(divCol1);
			divRow.appendChild(divCol2);
			divRow.appendChild(divCol3);
			divRow.appendChild(divCol4);
			
			//value for parameters
			
			let param1 = document.createElement("input");
			param1.setAttribute("type", "hidden");
			param1.setAttribute("name", "petId");
			param1.setAttribute("value", json.pet.id);

			let param2 = document.createElement("input");
			param2.setAttribute("type", "hidden");
			param2.setAttribute("name", "adopterName");
			param2.setAttribute("value", json.users[i].username);
			
			
			form.appendChild(param1);
			form.appendChild(param2);
			form.appendChild(divRow);
			
			form.setAttribute("action", "/mascotas/endAdoption");
			form.setAttribute("method", "POST");

			forms.appendChild(form);
		}
		
		$('#requesters').modal('show');

		console.log(json);
		
		
		
	});
});



const confirmAdoption = (username) => {
	
	console.log("This is the username in the confirmation adoptions", username);
	
	let submitForm = document.getElementById("submitForm"+username);
	console.log("This is the submitForm ", submitForm);
	
	Swal.fire({
	  title: '¿Esta seguro?',
	  html: "La mascota sera dada en adopción a el usuario <b>" + username + "</b> y debera ponerse en contacto con el para la adopción",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Aceptar!'
	}).then((result) => {
	  if (result.isConfirmed) {
		
		console.log("Entra al if del sweetalert");
	    submitForm.submit();
	  }
	})
	
	
}

