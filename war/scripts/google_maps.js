var maakond_keskus = {
	tartu : {loc: new google.maps.LatLng(58.380299,26.724586), name: "Tartumaa", 		  party: 'Eestimaa Sinised' , perc: '50%' , enabled: true},
	harju : {loc: new google.maps.LatLng(59.444377,24.751511), name: "Harjumaa", 		  party: 'Eestimaa Sinised' , perc: '100%', enabled: true},
	hiiu  : {loc: new google.maps.LatLng(58.999423,22.746763), name: "Hiiumaa",  		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	saare : {loc: new google.maps.LatLng(58.258591,22.489014), name: "Saaremaa", 		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	laane : {loc: new google.maps.LatLng(58.948592,23.536835), name: "Läänemaa", 		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	parnu : {loc: new google.maps.LatLng(58.386418,24.496422), name: "Pärnumaa", 		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	rapla : {loc: new google.maps.LatLng(59.005744,24.794254), name: "Raplamaa", 		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	jarva : {loc: new google.maps.LatLng(58.887265,25.569992), name: "Järvamaa", 		  party: 'Info puudub',       perc: 'N/A' , enabled: true},
	vilja : {loc: new google.maps.LatLng(58.368417,25.59763),  name: "Viljandimaa",   party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	valga : {loc: new google.maps.LatLng(57.777264,26.03159),  name: "Valgamaa", 		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	polva : {loc: new google.maps.LatLng(58.058946,27.066879), name: "Põlvamaa", 		  party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	jogeva: {loc: new google.maps.LatLng(58.746387,26.397142), name: "Jõgevamaa", 	  party: 'Maalaste Erakond' , perc: '100%', enabled: true},
	l_viru: {loc: new google.maps.LatLng(59.348158,26.362381), name: "Lääne-Virumaa", party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	i_viru: {loc: new google.maps.LatLng(59.353726,27.39501),  name: "Ida-Virumaa, ", party: 'Info puudub', 			perc: 'N/A' , enabled: true},
	voru  : {loc: new google.maps.LatLng(57.846213,26.998558), name: "Võrumaa",  		  party: 'Info puudub', 			perc: 'N/A' , enabled: true} 
}

function initialize() {
	var zoomLevel = 7;
	//if (localStorage.getItem("lastZoom") != "null") {
	//	zoomLevel = parseInt(localStorage.getItem("lastZoom"));
	//}

	var mapProp = {
	  center:new google.maps.LatLng(58.698495,25.452264),
	  zoom: zoomLevel,
	  mapTypeId:google.maps.MapTypeId.ROADMAP
	};
	
	var map = new google.maps.Map(document.getElementById("googleMap") ,mapProp);

	for (var mknd in maakond_keskus) {
		if (maakond_keskus[mknd].enabled === false) {
			continue;
		}
		var marker = new google.maps.Marker({position: maakond_keskus[mknd].loc, animation: google.maps.Animation.DROP})
		marker.setMap(map);
		var info = new InfoBox({
			content: maakond_keskus[mknd].name + '</br>' + maakond_keskus[mknd].party + '</br>' + maakond_keskus[mknd].perc,
			pixelOffset: new google.maps.Size(-60, 0),
			boxStyle: {
				border: "solid 1px",
				background: "white",
				opacity: 0.75,
				width: "120px"
			}
		});
		info.open(map, marker);
	}

	//google.maps.event.addListener(map, 'zoom_changed', function() {
	//	localStorage.setItem("lastZoom", map.getZoom());
	//});
}

function updateMarkers() {
  $("input:checkbox:not(:checked)").each(function() {
 	  maakond_keskus[$(this).val()].enabled = false;
	});
	localStorage.setItem("markers", maakond_keskus)
	initialize();
}


$(document).ready(function() {
	//maakond_keskus = localStorage.getItem("markers") != null ? maakond_keskus : localStorage.getItem("markers")
	var $gmap = $("#checkBoxes")
	for (mknd in maakond_keskus) {
		$("#checkBoxes").append("<input type=\"checkbox\" value=\"" + mknd + "\" >" + maakond_keskus[mknd].name) // " + maakond_keskus[mknd].enabled ? "checked" : ""  + "
	}
	$("#checkBoxes").append("<button onclick=\"updateMarkers()\">Muuda</button>")

	initialize();
});