const newsCount = 4;
window.onload = function() {
    var newsSector = document.getElementById('news-sector');
    this.contactServer("../news","", function(res) {
        var html = "";
        for (var i = 0;i<newsCount;i++) {

            html = html + "<div>"+
            "<h1>"+res.ArrayList[i].title+"</h1>"+
            "<img src="+res.ArrayList[i].picUrl+">"+
            "<p>"+res.ArrayList[i].description+"</p>"+
            "<a href="+res.ArrayList[i].articleUrl+">More...</a>"+
            "</div>";
        }
        newsSector.innerHTML = html;
    });
}

function contactServer(path, params, callBackFunc) {
	let fullPath = getPath() + "/" + path + "?";
	const keys = Object.keys(params);
	for (let i = 0; i < keys.length; i++) {
		const currentKey = keys[i];
		const currentValue = encodeURIComponent(params[currentKey]);
		fullPath += currentKey + "=" + currentValue;
		if (keys.length > 1 && i < keys.length - 1) {
			fullPath += "&";
		}
	}
	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			let response;
			if (this.responseText != null) {
				response = JSON.parse(this.responseText);
			} else  {
				createDialogue("internal Error", "Error, Please Reload page and report", 4);
			}
			callBackFunc(response);
		}
	};
	xhttp.open("GET", fullPath);
	xhttp.send();

}