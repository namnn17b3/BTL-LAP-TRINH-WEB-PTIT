document.querySelectorAll('a').forEach(item => {
	/*item.onclick = () => {		
		var csrfToken = document.querySelector('#csrf-token').innerText;
		document.cookie = `csrf_token=${csrfToken}; expires=${new Date(new Date().getTime() + 3600000 * 24).toUTCString()}; path=/fruitshop`;
	}*/
	var csrfToken = document.querySelector('#csrf-token').innerText;
	if (item.href.indexOf('?') < 0) {		
		item.href = item.href + `?csrf-token=${encodeURIComponent(csrfToken)}`;
	}
	else if (item != document.querySelector('.nut-len-dau-trang')) {
		item.href = item.href + `&csrf-token=${encodeURIComponent(csrfToken)}`;
	}
});

document.querySelectorAll('form').forEach(item => {
	/*item.onclick = () => {		
		var csrfToken = document.querySelector('#csrf-token').innerText;
		document.cookie = `csrf_token=${csrfToken}; expires=${new Date(new Date().getTime() + 3600000 * 24).toUTCString()}; path=/fruitshop`;
	}*/
	var csrfToken = document.querySelector('#csrf-token').innerText;
	if (item.action.indexOf('?') < 0) {		
		item.action = item.action + `?csrf-token=${encodeURIComponent(csrfToken)}`;
	}
	else {
		item.action = item.action + `&csrf-token=${encodeURIComponent(csrfToken)}`;
	}
});