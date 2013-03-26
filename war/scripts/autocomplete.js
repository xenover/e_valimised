$(document).ready(function() {
	var eesnimed = [
		"Aivar",
		"Andres",
		"Ivan",
		"Jaak",
		"Juku",
		"Maali"
	]
	var perenimed = [
   "Ubasalu",
   "Auto",
   "Päevalill",
   "Põllumaa",
   "Maarohi",
   "Mardikas"
	]
	
	$('#eesnimi').autocomplete({source: eesnimed});
	$('#perenimi').autocomplete({source: perenimed});
});