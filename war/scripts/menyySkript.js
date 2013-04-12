function sisu(nimi) {
  $("#keha").empty();
  $("#keha").load(nimi + ".html")
  if(nimi == "statistika")
  {
      setTimeout(function(){document.getElementById("statistika_tekst").style.visibility="hidden";},1500);
      setTimeout(function(){document.getElementById("loader").style.visibility="hidden";},1500);
      setTimeout(function(){document.getElementById("chart_pie").style.visibility="visible";},1500);
      setTimeout(function(){document.getElementById("chart_div").style.visibility="visible";},1500);
  }
  else if( nimi == "haaletamine")
  {
	  	voteInfo = "Sa pole veel kellelegi oma häält andnud.";
		var params = "?id=6" 
		var url = "/GetVote" + params;
	    $.get(url ,function(responseJson) {
	      if(responseJson!=null){
	    	$("#candidatetable").find("tr:gt(0)").remove();
	        var table1 = $("#candidatetable");
		    $.each(responseJson, function(key,value) { 
		      voteInfo = "Oled andnud oma hääle juba kandidaadile " +  value['first_name'] + " " + value["last_name"] + ". Uue hääle andmisel eelmine hääl tühistatakse.";
		      $("#tyhista").css( "display", "block" );
		    });
	      }
	      $("#haaletus").append(voteInfo);
	    });
  }
}

function navig() {
  a = location.hash;
  a = a.replace("#", "");
  if (a != "") {
    sisu(a);
  }
  else {
    sisu("avaleht");
  }
}
window.onload = navig;
window.onhashchange = navig; 
