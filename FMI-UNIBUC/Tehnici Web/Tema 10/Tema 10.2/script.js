var turn = 0;
var move = true;

function button(id) {
    var btn = document.getElementById(id);

    if (btn.textContent != '-')
        return;

    if (++turn % 2 == 0)
        btn.textContent = 'x';
    else
        btn.textContent = '0';
    move = true;

    var i = id / 10 | 0;
    var j = id % 10;

    var win1 = true, win2 = true;
    for (var k = 1; k <= 3; ++k) {
        var aux = document.getElementById(k + '' + j);
        var aux2 = document.getElementById(i + '' + k);

        if (aux.textContent != btn.textContent)
            win1 = false;
        if (aux2.textContent != btn.textContent)
            win2 = false;
    }

    var win3 = false;
    if (i == j) {
        var aux = document.getElementById('11');
        var aux2 = document.getElementById('22');
        var aux3 = document.getElementById('33');

        if (aux.textContent == aux2.textContent && aux.textContent == aux3.textContent)
            win3 = true;
    }

    if (i == 3 - j + 1) {
        var aux = document.getElementById('13');
        var aux2 = document.getElementById('22');
        var aux3 = document.getElementById('31');

        if (aux.textContent == aux2.textContent && aux.textContent == aux3.textContent)
            win3 = true;
    }

    if (win1 || win2 || win3) {
        alert(btn.textContent + " e castigatorul!");
        reset();
    }
}

function reset() {
    for (var i = 1; i <= 3; ++i)
        for (var j = 1; j <= 3; ++j) {
            var btn = document.getElementById(i + '' + j);
            btn.textContent = '-';
        }
}

function upd() {
    if (move == false) {
        alert("Te gandesti prea mult");
        reset();
    }
    else {
        move = false;
    }
    setTimeout(upd, 5000);
}