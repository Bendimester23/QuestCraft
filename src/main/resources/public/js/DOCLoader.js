window.onload = function () {
	const fileName = location.pathname.split("/").slice(-1)[0].slice(0, -5);
	//this.console.log(fileName);
	const registerTabs = document.getElementsByClassName("registerLink");
	//const nav = document.getElementById("navUl");
	let verified = false;
	const navHTML = document.getElementById("nav");
	navHTML.innerHTML = getNav();
	this.contactServer("verify", { UUID: getCookie("UUID") }, function (response) {
		verified = response;
		if (verified == true) {
					const sideBar = document.getElementsByClassName("login-sector")[0];

                if (sideBar != undefined) {
                    const logins = document.getElementById("logins");
                    logins.innerHTML += '<h1>Your Stuff</h1><a class="log-button" href="html/Account.html">Account</a><button class="log-button" onclick="logOut()">Log Out</button>';
            	    logins.setAttribute("style", "display: inherit;")
            	}
			//registerTabs[0].innerHTML = '<li><a href = "Account.html" id = "hiddenlink">Account</a></li><li><a href = "logOut.html" id = "hiddenlink">Log Out</a></li>';

			if (fileName === "Account" || fileName === "Settings") {
				const dataDiv = document.getElementsByClassName("accountData");
				contactServer("getInfo", { UUID: getCookie("UUID") }, function(response) {
					console.log(Object.keys(response)[0]);
					if (Object.keys(response)[0] === "ErrorClass") {
						const message = response[Object.keys(response)[0]].message
						const code = response[Object.keys(response)[0]].errorCode;
						createDialogue("Invalid User", message, code);
					} else if (Object.keys(response)[0] === "Account") {
						 const name = response[Object.keys(response)[0]].username;
						 const pic = response[Object.keys(response)[0]].profilePic;

						 const pictureElement = document.getElementById("account-pic");
						const usernameElement = document.getElementById("account-name");

						usernameElement.innerHTML = name;

						if (pic !== "null") {
							pictureElement.src = pic;
						}

						if (pic != null) {
							document.getElementById("profilePicture").src = pic;
						}
					}
				});
			}
		} else {
			const sideBar = document.getElementsByClassName("login-sector")[0];
            	if (sideBar != undefined) {
                    const logins = document.getElementById("logins");
                    logins.innerHTML += '<h1>Join Us</h1><a class="log-button" href="html/Login.html">Log In</a><a class="log-button" href="html/Register.html">Sign Up</a>';
            	    logins.setAttribute("style", "display: inherit;")
            	}
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



