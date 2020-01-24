function setCookie(name, value) {
	document.cookie = name + "=" + value;
}

function getCookie(name) {
	const docCookie = document.cookie;

	if (docCookie != null) {
	
		const cookieArray = docCookie.split(";");

		for (let cookie = 0; cookie < cookieArray.length; cookie++) {
			
			const partOfCookie = cookieArray[cookie].split("=");
			
			if (partOfCookie[0].trim() == name) {
				return partOfCookie[1];
			}
		}
	}
}
function hasCookie(name) {
	if (getCookie(name) != null) {
		return true;
	} else {
		return false;
	}
}
function delCookie(name) {
	if (hasCookie(name)) {
		document.cookie = name + "=a; expires=Thu, 18 Dec 2013 12:00:00 UTC;";
	}
}
