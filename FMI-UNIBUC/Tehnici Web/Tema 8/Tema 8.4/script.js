function done() {
    var mail = document.getElementById("email");

    var valid = true;

    var gmailformat = "@gmail.com";
    var inputemail = mail.value;

    if (inputemail.length <= gmailformat.length) {
        valid = false;
    }
    else {
        for (var i = 1; i <= gmailformat.length; ++i) {
            if (gmailformat[gmailformat.length - i] != inputemail[inputemail.length - i]) {
                valid = false;
                break;
            }
        }
    }

    if (valid == false)
        alert("The e-mail must be: something@gmail.com");
    else
        alert("Success!");
}