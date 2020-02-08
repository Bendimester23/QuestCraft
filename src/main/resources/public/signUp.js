let username;
let password;
let checkPassword;
let accountData;
let termOfService;
let inGameUser;
let email;



function logOut() {
	setCookie("UUID", null);
}

function submitClicked() {
	console.log("submit hit");
	const form  = document.getElementsByClassName("form")[0];
	inGameUser = document.getElementById("InGameUser");
	email = document.getElementById("email");
	username = document.getElementById("username");
	password = document.getElementById("password");
	checkPassword = document.getElementById("repassword");
	termOfService = document.getElementById("TermsAndService");
	
 	if (!form.checkValidity()) {
		if (password.value != checkPassword.value) {
			checkPassword.setCustomValidity("Passwords Dont Match");
		}
		document.getElementsByClassName("hiddenSubmit")[0].click();
	 } else if (password.value != checkPassword.value) {
		checkPassword.setCustomValidity("Passwords Dont Match");	
	} else {
		checkPassword.setCustomValidity("");
		allFieldsFilled();

	}
}


function allFieldsFilled() {
	if (username.value != "" && password.value != "" && checkPassword.value != "" && termOfService.checked == true) {
//		const today = new Date();
//
//		accountData = {
//			"username": username.value,
//			"password": password.value,
//			"gameUser": inGameUser.value,
//			"email": email.value,
//			"createdOn": today.getMonth() + 1 + "-" + today.getDate() + "-" + today.getFullYear(),
//		}
		loadingOn();
		contactServer("signup", {username: username.value, password: password.value, email: email.value, mcUser: inGameUser.value}, function(response) {
			loadingOff();
			if (Object.keys(response)[0] == "ErrorClass") {
				const message = response[Object.keys(response)[0]].message
				const code = response[Object.keys(response)[0]].errorCode;

				createDialogue("Failed to Create Account", message, code);
			} else if (Object.keys(response)[0] == "String") {
				const uuid = response[Object.keys(response)[0]];
				console.log(uuid);
				setCookie("UUID", uuid);
				 window.location = "Account.html";
			} else {

			}

		});
	}

}
