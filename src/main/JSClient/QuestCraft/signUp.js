let username;
let password;
let checkPassword;
let accountData;
let termOfService;
let inGameUser;
let email;



function logOut() {
	console.log("logging out");
	setCookie("isSignedIn", false);
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
	} else if (password.length <= 6) {
		password.setCustomValidity("Please lengthen password to over 6 characters");
	} else if (checkPassword.length <= 6) {
		checkPassword.setCustomValidity("Please lengthen password to over 6 characters");		
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
			"createdOn": today.getMonth() + 1 + "-" + today.getDate() + "-" + today.getFullYear(),
		}
		console.log(accountData);
		setCookie("isSignedIn", true);
		

	}

}
