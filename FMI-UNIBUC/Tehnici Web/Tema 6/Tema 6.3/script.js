var persoane = [];

function Persoana(nume, prenume, cnp, data) {
    this.nume = nume;
	this.prenume = prenume;
	this.cnp = cnp;
	this.data_nastere = data;
}

function Adauga() {
	var nume = prompt("Numele");
	var prenume = prompt("Prenumele");
	var cnp = prompt("CNP");
	var data = prompt("Data nasterii");
	
	var regex = new RegExp(/[12]\d\d\d\d\d\d\d\d\d\d\d\d/, 'i');
	if(regex.test(cnp)) {
		var pers = new Persoana(nume, prenume, cnp, new Date(data));
		persoane[persoane.length] = pers;
	}
}

function Info(pers) {
	return pers.nume + " " + pers.prenume + " " + pers.cnp + " " + pers.data_nastere.toString();
}

function Afiseaza() {
	var ret = "";
	
	for(var i in persoane) {
		ret += "<p>" + Info(persoane[i]) + "</p>";
	}
	
	document.getElementById("Informatii").innerHTML = ret;
}

function Selecteaza() {
	var varstaMax = prompt("Introduceti o varsta.");
	
	var crtDate = new Date();
	var msg = "";
	
	for(var i in persoane) {
		var ms = crtDate.getTime() - persoane[i].data_nastere.getTime();
		var varsta = Math.floor(ms / 31536000000);
		
		if(varsta <= varstaMax) {
			msg += Info(persoane[i]) + "\n";
		}
	}
	
	alert(msg);
}