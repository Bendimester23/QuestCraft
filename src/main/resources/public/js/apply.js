function submitApp(userField, emailField, q1Field, q2Field, q3Field, q4Field, rulesField, discordfield, discordIDField) {
    const user = document.getElementById(userField);
    const email = document.getElementById(emailField);
    const q1 = document.getElementById(q1Field);
    const q2 = document.getElementById(q2Field);
    const q3 = document.getElementById(q3Field);
    const q4 = document.getElementById(q4Field);
    const rules = document.getElementById(rulesField);
    const discordUser = document.getElementById(discordfield);
    const discordID = document.getElementById(discordIDField);
    const form = user.parentElement;
    if (!form.checkValidity()) {
        form.getElementsByClassName("hiddenSubmit")[0].click();
    
    } else {
        const completeApp = "1) " + q1.value + " 2) " + q2.value + " 3) " + q3.value + " 4) " + q4.value;
        loadingOn();
        contactServer("createApplication", {"questions": completeApp, "mcUser": user.value, "email": email.value, "discordUser": discordUser.value + "#" + discordID.value, "UUID": getCookie("UUID")}, function(response) {
            loadingOff();
            if (Object.keys(response)[0] == "String") {
                createDialogue("Application", "Thanks for Applying, You application has been added to the queue, it will take around a day to approve", null);
            } else {
                const message = response[Object.keys(response)[0]].message;
                const code = response[Object.keys(response)[0]].errorCode;
                createDialogue("Error", message, code);
            }
        });
    }
}