var email_ok = false;
var phone_ok = false;
var fam_ok = false;
var educ_ok = false;
var job_ok = false;
var facil_ok = false;
var party_ok = false;
var pic_ok = true;
var cv_ok = true;

function checkEmail() {
	var email = $("#email");
	var email_err = $("#email_err");
	var error = "";
	var value = email.val();

	if (!value.match(/.+\@.+\..+/)) {
		if (value.length < 1) {
			error = "Palun sisestage oma email!"
		} else {
			error = "Palun sisestage korrektne email!"
		};
		email.addClass("fail");
		email_err.html(error);
		email_err.addClass("err_fail");
		email_ok = false;
	}
	else {
		email.removeClass("fail")
		email_err.html("");
		email_ok = true;
	};
	return email_ok;
};

function checkPhone() {
	var phone = $("#telefon");
	var phone_err = $("#telefon_err");
	var error = "";
	var value = phone.val();

  if (!value.match(/^\d{7,9}$/)) {
  	if (value.length < 1) {
			error = "Palun sisestage oma telefoni number!"
		} else {
			error = "Palun sisestage korrektne telefoni number!"
		};
		phone.addClass("fail");
		phone_err.html(error);
		phone_err.addClass("err_fail");
		phone_ok = false;
  }
  else {
    phone.removeClass("fail");
    phone_err.html("");
    phone_ok = true;
  };
  return phone_ok;
};

function checkFamStat() {
  var fam_stat = $("#p_seis");
	var fam_err = $("#p_err");
	var value = fam_stat.val();

  if (value === 'tyhi') {
  	fam_err.addClass("err_fail")
  	fam_err.html("Palun valige oma perekonna seis!");
  	fam_ok = false;
  }
  else {
  	fam_err.html("");
  	fam_ok = true;
  };
  return fam_ok;
};

function checkEduc() {
  var educ = $("#haridus");
	var educ_err = $("#har_err");
	var value = educ.val();

  if (value === 'tyhi') {
  	educ_err.addClass("err_fail")
  	educ_err.html("Palun valige oma haridustase!");
  	educ_ok = false;
  }
  else {
  	educ_err.html("");
  	educ_ok = true;
  };
  return educ_ok;
};

function checkJob() {
	var job = $("#amet");
	var job_err = $("#amet_err");
	var error = "";
	var value = job.val();

  if (value.length < 1) {
		error = "Palun sisestage oma ametiala!"
		job.addClass("fail");
		job_err.html(error);
		job_err.addClass("err_fail");
		job_ok = false;
	}
	else {
		job.removeClass("fail")
		job_err.html("");
		job_ok = true;
	};
	return job_ok;
};

function checkFacil() {
	var facil = $("#asutus");
	var facil_err = $("#asutus_err");
	var error = "";
	var value = facil.val();

  if (value.length < 1) {
		error = "Palun sisestage oma tööasutus!"
		facil.addClass("fail");
		facil_err.html(error);
		facil_err.addClass("err_fail");
		facil_ok = false;
	}
	else {
		facil.removeClass("fail")
		facil_err.html("");
		facil_ok = true;
	};
	return facil_ok;
};

function checkParty() {
  var sparty = $("#erakond");
	var party_err = $("#erakond_err");
	var value = sparty.val();

  if (value === 'tyhi') {
  	party_err.addClass("err_fail")
  	party_err.html("Palun valige erakond kuhu kuulute!");
  	party_ok = false;
  }
  else {
  	party_err.html("");
  	party_ok = true;
  };
  return party_ok;
};

function checkFile(selector, error) {
  //alert("yo!");
}

function checkAll() {
	var allOk = true;

	var field_method = {
		email_ok : checkEmail(),
		phone_ok : checkPhone(),
		fam_ok : checkFamStat(),
		educ_ok : checkEduc(),
		job_ok : checkJob(),
		facil_ok : checkFacil(),
		party_ok : checkParty()
  }

  for (field in field_method) {
    if (!field_method[field]) {
    	allOk = false;
    };
  }

  if (allOk) {
  	alert("Andmed salvestatud");
  }
};