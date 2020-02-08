//image names in object with license.
const images = {
	QuestCraftSH: '"QuestCraft Shopping District" by Chestly is licensed under CC-BY-ND CC Search 4.0', 
	QuestCraftSpawn: '"QuestCraft Spawn" by Chestly is licensed under CC-BY-ND CC Search 4.0',
	QuestCraftVillage:'"QuestCraft Village" by Chestly is licensed under CC-BY-ND CC Search 4.0'
	};
//initiating variables for global use
let imagePath;
let imageLicense;


//creating variable current image variable to cycle through
let currentPic = 1;
//next function
function next() {
	imagePath = document.getElementById("SlidingImage");
	imageLicense = document.getElementById("imageLicense");
	if (currentPic < Object.keys(images).length - 1) {
		currentPic++;
	} else {
		currentPic = 0;
	}
   
		changeImage()
}
// function preLoadImgs() {
// 	for (let current = 0; current <  Object.keys(images).length; current++) {
// 		let img = new Image();
// 		img.src = "SliderImages/" + Object.keys(images)[current]; + ".png"
// 	}
// }
//prev functin
function prev() {
	imagePath = document.getElementById("SlidingImage");
	imageLicense = document.getElementById("imageLicense");
	if (currentPic > 0) {
		currentPic--;
	} else {
		currentPic = Object.keys(images).length - 1;
	}
	
	changeImage()
}
//changeing image after click
function changeImage() {
	//preLoadImgs();
	let imagesKey = Object.keys(images)[currentPic];
	const setImage = "SliderImages/" + imagesKey + ".png"
	imageLicense.innerHTML = images[imagesKey];
	imagePath.src = setImage;
	loadingOn();
	imagePath.onload = function() {
		if (imagePath.src.includes(setImage)) {
			loadingOff();
		}
	}
}
