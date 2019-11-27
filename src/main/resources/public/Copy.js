
function copyToClip(str) {
	const textA = document.createElement("textarea");
	textA.value = str;
	document.body.appendChild(textA);
	textA.select();
	document.execCommand("copy");
	document.body.removeChild(textA);
}