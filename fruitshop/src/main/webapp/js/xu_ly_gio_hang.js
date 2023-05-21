var url = window.location.href;
document.querySelectorAll('.them-vao-gio-hang').forEach((item) => {	
	item.onclick = () => {
		var id = item.nextElementSibling.href.slice(item.nextElementSibling.href.lastIndexOf('=') + 1);
		var data = `x=${window.pageXOffset}_y=${window.pageYOffset}_soLuong=1_url=${'.' + url.slice(url.lastIndexOf('/'))}_id=${id}`;
		themCookie('clickThemVaoGioHang', data, 3600000 * 24, 60, '/fruitshop/xu-ly-gio-hang');
		themCookie('flagClickThemVaoGioHang', 0, 3600000 * 24, 60, '/fruitshop' + url.slice(url.lastIndexOf('/')));
		item.nextElementSibling.click();
		console.log(data);
	}
});