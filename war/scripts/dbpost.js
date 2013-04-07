$(document).ready(function() {
  $("#submitCandidature").click(function(event){
  	alert("vajutasin 13:21");
  	$.post("/SetCandidature");
  });
});