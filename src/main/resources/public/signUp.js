let username;
let password;
let checkPassword;
let accountData;
let termOfService;
let inGameUser;
let email; 


function checkForPark() {
	console.log(email);
	if (email.value == "jlpark@seattleschools.org") {
		return true;
	} else {
		return false;
	}
}

function logOut() {
	console.log("logging out");
	setCookie("isSignedIn", false);
	setCookie("isPark", false);
}

function submitClicked() {
	inGameUser = document.getElementById("InGameUser");
	email = document.getElementById("emailField");

	username = document.getElementById("username");
	password = document.getElementById("password");
	checkPassword = document.getElementById("repassword");
	termOfService = document.getElementById("TermsAndService");
	if (password.value != checkPassword.value) {
		checkPassword.setCustomValidity("Passwords Dont Match");
	} else {

		checkPassword.setCustomValidity("");
		allFieldsFilled();
		
	}
}

function logIn() {
	//put data base here
	if (true == true) {
		setCookie("isSignedIn", true);
	}
}

function allFieldsFilled() {
	if (username.value != "" && password.value != "" && checkPassword.value != "" && termOfService.checked == true) {
		const today = new Date();
		
		accountData = {
			"username": username.value,
			"password": password.value,
			"gameUser": inGameUser.value,
			"email": email.value,
			"createdOn": today.getMonth()+1 + "-" + today.getDate() + "-" + today.getFullYear(),
			"isPark": checkForPark(),
		}
		if (checkForPark() == true) {
			setCookie("isPark", true);
		}
		console.log(accountData);
		setCookie("isSignedIn", true);
		
		
	}
	
}
