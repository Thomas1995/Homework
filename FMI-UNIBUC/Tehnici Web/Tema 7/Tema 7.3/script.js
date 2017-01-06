function addclass() {
    var spans = document.getElementsByTagName("span");

    for (var i in spans) {
        spans[i].className = "highlight";
    }
}