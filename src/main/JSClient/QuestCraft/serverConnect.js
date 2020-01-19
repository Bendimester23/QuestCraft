function contactServer() {
	console.log("contacting");
	const xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {
				alert(this.responseText);
			}
		};
		xhttp.open("GET", "http://localhost:4567/signup?username=coolZz&password=1234Colby", true);
		xhttp.send();
}