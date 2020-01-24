window.onload = function () {
	const registerTabs = document.getElementsByClassName("registerLink");
	const nav = document.getElementById("navUl");
	let verified = false;
	const xhttp = new XMLHttpRequest();
    		xhttp.onreadystatechange = function () {
    			if (this.readyState == 4 && this.status == 200) {

                verified = Object.keys(response)[0];
                }
    		};

    		xhttp.open("GET", getPath() + "/verify?UUID=" + getCookie("UUID"));
    		xhttp.send();
	if (verified) {
		
		registerTabs[0].innerHTML = '<li><a href = "Account.html" id = "hiddenlink">Account</a></li><li><a href = "logOut.html" id = "hiddenlink">Log Out</a></li>';

		if (document.getElementById("actualGames") != null) {
			const notifySign = document.getElementById("NotifySign");
			notifySign.parentNode.removeChild(notifySign);
			const games = document.getElementById("actualGames");
			games.style.display = "block";
		}
	} else {
		registerTabs[0].innerHTML = '<li><a href = "SignUp.html" id = "hiddenlink">Sign Up</a></li><li><a href = "LogIn.html" id = "hiddenlink">Log In</a></li>';
	}
}

const path = "http://localhost:4567/";
function getPath() {
    return path;
}

			