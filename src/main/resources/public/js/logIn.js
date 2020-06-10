function checkLogIn() {
    const username = document.getElementById("username");
    const password = document.getElementById("password");
	const form  = document.getElementsByClassName("form")[0];
    console.log("contacting");
    loadingOn();
    if (!form.checkValidity()) {
        	document.getElementsByClassName("hiddenSubmit")[0].click();
    } else {}
    contactServer("logIn", { "username": username.value, "password":password.value }, function (response)  {
        
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

