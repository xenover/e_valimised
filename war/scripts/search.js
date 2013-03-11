var table_start = "<table border=\"1\" id=\"tabel\">";
var headers = "<tr><th>Nimi</th><th>Kandidaadi#</th><th>Erakond</th><th>Regioon</th></tr>";
var table_end = "</table>";

function search() {
	var name = $("#nimi");
  var party = $("#erakond");
  var region = $("#regioon");

  $("#tabel").remove();
  
  if ($("#nimi").val().length > 0) {
  	candidate();
  } else if ($("#erakond").val().length > 0 && $("#regioon").val().length > 0) {
    byPartyRegion();
  } else if ($("#erakond").val().length > 0) {
  	byParty();
  } else if ($("#regioon").val().length > 0) {
  	byRegion();
  } else {
  	$("#results").after("Tühi otsing, palun valige vähemalt üks ülalnimetatud väljadest");
  }
}

function candidate() {
	$.ajax({
		type: "GET",
		url: "files/candidate.json",
		dataType: "json",
		success : function(data) {
			var output = "";

			$.each($(data), function() {
				output += "<tr><th>" + this.person.name + "</th><th>" + this.person.id + "</th><th>" + this.party.name + "</th><th>" + this.region.name + "</th></tr>"
			})

			$("#results").after(table_start + headers + output + table_end);
		}
	});
}

function byParty() {
  $.ajax({
		type: "GET",
		url: "files/findCandidatesByParty.json",
		dataType: "json",
		success : function(data) {
			var output = "";

			$.each($(data.candidates), function() {
				output += "<tr><th>" + this.person.name + "</th><th>" + this.person.id + "</th><th>" + $("#erakond").find(":selected").html() + "</th><th>" + this.region.name + "</th></tr>"
			})

			$("#results").after(table_start + headers + output + table_end);
		}
	});
};

function byRegion() {
  $.ajax({
		type: "GET",
		url: "files/findCandidatesByRegion.json",
		dataType: "json",
		success : function(data) {
			var output = "";

			$.each($(data.candidates), function() {
				output += "<tr><th>" + this.person.name + "</th><th>" + this.person.id + "</th><th>" + this.party.name + "</th><th>" + $("#regioon").find(":selected").html() + "</th></tr>"
			})

			$("#results").after(table_start + headers + output + table_end);
		}
	});
};

function byPartyRegion() {
  $.ajax({
		type: "GET",
		url: "files/findCandidatesByPartyAndRegion.json",
		dataType: "json",
		success : function(data) {
			var output = "";

			$.each($(data.candidates), function() {
				output += "<tr><th>" + this.person.name + "</th><th>" + this.person.id + "</th><th>" + $("#erakond").find(":selected").html() + "</th><th>" + $("#regioon").find(":selected").html() + "</th></tr>"
			})

			$("#results").after(table_start + headers + output + table_end);
		}
	});
};