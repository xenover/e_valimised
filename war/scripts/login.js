$(document).ready(function() {
	var uid;
// Additional JS functions here
  window.fbAsyncInit = function() {
    FB.init({
      appId      : 	323774344414844, // App ID
      channelUrl : '//xenover.appspot.com/channel.html', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });

    FB.getLoginStatus(function(response) {
    	  if (response.status === 'connected') {
    		console.log("Oled sisse loginud");
        	uid = response.authResponse.userID;
        	checkUser();
    	  }
    	  else {
    		  console.log("pole sisse loginud");
    	  }
    	 });
   
    function login() {
        FB.login(function(response) {
            if (response.authResponse) {
            	$("#user").html(response.name);
            	
            } else {
            	$("#user").html();
            }
        });
    }
    
    function checkUser(){
    	FB.api(
    			{
    				method: 'fql.query',
    				query: 'SELECT uid, first_name, last_name FROM user WHERE uid = ' + uid
    			},
    			function(resp) {
    				//    do something with the response
    				console.log(resp[0].uid);
    				// KIRUTADA JUURDE, KAS KASUTAJA JUBA ANDMEBAASIS OLEMAS
    				var params = "?";
    				params += "id=" + resp[0].uid; 
    				var url = "/SearchPerson" + params;
    				$.get(url ,function(response) {
    					if(response == 0)
    					{
    						newUser(resp[0].uid);
    					}
    				});

    			}
    	);
    }
  };

  // Load the SDK Asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
});