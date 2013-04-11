$(document).ready(function() {
  $("#submitCandidature").click(function(event){
  	var id = 6;
  	var family = $("#p_seis");
  	var education = $("#haridus");
  	var job = $("#amet");
  	var institution = $("#asutus");
  	var party = $("#erakond");
  	$.post("/SetCandidature", {id: id, family: family.val(), education: education.val(), job: job.val(), institution: institution.val(), party: party.val()});
    $("#keha").empty();
    $("#keha").load("kandideeritud.html");
  });
