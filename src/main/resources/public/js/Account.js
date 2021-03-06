function changePassword(newP, oldP, confirmP) {
    const newPassword = document.getElementById(newP);
    const oldPassword = document.getElementById(oldP);
    const confirmPassword = document.getElementById(confirmP);
    const form = newPassword.parentElement;
    confirmPassword.setCustomValidity("");
    if (!form.checkValidity()) {
		if (newPassword.value != confirmPassword.value) {
			confirmPassword.setCustomValidity("Passwords Dont Match");
		} 
		form.getElementsByClassName("hiddenSubmit")[0].click();
	} else if (newPassword.value != confirmPassword.value) {
        confirmPassword.setCustomValidity("Passwords Dont Match");	
        form.getElementsByClassName("hiddenSubmit")[0].click();
	} else {
        confirmPassword.setCustomValidity("");
        loadingOn();
        contactServer("changePassword", {"UUID": getCookie("UUID"), "newP": newPassword.value, "oldP": oldPassword.value}, function (response) {
            loadingOff();
            if (Object.keys(response)[0] == "ErrorClass") {
              
                const message = response[Object.keys(response)[0]].message;
                const code = response[Object.keys(response)[0]].errorCode;
                createDialogue("Incorrect Password", message, code);
            } else {
              createDialogue("Password Change", "Successfully changed your passsword!", null);
            }
        });

	}

   
}
function changeUserName(username) {
    const newUser = document.getElementById(username);
    const form = newUser.parentElement;
    if (!form.checkValidity()) {
        form.getElementsByClassName("hiddenSubmit")[0].click();
    } else {
        loadingOn();
        contactServer("changeAccountData", {"field": "username", "value": newUser.value, "UUID": getCookie("UUID")}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
                window.location.reload();
                createDialogue("Username Change", "Successfully Changed your username!");
            } else {
                const message = response[Object.keys(response)[0]].message;
                const code = response[Object.keys(response)[0]].errorCode;
                createDialogue("Error", message, code);
            }
        });
    }
}
function changeEmail(email) {
    const newEmail = document.getElementById(email);
    const form = newEmail.parentElement;
    if (!form.checkValidity()) {
        form.getElementsByClassName("hiddenSubmit")[0].click();
    } else {
        loadingOn();
        contactServer("changeAccountData", {"field": "email", "UUID": getCookie("UUID"), "value": newEmail.value}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
                createDialogue("Verifying Email", "Sent confirmation code to '" + newEmail.value + "'", null);
            } else {
                const message = response[Object.keys(response)[0]].message;
                const code = response[Object.keys(response)[0]].errorCode;
                createDialogue("Error", message, code);
            }
        });
    }
}
function changePic(pic) {
    const newPic = document.getElementById(pic);
    const form = newPic.parentElement;
    if (!form.checkValidity()) {
        form.getElementsByClassName("hiddenSubmit")[0].click();
    } else {
        loadingOn();
        contactServer("changeAccountData", {"field": "profilePic", "UUID": getCookie("UUID"), "value": newPic.value}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
                window.location.reload();
                createDialogue("Picture Changed", "Your Profile Picture has Been Changed", null);
                document.getElementById("profilePicture").src = newPic.value;
            } else {
                const message = response[Object.keys(response)[0]].message;
                const code = response[Object.keys(response)[0]].errorCode;
                createDialogue("Error", message, code);
            }
        });
    }
}
function changeMcUser(mcUser) {
    const newMcUser = document.getElementById(mcUser);
    const form = newMcUser.parentElement;
    if (!form.checkValidity()) {
        form.getElementsByClassName("hiddenSubmit")[0].click();
    } else {
        loadingOn();
        contactServer("changeAccountData", {"field": "mcUser", "UUID": getCookie("UUID"), "value": newMcUser.value}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
                createDialogue("Verifying Mc User", "A verification code will be sent Ingame! Thanks for verifying!", null);
                document.getElementById("profilePicture").src = newPic.value;
            } else {
                const message = response[Object.keys(response)[0]].message;
                const code = response[Object.keys(response)[0]].errorCode;
                createDialogue("Error", message, code);
            }
        });
    }
}
