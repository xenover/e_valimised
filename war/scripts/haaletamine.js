$(document).ready(function() {
     $("#showTable").click(function(event){
           $.get('PopulateTable',function(responseJson) {
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