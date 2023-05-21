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