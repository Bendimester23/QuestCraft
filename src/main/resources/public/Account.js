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
        contactServer("changeUsername", {"newUser": newUser.value, "UUID": getCookie("UUID")}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
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
        contactServer("addPendingEmail", {"UUID": getCookie("UUID"), "email": newEmail.value}, function(response) {
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
        contactServer("changeProfilePic", {"UUID": getCookie("UUID"), "URL": newPic.value}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
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

