var url = window.location.href;
var csrfToken = url.substring(url.lastIndexOf('csrf-token=') + 11, url.length);
url = url.substring(0, url.indexOf('/fruitshop')) + `/fruitshop/api/admin/main?csrf-token=${csrfToken}`;
if (url.indexOf('http') >= 0) {
	url = url.replace('http', 'ws');
}
else {
	url = url.replace('https', 'wss');
}
var connection = new WebSocket(url);

connection.onopen = () => {
	console.log('Connected to server...');
	connection.send('');
	setInterval(() => {		
		connection.send('');
	}, 1000);
}

connection.onmessage = (response) => {
	var data = JSON.parse(response.data);
	var listGiaTri = document.querySelectorAll('.noi-dung-thanh-phan-grid div.gia-tri');
	for (var i = 0; i < listGiaTri.length; i++) {
		listGiaTri[i].innerText = data[i];
		if (i == 0) {
			listGiaTri[i].innerText = data[i] + ' VNĐ';
		}
	}
}

connection.onclose = () => {
	console.log('Đã đóng kết nối');
}

connection.onerror = (error) => {
	console.log('Lỗi: ' + error);
}

window.addEventListener('beforeunload', () => {
	connection.close();
});