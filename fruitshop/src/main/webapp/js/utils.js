// Vô hiệu hóa khi có thông báo
function voHieuHoaCuonChuot(e) {
    e.preventDefault();
}

function themCookie(name, value, time, defaultValue = 60, path='/fruitshop/register') {
	if (document.cookie == '' && name == 'time') {
		document.cookie = `${name}=${defaultValue}; expires=${new Date(new Date().getTime() + time).toUTCString()}; path=${path}`;
		return;
	}
	document.cookie = `${name}=${value}; expires=${new Date(new Date().getTime() + time).toUTCString()}; path=${path}`;
}

function layCookie(name, defaultValue) {
	if (document.cookie == '') {
		return defaultValue;
	}
	var cookies = document.cookie.split(';').map(item => {
		return item.trim().split('=');
	});
	for (var item of cookies) {
		if (item[0] == name) {
			return parseInt(item[1]);
		}
	}
	return defaultValue;
}

document.querySelectorAll('.tien-format').forEach(item => {
	var gia = parseInt(item.innerText).toLocaleString();
	item.innerText = gia.replaceAll(',', '.');
});

function callAPI(apiUrl, method, data=null, handler) {
    if (data instanceof FormData) {
        console.log(data.get('jsonData'));
    }
    accessToken = localStorage.getItem('accessToken') ? localStorage.getItem('accessToken') : 'abcxyz';

    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", handler);

    xhr.open(method, apiUrl, true);
    xhr.setRequestHeader("Authorization", `Bearer ${accessToken}`);
    xhr.send(data);
}

function callAPIDowload(url, method,  data=null, handler) {
    apiUrl = `${prefixUrl}${url}`;
    accessToken = localStorage.getItem('accessToken') ? localStorage.getItem('accessToken') : 'abcxyz';

    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("load", handler);

    xhr.open(method, apiUrl, true);
    xhr.responseType = 'blob';
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", `Bearer ${accessToken}`);
    xhr.send(data);
}