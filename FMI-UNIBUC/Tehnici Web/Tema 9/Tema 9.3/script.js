function loadall() {
    var nav = document.createElement('nav');
    document.body.appendChild(nav);

    for (var i = 0; i <= 9; ++i)
        nav.innerHTML += "<div id='b" + i + "'>" + i + "</div>";
}

function keydown(evt) {
    if (evt.keyCode >= 48 && evt.keyCode <= 57) {
        var nr = evt.keyCode - 48;
        var elem = document.getElementById("b" + nr);
        elem.className = "pressed";
    }
}

function keyup(evt) {
    if (evt.keyCode >= 48 && evt.keyCode <= 57) {
        var nr = evt.keyCode - 48;
        var elem = document.getElementById("b" + nr);
        elem.className = "";
    }
}