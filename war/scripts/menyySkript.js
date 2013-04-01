$(document).ready(function(){
  var last_page = $.cookie("current_tab");
  //Lehe avamisel avab avalehe või cookiesse salvestatud lehe
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