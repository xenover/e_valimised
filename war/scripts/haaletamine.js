$(document).ready(function() {
  $("#showTable").click(function(event){
	var params = "?";
	var fname = $("#eesnimi");
	var lname = $("#perenimi");
	var id = $("#kandidaadiNr")
	var party = $("#erakond option:selected");
	var region = $("#regioon option:selected");
	if (fname.val().length > 0) {
	  params += "fname=" + fname.val() + "&";
    }
	if (lname.val().length > 0) {
	  params += "lname=" + lname.val() + "&";
    }
	if (id.val().length > 0) {
      params += "id=" + id.val() + "&";
	}
	if (party.val().length > 0) {
	  params += "party=" + party.text() + "&";
	}
    if (region.val().length > 0) {
  	  params += "region=" + region.text() + "&";
    } 
    
	var url = "/PopulateTable" + params;
    $.get(url ,function(responseJson) {
      if(responseJson!=null){
        $("#countrytable").find("tr:gt(0)").remove();
        var table1 = $("#countrytable");
	    $.each(responseJson, function(key,value) { 
	      var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
	      rowNew.children().eq(0).text(value['id']); 
	      rowNew.children().eq(1).text(value['first_name']); 
	      rowNew.children().eq(2).text(value['last_name']); 
	      rowNew.children().eq(3).text(value['party']);
	      rowNew.appendTo(table1);
	    });
      }
    });        
  });      
});