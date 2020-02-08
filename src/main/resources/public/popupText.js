let boxHTML;
let dialogue;
let currentLoader;
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
		setTimeout(function() {
			dialogue.parentNode.removeChild(dialogue);
		}, 500);
	});
	
}
function loadingOn() {
	document.body.innerHTML += "<img src='randImages/LoadingGif.gif' id='LoadingGif'/>";
	curentLoader = document.getElementById("LoadingGif");
}

function loadingOff() {
	const elem = document.getElementById("LoadingGif");
	if (elem) {
		elem.parentNode.removeChild(elem);
	}
	
}
function makeValidation(element, message) {

}

