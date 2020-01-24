let boxHTML;
let dialogue;
function createDialogue (type, text, code) {
	if (code != null) {
		boxHTML = '<div id="popup"><fieldset><legend class = "legend">'+type+'</legend><div id="closeButtonRight" class = "legend"><button onclick = "close()">Close</button></div><div>Message: ' + text + '</div><div>Error Code: ' + code + '</div></fieldset></div>'
	} else {    
		boxHTML = boxHTML = '<div id="popup"><fieldset><legend class = "legend">'+type+'</legend><div id="closeButtonRight" class = "legend"><button onclick = "close()">Close</button></div><div>Message: ' + text + '</div></fieldset></div>'
	}
	document.body.innerHTML += boxHTML;
	dialogue = document.getElementById("popup");
	setTimeout(function() {
		dialogue.style.bottom = "75vh";
	}, 5);
	dialogue.addEventListener("click", function() {
		dialogue.style.bottom = "100vh";
	});
	
}



