jQuery(document).ready(function() {
  jQuery("#submitCandidature").click(function(event){
  	var id = 6;
  	var family = jQuery("#p_seis");
  	var education = jQuery("#haridus");
  	var job = jQuery("#amet");
  	var institution = jQuery("#asutus");
  	var party = jQuery("#erakond");
  	jQuery.post("/SetCandidature", {id: id, family: family.val(), education: education.val(), job: job.val(), institution: institution.val(), party: party.val()});
    jQuery("#keha").emhaaletaminepty();
    jQuery("#keha").load("kandideeritud.html");
  });
  
  jQuery("#deleteVote").click(function(event){
		if (confirm("Kas oled kindel, et soovid hääle tühistada?")) {
			id = 6;
		  	var request = jQuery.post("/DeleteVote", {voter_id: id});
		  	console.log("Päring tehtud");
////		  	location.reload();
//		    // callback handler that will be called on success
		    request.done(function (response, textStatus, jqXHR){
		        // log a message to the console
		        location.reload();
		    });
		} else {
		    return;
		}
  });
})
