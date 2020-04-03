window.onload = function () {
	const fileName = location.pathname.split("/").slice(-1)[0].slice(0, -5);
	this.console.log(fileName);
	const registerTabs = document.getElementsByClassName("registerLink");
	const nav = document.getElementById("navUl");
	let verified = false;
	this.contactServer("verify", { UUID: getCookie("UUID") }, function (response) {
		verified = response;
		if (verified == true) {
			
			registerTabs[0].innerHTML = '<li><a href = "Account.html" id = "hiddenlink">Account</a></li><li><a href = "logOut.html" id = "hiddenlink">Log Out</a></li>';

			if (fileName == "games") {
				const notifySign = document.getElementById("NotifySign");
				notifySign.parentNode.removeChild(notifySign);
				const games = document.getElementById("actualGames");
				games.style.display = "block";
			} else if (fileName == "Account") {
				const dataDiv = document.getElementsByClassName("accountData");
				contactServer("getInfo", { UUID: getCookie("UUID") }, function(response) {
					console.log(Object.keys(response)[0]);
					if (Object.keys(response)[0] == "ErrorClass") {
						const message = response[Object.keys(response)[0]].message
						const code = response[Object.keys(response)[0]].errorCode;
		
						createDialogue("Invalid User", message, code);
					} else if (Object.keys(response)[0] == "Account") {
						const name = response[Object.keys(response)[0]].username;
						const IGN = response[Object.keys(response)[0]].inGameUser;
						const email = response[Object.keys(response)[0]].email;
						const pic = response[Object.keys(response)[0]].profilePic;
						dataDiv[0].innerHTML += name;
						dataDiv[1].innerHTML += IGN;
						dataDiv[2].innerHTML += email;
						if (pic != null) {
							document.getElementById("profilePicture").src = pic;
						}
					}
				});
			} 
		} else {
			registerTabs[0].innerHTML = '<li><a href = "SignUp.html" id = "hiddenlink">Sign Up</a></li><li><a href = "LogIn.html" id = "hiddenlink">Log In</a></li>';
		}
	});


}

const path = "";
function getPath() {
	return path;
}

function contactServer(path, params, callBackFunc) {
	let fullPath = getPath() + "/" + path + "?";
	const keys = Object.keys(params);
	for (let i = 0; i < keys.length; i++) {
		const currentKey = keys[i];
		const currentValue = encodeURIComponent(params[currentKey]);
		fullPath += currentKey + "=" + currentValue;
		if (keys.length > 1 && i < keys.length - 1) {
			fullPath += "&";
		}
	}
	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			let response;
			if (this.responseText != null) {
				response = JSON.parse(this.responseText);
			} else  {
				createDialogue("internal Error", "Error, Please Reload page and report", 4);
			}
			callBackFunc(response);
		}
	};
	xhttp.open("GET", fullPath);
	xhttp.send();

}



