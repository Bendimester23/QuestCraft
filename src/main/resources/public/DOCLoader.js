window.onload = function () {
	const registerTabs = document.getElementsByClassName("registerLink");
	const nav = document.getElementById("navUl");
	
	if (getCookie("isSignedIn") == "true") {
	
		registerTabs[0].innerHTML = '<li><a href = "Account.html" id = "hiddenlink">Account</a></li><li><a href = "logOut.html" id = "hiddenlink">Log Out</a></li>';
		if (getCookie("isPark")) {
			nav.innerHTML += '<li><a href ="portfolio.html" id = "hiddenlink">Portfolio</a></li>';
		}
	} else {
		registerTabs[0].innerHTML = '<li><a href = "SignUp.html" id = "hiddenlink">Sign Up</a></li><li><a href = "LogIn.html" id = "hiddenlink">Log In</a></li>'
	}
}

			