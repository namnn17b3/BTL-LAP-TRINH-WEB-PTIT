var listThemVaoGioHang = document.querySelectorAll('.them-vao-gio-hang');
var listGiaSanPhamHidden = document.querySelectorAll('.gia-san-pham-hidden')
document.querySelectorAll('.mua-ngay').forEach((item, index) => {
	item.onclick = () => {
		var id = listThemVaoGioHang[index].href.slice(listThemVaoGioHang[index].href.lastIndexOf('=') + 1);
		var soLuong = 1;
		try {
			soLuong = parseInt(document.querySelector('.so-luong').innerText);
		}
		catch (e) {}
		var tongTien = parseInt(listGiaSanPhamHidden[index].innerText) * soLuong;
		var data = `id==${id}_soLuong==${soLuong}_tongTien==${tongTien}`;
		themCookie('clickMuaNgay', data, 3600000 * 24, 60, '/fruitshop/thanh-toan');
	}
});