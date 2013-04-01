<<<<<<< HEAD
$(document).ready(function(){
  var last_page = $.cookie("current_tab");
  //Lehe avamisel avab avalehe v�i cookiesse salvestatud lehe
  $("#keha").load(last_page == undefined ? "avaleht.html" : last_page);
  
  $("#avaleht").click(function(){
    $("#keha").empty();
    $("#keha").load("avaleht.html");
    $.cookie("current_tab", "avaleht.html");
  });
  
  $("#statistika").click(function(){
    $("#keha").empty();
    $("#keha").load("statistika.html");
    $.cookie("current_tab", "statistika.html");
  });
  
  $("#user").click(function(){
    $("#keha").empty();
  	$("#keha").load("seaded.html");
  	$.cookie("current_tab", "seaded.html")
  });
  
  $("#haaletamine").click(function(){
    $("#keha").empty();
    $("#keha").load("haaletamine.html");
    $.cookie("current_tab", "haaletamine.html")
  });
  
  $("#kandideeri").click(function(){
    $("#keha").empty();
    $("#keha").load("kandideeri.html");
    $.cookie("current_tab", "kandideeri.html")
  });
  
  $("#statistika_isikud").click(function(){
    $("#keha").empty();
    $("#keha").load("isikute_statistika.html");
    $.cookie("current_tab", "isikute_statistika.html")
  });
});
=======
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
>>>>>>> Search ja back töötavad
