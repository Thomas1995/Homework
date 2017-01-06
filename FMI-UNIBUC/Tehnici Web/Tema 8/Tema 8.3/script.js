function afis1() {
    alert("Salut");
}

function afis2() {
    alert("Buna");
}

function createbtns() {
    var styleopt = document.getElementById("styleopt");
    var div = document.createElement("div");
    var btn = [document.createElement("button"), document.createElement("button")];

    for (var i = 0; i < 2; ++i) {
        btn[i].className = styleopt.options[styleopt.selectedIndex].text;
        btn[i].textContent = "buton " + i;

        if (i == 0)
            btn[i].onclick = afis1;
        else
            btn[i].onclick = afis2;

        div.appendChild(btn[i]);
    }

    document.body.appendChild(div);
}