var move = false;

function keyDown(evt) {
    if (move == true) {
        var img = document.getElementById('img');

        if (evt.keyCode == 37) {
            var prevPos = parseInt(img.style.left);
            var newPos = prevPos - 5;
            img.style.left = newPos + 'px';
        }
        if (evt.keyCode == 39) {
            var prevPos = parseInt(img.style.left);
            var newPos = prevPos + 5;
            img.style.left = newPos + 'px';
        }

        if (evt.keyCode == 38) {
            var prevPos = parseInt(img.style.top);
            var newPos = prevPos - 5;
            img.style.top = newPos + 'px';
        }
        if (evt.keyCode == 40) {
            var prevPos = parseInt(img.style.top);
            var newPos = prevPos + 5;
            img.style.top = newPos + 'px';
        }

        localStorage.setItem('left', parseInt(img.style.left));
        localStorage.setItem('top', parseInt(img.style.top));
    }
}

function startmoving() {
    move = true;
}

function init() {
    if (!localStorage.getItem('left'))
        localStorage.setItem('left', '0');

    if (!localStorage.getItem('top'))
        localStorage.setItem('top', '0');

    var img = document.getElementById('img');
    img.style.left = localStorage.getItem('left') + 'px';
    img.style.top = localStorage.getItem('top') + 'px';
}