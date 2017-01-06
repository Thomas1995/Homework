function generate() {
    var cuprins = [
        "<section>",
        "<h1>Cuprins</h1>",
        "<ul>",
        "<li><h2>Capitolul 1</h2></li>",
        "<li><h2>Capitolul 2</h2></li>",
        "<li><h2>Capitolul 3</h2></li>",
        "<li><h2>Capitolul 4</h2></li>",
        "<li><h2>Capitolul 5</h2></li>",
        "</ul>",
        "</section>"
    ]

    var cuprinsTotal = "";
    for (var i in cuprins)
        cuprinsTotal += cuprins[i] + "\n";

    var raspuns = prompt("Scrieti INCEPUT sau SFARSIT pentru a se afisa la inceputul, respectiv sfarsitul documentului.");

    if (raspuns == "INCEPUT")
        document.body.innerHTML = cuprinsTotal + document.body.innerHTML;
    else
        if (raspuns == "SFARSIT")
            document.body.innerHTML += cuprinsTotal;
        else
            alert("Input invalid");
}