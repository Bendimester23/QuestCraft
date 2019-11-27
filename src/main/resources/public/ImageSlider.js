//image names in object with license.
const images = {
	QuestCraftS: '"QuestCraft Village Chimmney" by roblocow is licensed under CC BY-NC-ND 4.0 (CC Search)', 

	QuestCraftSD: '"QuestCraft Shopping Discrict" by roblocow is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftVF: '"QuestCraft Village Farm" by roblocow is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftCM:'"QuestCraft Community Mine" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftCNP:'"QuestCraft Community nether portal" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftDT:'"QuestCraft Dead Tree" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftEJ:'"QuestCraft Excavator Jail" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftIOS:'"QuestCraft inside of Spawn" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftITCR:'"QuestCraft inside of Court" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftOOS:'"QuestCraft outside of Spawn" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftOTCR:'"QuestCraft Outside of Court" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftTommyB:'"QuestCraft Tommys Base" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftTOP:'"QuestCraft The Out Post" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',

	QuestCraftTWT:'"QuestCraft Water Tower" by Chestly is licensed under CC BY-NC-ND 4.0 (CC Search)',
	
	};
//initiating variables for global use
let imagePath;
let imageLicense;


//creating variable current image variable to cycle through
let currentPic = 0;
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
	if (currentPic >0) {
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
}