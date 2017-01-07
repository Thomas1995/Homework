var wrong = [];
var letters = [];
var wait = false;

function keydown(evt) {
    if (wait == true)
        return;

    wait = true;
    if (evt.keyCode >= 65 && evt.keyCode <= 90) {
        var chr = String.fromCharCode(evt.keyCode + 32);
        if (letters.length > 0) {
            if (letters[0][0] != chr) {
                wrong.push(letters[0]);
                letters.shift();
            }
            else {
                if (--letters[0][1] == -1)
                    letters.shift();
            }
        }
    }
    wait = false;
}

function upd() {
    var board = document.getElementById('board');

    var letter = Math.floor((Math.random() * 26));
    var lvl = Math.floor((Math.random() * 4));

    letters.push([String.fromCharCode(97 + letter), lvl]);
    var txt = '';

    txt += "<font color=\"red\">";
    for (var i = 0; i < wrong.length; ++i) {
        var tagbefore = '';
        var tagafter = '';

        if (wrong[i][1] == 1) {
            tagbefore = '<i>';
            tagafter = '</i>';
        }

        if (wrong[i][1] == 2) {
            tagbefore = '<b>';
            tagafter = '</b>';
        }

        if (wrong[i][1] == 3) {
            tagbefore = '<b><i>';
            tagafter = '</i></b>';
        }

        txt += tagbefore + wrong[i][0] + tagafter + ' ';
    }
    txt += "</font>";

    for (var i = 0; i < letters.length; ++i) {
        var tagbefore = '';
        var tagafter = '';

        if (letters[i][1] == 1) {
            tagbefore = '<i>';
            tagafter = '</i>';
        }

        if (letters[i][1] == 2) {
            tagbefore = '<b>';
            tagafter = '</b>';
        }

        if (letters[i][1] == 3) {
            tagbefore = '<b><i>';
            tagafter = '</i></b>';
        }

        txt += tagbefore + letters[i][0] + tagafter + ' ';
    }
    board.innerHTML = txt;

    var limit = 1.0 * board.clientWidth / 11.63;

    if (letters.length + wrong.length <= limit) {
        setTimeout(upd, 300);
    }
    else {
        alert("Ai pierdut!");
    }
}