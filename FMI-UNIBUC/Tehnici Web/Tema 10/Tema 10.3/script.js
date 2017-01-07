function button(id) {
    var btn = document.getElementById(id);

    var i = id / 10 | 0;
    var j = id % 10;

    var btnup = document.getElementById((i - 1) + '' + j);
    var btndown = document.getElementById((i + 1) + '' + j);
    var btnleft = document.getElementById(i + '' + (j - 1));
    var btnright = document.getElementById(i + '' + (j + 1));

    btns = [btnup, btndown, btnleft, btnright];

    for (var i = 0; i < 4; ++i) {
        if (btns[i]) {
            if (btns[i].textContent == '-') {
                btns[i].textContent = btn.textContent;
                btn.textContent = '-';

                var moveselem = document.getElementById('moves');
                var moves = parseInt(moveselem.value) + 1;
                moveselem.value = moves;

                break;
            }
        }
    }
}

function loadall() {
    letters = [];
    for (var i = 65; i < 80; ++i)
        letters.push(String.fromCharCode(i));
    letters.push('-');

    for (var i = 1; i <= 100; ++i) {
        var i1 = Math.floor((Math.random() * 16));
        var i2 = Math.floor((Math.random() * 16));
        var aux = letters[i1];
        letters[i1] = letters[i2];
        letters[i2] = aux;
    }

    var nr = 0;
    for(var i = 1; i <= 4; ++i)
        for (var j = 1; j <= 4; ++j) {
            var btn = document.getElementById(i + '' + j);
            btn.textContent = letters[nr++];
        }
}