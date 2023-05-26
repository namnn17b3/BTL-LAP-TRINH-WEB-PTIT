var url = window.location.href;
document.querySelectorAll('.them-vao-gio-hang').forEach((item) => {	
	item.onclick = () => {
		var id = item.href.slice(item.href.lastIndexOf('=') + 1);
		var soLuong = 1;
		try {
			soLuong = parseInt(document.querySelector('.so-luong').innerText);
		}
		catch (e) {}
		var data = `x==${window.pageXOffset}_y==${window.pageYOffset}_soLuong==${soLuong}_url==${'.' + url.slice(url.lastIndexOf('/'))}_id==${id}`;
		themCookie('clickThemVaoGioHang', data, 3600000 * 24, 60, '/fruitshop/xu-ly-gio-hang');
		themCookie('flag', 0, 3600000 * 24, 60, '/fruitshop' + url.slice(url.lastIndexOf('/')));
		item.nextElementSibling.click();
	}
});