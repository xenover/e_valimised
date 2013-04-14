function sisu(nimi) {
  $("#keha").empty();
  $("#keha").load(nimi + ".html")
  if(nimi == "statistika")
  {
      setTimeout(function(){document.getElementById("statistika_tekst").style.visibility="hidden";},1500);
      setTimeout(function(){document.getElementById("loader").style.visibility="hidden";},1500);
      setTimeout(function(){$(".statistika").css( "display", "block" );},1500);
//      setTimeout(function(){document.getElementById("chart_div").style.visibility="visible";},1500);
  }
  else if( nimi == "haaletamine")
  {

      setTimeout(function(){document.getElementById("ootamistekst").style.visibility="hidden";},1500);
      setTimeout(function(){document.getElementById("loader_haaletamine").style.visibility="hidden";},1500);
      setTimeout(function(){$(".statistika").css( "display", "block" );},1500);
	  var params = "?id=6" 
	  var url = "/GetVote" + params;
	  $.get(url ,function(responseJson) {
	      if(responseJson!=""){
	    	$("#candidatetable").find("tr:gt(0)").remove();
	        var table1 = $("#candidatetable");
	        $("#haaletamata").css( "display", "none" );
	        $("#tyhista").css( "display", "block" );
	        $("#haaletanud").css( "display", "block" );
		    $.each(responseJson, function(key,value) { 
		      var voteInfo = "Oled andnud oma hääle juba kandidaadile " +  value['first_name'] + " " + value["last_name"] + ". Uue hääle andmisel eelmine hääl tühistatakse.";
		      $("#haaletanud").append(voteInfo);
		    });
	      }
	      else{
		      $("#haaletamata").css( "display", "block" );
		      $("#tyhista").css( "display", "none" );
		      $("#haaletanud").css( "display", "none" );
	      }
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

function checkVote()
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
//    channel = new goog.appengine.Channel('{{ token }}');
//    socket = channel.open();
//    socket.onopen = onOpened;
//    socket.onmessage = onMessage;
//    socket.onerror = onError;
//    socket.onclose = onClose;
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