function close() {
    var alertBox = document.getElementById("malert");

    if (alertBox != null)
        document.body.removeChild(alertBox);
}

function myalert() {
    close();

    var alertHTML = [
        "<div style=\"text-align:center;position:fixed;border:1px solid black;top:10px;left:500px;width:300px;background-color:#d3d3d3;box-shadow: 10px 10px 5px #888888\" id=\"malert\">",
        "Salut!<br>",
        "<button style=\"float:right\" id=\"okbtn\">Ok</button>",
        "</div>",
    ]

    var htmlToAdd = "";
    for(var i in alertHTML)
        htmlToAdd += alertHTML[i] + "\n";

    document.body.innerHTML += htmlToAdd;

    var btn = document.getElementById("okbtn");
    btn.onclick = close;
}