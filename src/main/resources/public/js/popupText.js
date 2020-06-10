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
	document.body.innerHTML += "<img src='../img/LoadingGif.gif' id='LoadingGif'/>";
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
let openableElements = {};
function openDialogue(height, itemKey, elementPath) {
    const element = document.getElementById(elementPath);
    if (openableElements.hasOwnProperty(itemKey) && !element.contains(document.activeElement)) {
       
        if (openableElements[itemKey]) {
            element.style.height = "0px";
            element.style.borderWidth = "0px";
            openableElements[itemKey] = false;
        } else {
            element.style.height = height;
			element.style.width = "auto";
			element.style.borderWidth = "5px";
            openableElements[itemKey] = true;
        }
    } else {
        element.style.height = height;
			element.style.width = "auto";
			element.style.borderWidth = "5px";
        openableElements[itemKey] = true;
    }
    const mouseDown = document.addEventListener("mousedown", function(event) {
        
        //console.log(event.clientX + ", " + event.clientY);
        const elemRect = element.getBoundingClientRect();
        const bodyRect = document.body.getBoundingClientRect();
        // console.log(bodyRect.top + ", " + bodyRect.left);
        // console.log(elemRect.top + ", " + elemRect.left);
        if (elemRect.left > event.clientX || elemRect.right < event.clientX || elemRect.top > event.clientY || elemRect.bottom < event.clientY) {
            element.style.height = "0px";
			
			element.style.borderWidth = "0px";
            openableElements[itemKey] = false;
            document.removeEventListener("mousedown", mouseDown);
        }
    });
}
