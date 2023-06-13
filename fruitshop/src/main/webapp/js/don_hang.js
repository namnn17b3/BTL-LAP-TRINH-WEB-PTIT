var maDonHang = document.querySelectorAll('.ma-don-hang');
document.querySelectorAll('.huy-don-hang__don-hang').forEach((item, index) => {	
	item.onclick = () => {
		themCookie('clickHuyDonHang', `x==${window.pageXOffset}_y==${window.pageYOffset}_id==${maDonHang[index].innerText}`, 3600000 * 24, 60, '/fruitshop/huy-don-hang');
		themCookie('flag', 0, 3600000 * 24, 60, '/fruitshop/don-hang');
	}
});