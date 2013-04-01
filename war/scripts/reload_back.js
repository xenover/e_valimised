var loggedin = false;
function sisu(nimi) {
	$("body").attr("class", nimi);
	if (nimi != "Kandidaadid") {
		$("#peasisu").load(nimi + ".html #sisu", function() {
				hidesees();
	});
} 
	else {
		$("#peasisu").load(nimi + ".html #sisu", function() {
				hider();
//autoc();
	});
}
// $("#sisu").outerHTML=$("#sisu").innerHTML;
// document.getElementById('sisu').outerHTML=document.getElementById('sisu').innerHTML;
// }
// $("body").load(nimi+".html");
}
function navig() {
	hidesees();
	a = location.hash;
	a = a.replace("#", "");
	alert(a);
	if (a != "") {
		sisu(a);
	}
	else {
		sisu("avaleht.html");
	}
}
window.onload = navig;
window.onhashchange = navig; 


    setTimeout(function(){document.getElementById("statistika_tekst").style.visibility="hidden";},1500);
    setTimeout(function(){document.getElementById("loader").style.visibility="hidden";},1500);
    setTimeout(function(){document.getElementById("chart_pie").style.visibility="visible";},1500);
    setTimeout(function(){document.getElementById("chart_div").style.visibility="visible";},1500);