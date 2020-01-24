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
                        const uuid = response[Object.keys(response)[0].uuid];
                        console.log(uuid);
                        setCookie("UUID", true);
                    } else {
                    //dont even know what happened
                    }

        		    }
        		};

        		xhttp.open("GET",  getPath() + "/signup?username=" + username.value + "&password=" + password.value, true);
        		xhttp.send();

	}

}
