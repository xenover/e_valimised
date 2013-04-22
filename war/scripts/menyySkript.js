function sisu(nimi) {
	jQuery("#keha").empty();
  
  localStorage.getItem('newuser');
  if(localStorage.getItem('newuser') == "true" ){
	  jQuery("#keha").load("uuskasutaja.html");
  }
  else{
	  jQuery("#keha").load(nimi + ".html")
	  if(nimi == "statistika")
	  {
		  setTimeout(function(){document.getElementById("statistika_tekst").style.visibility="hidden";},1500);
		  setTimeout(function(){document.getElementById("loader").style.visibility="hidden";},1500);
		  setTimeout(function(){jQuery(".statistika").css( "display", "block" );},1500);
//		  setTimeout(function(){document.getElementById("chart_div").style.visibility="visible";},1500);
	  }
	  else if( nimi == "haaletamine")
	  {
		  setTimeout(function(){document.getElementById("ootamistekst").style.visibility="hidden";},1500);
		  setTimeout(function(){document.getElementById("loader_haaletamine").style.visibility="hidden";},1500);
		  setTimeout(function(){jQuery(".statistika").css( "display", "block" );},1500);
		  var params = "?id=6" 
			  var url = "/GetVote" + params;
		  	jQuery.get(url ,function(responseJson) {
			  if(responseJson!=""){
				  jQuery("#candidatetable").find("tr:gt(0)").remove();
				  var table1 = jQuery("#candidatetable");
				  jQuery("#haaletamata").css( "display", "none" );
				  jQuery("#tyhista").css( "display", "block" );
				  jQuery("#haaletanud").css( "display", "block" );
				  jQuery.each(responseJson, function(key,value) { 
					  var voteInfo = "Oled andnud oma hääle juba kandidaadile " +  value['first_name'] + " " + value["last_name"] + ". Uue hääle andmisel eelmine hääl tühistatakse.";
					  jQuery("#haaletanud").append(voteInfo);
				  });
			  }
			  else{
				  jQuery("#haaletamata").css( "display", "block" );
				  jQuery("#tyhista").css( "display", "none" );
				  jQuery("#haaletanud").css( "display", "none" );
			  }
		  });
	  }
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

function checkVote()
{
  	voteInfo = "Sa pole veel kellelegi oma häält andnud.";
	var params = "?id=6" 
	var url = "/GetVote" + params;
    jQuery.get(url ,function(responseJson) {
      if(responseJson!=null){
    	jQuery("#candidatetable").find("tr:gt(0)").remove();
        var table1 = jQuery("#candidatetable");
	    jQuery.each(responseJson, function(key,value) { 
	      voteInfo = "Oled andnud oma hääle juba kandidaadile " +  value['first_name'] + " " + value["last_name"] + ". Uue hääle andmisel eelmine hääl tühistatakse.";
	      jQuery("#tyhista").css( "display", "block" );
	    });
      }
      jQuery("#haaletus").append(voteInfo);
    });
}

function onOpened() {
    alert("Channel opened!");
}

function onMessage(msg) {
    alert(msg.data);
}

function onMessage(err) {
    alert(err);
}

function onClose() {
    alert("Channel closed!");
}