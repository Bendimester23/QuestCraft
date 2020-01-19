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
				if (this.responseText == "true") {
				console.log("yaya");
                    success.style.visibility = "visible";
                    window.location = "http://localhost:63342/QuestCraft/src/main/JSClient/QuestCraft/Account.html";
				} else {
                    console.log("NAyad");
				}
			}
		};
		console.log("http://localhost:4567/login?username=" + username.value + "&password=" + password.value);
		xhttp.open("GET", "http://localhost:4567/logIn?username=" + username.value + "&password=" + password.value, true);
		xhttp.send();
}