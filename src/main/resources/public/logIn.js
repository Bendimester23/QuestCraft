let username;
let password;
let success;

function checkLogIn() {
    username = document.getElementById("username");
    password = document.getElementById("password");
    success = document.getElementById("loginResponse");
    console.log("contacting");
	const xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);

			const response = JSON.parse(this.responseText);
            if (Object.keys(response)[0] == "ErrorClass") {
                const message = response[Object.keys(response)[0]].message
                const code = response[Object.keys(response)[0]].errorCode;

                createDialogue("Invalid User", message, code);
            } else if (Object.keys(response)[0] == "String") {
                const uuid = response[Object.keys(response)[0]];
                console.log(uuid);
                setCookie("UUID", uuid);
                console.log(getCookie("UUID"));
                 window.location = "Account.html"



            } else {
            //dont even know what happened
            }

		    }
		};

		xhttp.open("GET",  getPath() + "/logIn?username=" + username.value + "&password=" + password.value, true);
		xhttp.send();
}