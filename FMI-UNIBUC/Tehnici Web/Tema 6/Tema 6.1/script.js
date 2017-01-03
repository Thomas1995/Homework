function findWordIn(word, nr) {
	word = word.toLowerCase();
	var p = document.getElementById("p" + nr).textContent;
	var words = p.split(/[\s\n,.:;]+/);
	
	var count = 0;
	for (var i in words) {
		if(words[i].toLowerCase() == word)
			++count;
	}
	
	alert(count);
}

function findW() {
	var word = prompt("Enter a word then a paragraph.");
	var words = word.split(" ");
	findWordIn(words[0], words[1]);
}

function change(nr) {
	var p = document.getElementById("p" + nr);
	p.style.color = "red";
}

function resetp(nr) {
	var p = document.getElementById("p" + nr);
	p.style.color = "black";
}