window.onload = function () {
	const registerTabs = document.getElementsByClassName("registerLink");
	const nav = document.getElementById("navUl");
	
	if (getCookie("isSignedIn") == "true") {
		
		registerTabs[0].innerHTML = '<li><a href = "Account.html" id = "hiddenlink">Account</a></li><li><a href = "logOut.html" id = "hiddenlink">Log Out</a></li>';
		if (document.getElementById("actualGames") != null) {
			const notifySign = document.getElementById("NotifySign");
			notifySign.parentNode.removeChild(notifySign);
			const games = document.getElementById("actualGames");
			games.style.display = "block";
		}
	} else {
		registerTabs[0].innerHTML = '<li><a href = "SignUp.html" id = "hiddenlink">Sign Up</a></li><li><a href = "LogIn.html" id = "hiddenlink">Log In</a></li>'
	}
}

			