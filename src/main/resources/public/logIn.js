let username;
let password;
let success;

function checkLogIn() {
    username = document.getElementById("username");
    password = document.getElementById("password");
    success = document.getElementById("loginResponse");
    console.log("contacting");
    loadingOn();
    contactServer("logIn", { username: this.username.value, password: this.password.value }, function (response)  {
        
        if (Object.keys(response)[0] == "ErrorClass") {
            const message = response[Object.keys(response)[0]].message
            const code = response[Object.keys(response)[0]].errorCode;
            loadingOff();
            createDialogue("Invalid User", message, code);
        } else if (Object.keys(response)[0] == "String") {
            const uuid = response[Object.keys(response)[0]];
            console.log(uuid);
            setCookie("UUID", uuid);
            loadingOff();
            window.location = "Account.html";



        } else {
            //dont even know what happened
        }

    });


}