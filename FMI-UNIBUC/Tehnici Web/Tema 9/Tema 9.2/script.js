function newpost() {
    var blog = document.getElementById('blog');
    var post = document.getElementById('post');
    blog.innerHTML += "<p>" + post.value + "</p>"

    if (localStorage.getItem('nrpost'))
        localStorage.setItem('nrpost', '0');

    var nrp = parseInt(localStorage.getItem('nrpost')) + 1;
    localStorage.setItem('nrpost', nrp);

    var title = document.getElementById('title');
    var date = document.getElementById('date');
    localStorage.setItem('post' + nrp, title.value + ' ' + date.value.toString());
}

function showarh() {
    var nrp = localStorage.getItem('nrpost');
    if (nrp) {
        for (var i = 1; i <= nrp; ++i) {
            var str = localStorage.getItem('post' + i).toString();
            document.body.innerHTML += "<p>" + localStorage.getItem('post' + i).toString() + "</p>";
        }
    }
}