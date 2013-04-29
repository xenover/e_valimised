// Uue kasutaja loomiseks
function newUser(resp){
	alert("Testin");
	sisu("uuskasutaja");
	$("#eesnimi").append(resp.first_name);
	console.log(resp.first_name);
	$("#perenimi").append(resp.last_name);
	console.log(resp.last_name);
};
