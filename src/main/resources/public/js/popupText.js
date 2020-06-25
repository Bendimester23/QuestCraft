let boxHTML;
let dialogue;
let currentLoader;

function createDialogue(type, text, code) {
    if (code != null) {
        boxHTML = '<div id="popup"><fieldset><legend class = "legend">' + type + '</legend><div id="closeButtonRight" class = "legend"><button onclick = "close()">Close</button></div><div>Message: ' + text + '</div><div>Error Code: ' + code + '</div></fieldset></div>'
    } else {
        boxHTML = boxHTML = '<div id="popup"><fieldset><legend class = "legend">' + type + '</legend><div id="closeButtonRight" class = "legend"><button onclick = "close()">Close</button></div><div>Message: ' + text + '</div></fieldset></div>'
    }
    document.body.innerHTML += boxHTML;
    dialogue = document.getElementById("popup");
    setTimeout(function () {
        dialogue.style.bottom = "75vh";
    }, 5);
    dialogue.addEventListener("click", function () {
        dialogue.style.bottom = "100vh";
        setTimeout(function () {
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

function openDialogue(height, itemKey, elementPath, i) {
    const element = document.getElementsByClassName(elementPath)[i];
    if (openableElements.hasOwnProperty(itemKey) && !element.contains(document.activeElement)) {

        if (openableElements[itemKey]) {
            element.style.height = "0";
            element.style.borderWidth = "0px";
            element.style.display = "none";
            openableElements[itemKey] = false;
        } else {

            element.style.width = "auto";
            element.style.borderWidth = "5px";
            element.style.display = "block";
            openableElements[itemKey] = true;
            element.style.height = height;
        }
    } else {

        element.style.width = "auto";
        element.style.borderWidth = "5px";
        element.style.display = "block";
        element.style.height = height;
        openableElements[itemKey] = true;
    }
    const mouseDown = document.addEventListener("mousedown", function (event) {

        //console.log(event.clientX + ", " + event.clientY);
        const elemRect = element.parentElement.getBoundingClientRect();
        const bodyRect = document.body.getBoundingClientRect();
        // console.log(bodyRect.top + ", " + bodyRect.left);
        // console.log(elemRect.top + ", " + elemRect.left);
        if (elemRect.left > event.clientX || elemRect.right < event.clientX || elemRect.top > event.clientY || elemRect.bottom < event.clientY) {

            element.style.height = "0px";
            element.style.display = "none";
            element.style.borderWidth = "0px";
            openableElements[itemKey] = false;
            document.removeEventListener("mousedown", mouseDown);
        }
    });
}

// function heightTransition(element, height, time) {
//     const newHeight = height == "auto" ? element.height : height;
//     const addedAmount = height / time;
//     for (let i = 0; i < time; i++) {
//         const addAmount = element.height + (addedAmount * i);
//         setTimeout(function () {
//             element.style.height = "0px";
//             element.style.height = addedAmount + "px";
//         }, 10);
//     }
// }
