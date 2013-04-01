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