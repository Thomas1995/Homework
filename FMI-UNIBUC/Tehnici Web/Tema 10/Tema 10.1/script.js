var date;

function upd() {
    var time = date.getTime() - Date.now();

    var second = (time / 1000) % 60 | 0;
    var minute = (time / (1000 * 60)) % 60 | 0;
    var hour = (time / (1000 * 60 * 60)) | 0;

    document.body.innerHTML = hour + ":" + minute + ":" + second;

    setTimeout(upd, 100);
}

function afis() {
    date = new Date(document.getElementById('dt').value);
    document.body.innerHTML = "";
    upd();
}