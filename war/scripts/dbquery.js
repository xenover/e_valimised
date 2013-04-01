$(document).ready(function() {
  $("#showTable").click(function(event){
	var params = "?";
	var fname = $("#eesnimi");
	var lname = $("#perenimi");
	var id = $("#kandidaadiNr")
	var party = $("#erakond");
	var region = $("#regioon");
	params += "fname=" + fname.val() + "&";
	params += "lname=" + lname.val() + "&";
    params += "id=" + id.val() + "&";
	params += "party=" + party.val() + "&";
  	params += "region=" + region.val(); 
	var url = "/PopulateTable" + params;
    $.get(url ,function(responseJson) {
      if(responseJson!=null){
    	$("#candidatetable").find("tr:gt(0)").remove();
        var table1 = $("#candidatetable");
	    $.each(responseJson, function(key,value) { 
	      var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
	      rowNew.children().eq(0).text(value['id']); 
	      rowNew.children().eq(1).text(value['first_name']); 
	      rowNew.children().eq(2).text(value['last_name']); 
	      rowNew.children().eq(3).text(value['party']);
	      rowNew.children().eq(4).text(value['region']);
	      rowNew.children().eq(5).text("VALI!");
	      rowNew.appendTo(table1);
	    });
      }
    });
    $("#candidatetable").tablesorter();
  });
  
  $("#searchParties").click(function(event){
		var params = "?";
		var region = $("#partyRegion");
		params += "region=" + region.val(); 
		var url = "/PartyStatistics" + params;
	    $.get(url ,function(responseJson) {
	      if(responseJson!=null){
	        $("#partytable").find("tr:gt(0)").remove();
	        var table1 = $("#partytable");
		    $.each(responseJson, function(key,value) { 
		      var rowNew = $("<tr><td></td><td></td></tr>");
		      rowNew.children().eq(0).text(value['name']); 
		      rowNew.children().eq(1).text(value['votes']); 
		      rowNew.appendTo(table1);
		    });
	      }
	    });        
	  });
  
  $("#searchCandidates").click(function(event){
		var params = "?";
		var fname = $("#eesnimi");
		var lname = $("#perenimi");
		var id = $("#kandidaadiNr")
		var party = $("#erakond");
		var region = $("#candidateRegion");
		params += "fname=" + fname.val() + "&";
		params += "lname=" + lname.val() + "&";
	    params += "id=" + id.val() + "&";
		params += "party=" + party.val() + "&";
	  	params += "region=" + region.val(); 
		var url = "/CandidateStatistics" + params;
	    $.get(url ,function(responseJson) {
	      if(responseJson!=null){
	        $("#candidateTable").find("tr:gt(0)").remove();
	        var table1 = $("#candidateTable");
		    $.each(responseJson, function(key,value) { 
		      var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
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
});