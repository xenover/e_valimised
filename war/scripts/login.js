$(document).ready(function() {
	$.noConflict();
	var uid;
//	Additional JS functions here
	window.fbAsyncInit = function() {
		FB.init({
			appId      : 	323774344414844, // App ID
			channelUrl : '//test.xenover.appspot.com/channel.html', // Channel File
			status     : true, // check login status
			cookie     : true, // enable cookies to allow the server to access the session
			xfbml      : true  // parse XFBML
		});
		FB.XFBML.parse();

		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				console.log("Oled sisse loginud");
				uid = response.authResponse.userID;
				checkUser();
			}
			else {
				console.log("pole sisse loginud");
				localStorage.setItem('newuser',false);
			}
		});

		FB.Event.subscribe('auth.login', function(response) {
			// do something with response
			uid = response.authResponse.userID;
			checkUser();
			alert("Login sisse");
			location.reload();
		});
		FB.Event.subscribe('auth.logout', function(response) {
			// do something with response
			localStorage.setItem('newuser',false);
			alert("Login välja");
			location.reload();
		});
	};

	function checkUser(){
		FB.api(
				FB.api('/fql', 'GET', {q:'SELECT uid, first_name, last_name FROM user WHERE uid = ' + uid}, function(response){
					  if (response && response.data)
					    console.log(response.data);
						// KIRUTADA JUURDE, KAS KASUTAJA JUBA ANDMEBAASIS OLEMAS
						var params = "?";
						params += "id=" + resp[0].uid; 
						var url = "/SearchPerson" + params;
						$.get(url ,function(response) {
							if(response == 0)
							{
								(function($) {
									console.log("TEST");
									localStorage.setItem('newuser',true);
									})(jQuery);
								newUser(resp[0]);
							}
						});
					});
		)};

	// Load the SDK Asynchronously
	(function(d){
		var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
		if (d.getElementById(id)) {return;}
		js = d.createElement('script'); js.id = id; js.async = true;
		js.src = "//connect.facebook.net/en_US/all.js";
		ref.parentNode.insertBefore(js, ref);
	}(document));
});

// Uue kasutaja loomiseks
function newUser(resp){
	console.log("TESTIN");
	sisu("uuskasutaja");
	$("#eesnimi").append(resp.first_name);
	console.log(resp.first_name);
	$("#perenimi").append(resp.last_name);
	console.log(resp.last_name);
};
