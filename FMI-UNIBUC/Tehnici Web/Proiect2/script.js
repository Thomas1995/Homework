var lungs;
var cigaretteX = 0;
var ballX = 245;
var ballY = 500;
var sgnX = -1;
var sgnY = -1;
var speed = 3;

function loadall() {
    lungs = [
        '000000000000000000000000000000000000000000000000',
        '000000000000000000000000111100000000000000000000',
        '000000000000000000000000111100000000000000000000',
        '000000000000000000000000111100000000000000000000',
        '000000000000000000000000111100000000000000000000',
        '000000000000011111000000111100001111100000000000',
        '000000000000111111110000111100011111110000000000',
        '000000000001111111111100111100111111111110000000',
        '000000111111111111111111111111111111111111100000',
        '000011111111111111111111111111111111111111110000',
        '000111111111111111111111100111111111111111111100',
        '001111111111111111111100000000111111111111111100',
        '011111111111111111100000000000111111111111111110',
        '011111111111111110000000000000011111111111111110',
        '011111111111111110000000000000011111111111111110',
        '011111111111111110000000000000011111111111111110',
        '111111111111111110000000000000011111111111111110',
        '111111111111111110000000000000011111111111111111',
        '111111111111111110000000000000011111111111111111',
        '111111111111111110000000000000011111111111111111',
        '111111111111111100000000000000001111111111111111',
        '111111111111111100000000000000001111111111111111',
        '111111111111111100000000000000001111111111111111',
        '111111111111111000000000000000000111111111111111',
        '111111100101010000000000000000000010011111111111',
        '111011001000000000000000000000000000000111100111',
        '010100000000000000000000000000000000001011000010',
    ];

    var score = document.getElementById('inpscore');
    score.value = '0';

    cigaretteX = 0;
    ballX = 245;
    ballY = 500;
    sgnX = -1;
    sgnY = -1;
    speed = 3;

    paint();
}

function paint() {
    var main = document.getElementById('main');
    main.innerHTML = "";

    for (var i = 0; i < lungs.length; ++i)
        for (var j = 0; j < lungs[i].length; ++j) {
            if (lungs[i][j] == '1') {
                var block = document.createElement('div');
                block.className = 'mini';
                block.style.top = i * 10 + 'px';
                block.style.left = (j + 1) * 10 + 'px';
                main.appendChild(block);
            }
        }

    var cigarette = document.createElement('div');
    cigarette.className = 'cigarette';
    cigarette.style.left = cigaretteX + 'px';
    main.appendChild(cigarette);

    checkCollisionWalls();
    checkCollisionWithBlocks();

    if (Math.floor((Math.random() * 300) + 1) == 1)
        speed += 1;

    ballX += sgnX * speed;
    ballY += sgnY * speed;

    if (ballY >= 590) {
        alert("You lost!");
        loadall();
    }

    var ball = document.createElement('div');
    ball.className = 'smoke';
    ball.style.left = ballX + 'px';
    ball.style.top = ballY + 'px';
    main.appendChild(ball);

    setTimeout(paint, 30);
}

function checkCollisionWalls() {
    X = ballX + sgnX * speed;
    Y = ballY + sgnY * speed;

    if (X <= 0 || X >= 500) {
        sgnX = -sgnX;
    }

    if (Y <= 0 || checkCollisionWithCigarette()) {
        sgnY = -sgnY;
    }
}

function checkCollisionWithCigarette() {
    X = ballX + sgnX * speed;
    Y = ballY + sgnY * speed;

    if (Y >= 590 && X >= cigaretteX && X <= cigaretteX + 40)
        return true;
    return false;
}

function checkCollisionWithBlocks() {
    X = ballX + sgnX * speed;
    Y = ballY + sgnY * speed;

    for (var i = 0; i < lungs.length; ++i)
        for (var j = 0; j < lungs[i].length; ++j) {
            if (lungs[i][j] == '1') {
                var bX = (j + 1) * 10;
                var bY = i * 10;
                if (closeTo(X, Y, bX, bY)) {
                    var line = '';
                    for (var k = 0; k < lungs[i].length; ++k) {
                        if (k != j)
                            line += lungs[i][k];
                        else
                            line += '0';
                    }

                    lungs[i] = line;

                    var score = document.getElementById('inpscore');
                    var val = parseInt(score.value) + 1;
                    score.value = val;

                    return;
                }
            }
        }
}

function closeTo(x1, y1, x2, y2) {
    var d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    if (d <= 8)
        return true;
    return false;
}

function keydown(event) {
    if (event.keyCode == 37 && cigaretteX > 0)
        cigaretteX -= (speed + 2);
    if (event.keyCode == 39 && cigaretteX < 450)
        cigaretteX += (speed + 2);
}