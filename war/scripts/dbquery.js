jQuery(document).ready(function() {
	jQuery.noConflict();
  var eesnimed = [];
  var perenimed = [];
  jQuery.get("/FirstLastName", function(responseJson) {
	  if(responseJson != null) {
		var firstLastNames = jQuery.parseJSON(responseJson);
		eesnimed = firstLastNames["first_names"];
		perenimed = firstLastNames["last_names"]
		jQuery('#eesnimi').autocomplete({source: eesnimed});
        jQuery('#perenimi').autocomplete({source: perenimed});
	  }
  });
  
   jQuery("#showTable").click(function(event){
	var params = "?";
	var fname = jQuery("#eesnimi");
	var lname = jQuery("#perenimi");
	var id = jQuery("#kandidaadiNr")
	var party = jQuery("#erakond");
	var region = jQuery("#regioon");
	params += "fname=" + fname.val() + "&";
	params += "lname=" + lname.val() + "&";
    params += "id=" + id.val() + "&";
	params += "party=" + party.val() + "&";
  	params += "region=" + region.val(); 
	var url = "/PopulateTable" + params;
    jQuery.get(url ,function(responseJson) {
      if(responseJson!=null){
    	jQuery("#candidatetable").find("tr:gt(0)").remove();
        var table1 = jQuery("#candidatetable");
	    jQuery.each(responseJson, function(key,value) { 
	      var rowNew = jQuery("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
	      rowNew.children().eq(0).text(value['id']); 
	      rowNew.children().eq(1).text(value['first_name']); 
	      rowNew.children().eq(2).text(value['last_name']); 
	      rowNew.children().eq(3).text(value['party']);
	      rowNew.children().eq(4).text(value['region']);
	      var valiTxt = "<input type=\"submit\" onclick=\"vote("+ value['id'] + ")\" class=\"choose\" id=" + value['id'].toString() + " value=\"VALI!\">";
	      rowNew.children().eq(5).html(valiTxt);
	      rowNew.appendTo(table1);
	    })
      }
    });
    jQuery("#candidatetable").tablesorter();
  });
  
  jQuery("#searchParties").click(function(event){
		var params = "?";
		var region = jQuery("#regioon");
		params += "region=" + region.val(); 
		var url = "/PartyStatistics" + params;
	    jQuery.get(url ,function(responseJson) {
	      if(responseJson!=null){
	        jQuery("#partytable").find("tr:gt(0)").remove();
	        var table1 = jQuery("#partytable");
		    jQuery.each(responseJson, function(key,value) { 
		      var rowNew = jQuery("<tr><td></td><td></td></tr>");
		      rowNew.children().eq(0).text(value['name']); 
		      rowNew.children().eq(1).text(value['votes']); 
		      rowNew.appendTo(table1);
		      console.log("Sain erakonna");
		    });
	      }
	    });        
	  });
  
  jQuery("#searchCandidates").click(function(event){
		var params = "?";
		var fname = jQuery("#eesnimi");
		var lname = jQuery("#perenimi");
		var id = jQuery("#kandidaadiNr")
		var party = jQuery("#erakond");
		var region = jQuery("#region");
		params += "fname=" + fname.val() + "&";
		params += "lname=" + lname.val() + "&";
	    params += "id=" + id.val() + "&";
		params += "party=" + party.val() + "&";
	  	params += "region=" + region.val(); 
		var url = "/CandidateStatistics" + params;
	    jQuery.get(url ,function(responseJson) {
	      if(responseJson!=null){
	        jQuery("#candidateTable").find("tr:gt(0)").remove();
	        var table1 = jQuery("#candidateTable");
		    jQuery.each(responseJson, function(key,value) {
		      var rowNew = jQuery("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		      rowNew.children().eq(0).text(value['id']); 
		      rowNew.children().eq(1).text(value['first_name']); 
		      rowNew.children().eq(2).text(value['last_name']); 
		      rowNew.children().eq(3).text(value['party']);
		      rowNew.children().eq(4).text(value['region']);
		      rowNew.children().eq(5).text(value['votes']);
		      rowNew.appendTo(table1);
		    });
	      }
	    });        
	});  
  
  jQuery("#showTableLoggedOut").click(function(event){
		var params = "?";
		var fname = jQuery("#eesnimi");
		var lname = jQuery("#perenimi");
		var id = jQuery("#kandidaadiNr")
		var party = jQuery("#erakond");
		var region = jQuery("#regioon");
		params += "fname=" + fname.val() + "&";
		params += "lname=" + lname.val() + "&";
	    params += "id=" + id.val() + "&";
		params += "party=" + party.val() + "&";
	  	params += "region=" + region.val(); 
		var url = "/PopulateTable" + params;
	    jQuery.get(url ,function(responseJson) {
	      if(responseJson!=null){
	    	jQuery("#candidatetable").find("tr:gt(0)").remove();
	        var table1 = jQuery("#candidatetable");
		    jQuery.each(responseJson, function(key,value) { 
		      var rowNew = jQuery("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
		      rowNew.children().eq(0).text(value['id']); 
		      rowNew.children().eq(1).text(value['first_name']); 
		      rowNew.children().eq(2).text(value['last_name']); 
		      rowNew.children().eq(3).text(value['party']);
		      rowNew.children().eq(4).text(value['region']);
		      rowNew.appendTo(table1);
		    })
	      }
	    });
	    jQuery("#candidatetable").tablesorter();
	  });
});


// Nüüd peaks töötama
function vote(candidate_id)
{
	if (confirm("Kas oled kindel, et soovid anda oma hääle valitud kandidaadile?")) {
	  	var voter_id = 6;
	  	var request = jQuery.post('/Vote',{"voter_id": voter_id, "candidate_id": candidate_id});
//	  	location.reload();
	    // callback handler that will be called on success
	    request.done(function (response, textStatus, jqXHR){
	        // log a message to the console
	        location.reload();
	    });

	    // callback handler that will be called on failure
	    request.fail(function (jqXHR, textStatus, errorThrown){
	        // log the error to the console
	        console.error(
	            "The following error occured: "+
	            textStatus, errorThrown
	        );
	    });
	} else {
	    return;
	}
}
