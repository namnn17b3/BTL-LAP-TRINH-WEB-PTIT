document.querySelectorAll('.thanh-phan-phan-trang').forEach(item => {
	item.onclick = () => {
		themCookie('clickGioHang', 1, 3600000 * 24, 60, '/fruitshop/gio-hang');
		item.firstElementChild.click();
	}
});

var listDongGioHang = document.querySelectorAll('.dong-gio-hang');
var listNutTru = document.querySelectorAll('.nut-tru');
var listNutCong = document.querySelectorAll('.nut-cong');
var listHienThiSoLuong = document.querySelectorAll('.hien-thi-so-luong span');
var lisTongTienSanPham = document.querySelectorAll('.tong-tien-san-pham');
var listSoLuongSanPham = document.querySelectorAll('.so-luong-san-pham-hidden');
var listSoLuongSanPhamConLai = document.querySelectorAll('.so-luong-san-pham-con-lai');
var listTrangThaiSanPham = document.querySelectorAll('.trang-thai-san-pham span');
var listIdSanPham = document.querySelectorAll('.id-san-pham');
var listNutLoaiBoSanPham = document.querySelectorAll('.nut-loai-bo-san-pham');
var listGiaSanPham = document.querySelectorAll('.gia-san-pham');
var thanhTien = parseInt(document.querySelector('#thanh-tien').innerText);
var vongTronSoLuong = document.querySelector('.vong-tron-so-luong');
var phanTrangVaDonHang = document.querySelector('.phantrang-donhang');

listNutTru.forEach((item, index) => {
	item.onmousemove = () => {
		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
		var soLuong = soLuongPre - 1;
		if (soLuong <= 0) {
			listNutTru[index].style.cursor = 'not-allowed';
		}
		else {
			listNutTru[index].style.cursor = 'pointer';
		}
	}
});

listNutCong.forEach((item, index) => {
	item.onmousemove = () => {
		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
		var soLuong = soLuongPre + 1;
		var soLuongSanPhamConLai = parseInt(listSoLuongSanPhamConLai[index].innerText);
		if (soLuong > soLuongSanPhamConLai) {
			listNutCong[index].style.cursor = 'not-allowed';
		}
		else {
			listNutCong[index].style.cursor = 'pointer';
		}
	}
});

listNutTru.forEach((item, index) => {
	item.onclick = () => {
		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
		var soLuong = soLuongPre - 1;
		// var soLuongSanPhamConLai = parseInt(listSoLuongSanPhamConLai[index].innerText);
		if (soLuong > 0) {
			listSoLuongSanPham[index].innerText = soLuong.toString();
			listNutTru[index].style.cursor = 'pointer';
			/*if (soLuong <= soLuongSanPhamConLai) {
				listTrangThaiSanPham[index].innerText = 'Còn hàng';
				listTrangThaiSanPham[index].style.color = '#28a745';
				listNutCong[index].style.cursor = 'pointer';
			}*/
			if (soLuong <= 99) {
				listHienThiSoLuong[index].innerText = soLuong.toString();
			}
			else {
				listHienThiSoLuong[index].innerText = '99+';
			}
		}
		else {
			soLuong = 1;
			listHienThiSoLuong[index].innerText = '1';
			listSoLuongSanPham[index].innerText = '1';
			// listNutTru[index].style.cursor = 'not-allowed';
		}
		thanhTien = thanhTien + (soLuong - soLuongPre) * parseInt(listGiaSanPham[index].innerText);
		document.querySelector('#thanh-tien').innerText = thanhTien.toString();
		listHienThiSoLuong[index].title = soLuong.toString();
		themCookie(listIdSanPham[index].innerText, soLuong, 3600000 * 24, 60, '/fruitshop/gio-hang');
	}
});

listNutCong.forEach((item, index) => {
	item.onclick = () => {
		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
		var soLuong = soLuongPre + 1;
		var soLuongSanPhamConLai = parseInt(listSoLuongSanPhamConLai[index].innerText);
		console.log(index);
		/*if (soLuong > 0) {
			listNutTru[index].style.cursor = 'pointer';
		}*/
		if (soLuong > soLuongSanPhamConLai) {
			soLuong = soLuongSanPhamConLai;
			// listTrangThaiSanPham[index].innerText = 'Hết hàng';
			// listTrangThaiSanPham[index].style.color = '#dc3545';
			// listNutCong[index].style.cursor = 'not-allowed';
		}
		if (soLuong < 99) {
			listHienThiSoLuong[index].innerText = soLuong.toString();
		}
		else {
			listHienThiSoLuong[index].innerText = '99+';
		}
		thanhTien = thanhTien + (soLuong - soLuongPre) * parseInt(listGiaSanPham[index].innerText);
		document.querySelector('#thanh-tien').innerText = thanhTien.toString();
		listSoLuongSanPham[index].innerText = soLuong.toString();
		listHienThiSoLuong[index].title = soLuong.toString();
		themCookie(listIdSanPham[index].innerText, soLuong, 3600000 * 24, 60, '/fruitshop/gio-hang');
	}
});

listNutLoaiBoSanPham.forEach((item, index) => {
	item.onclick = () => {
		var soLuong = parseInt(listSoLuongSanPham[index].innerText);
		thanhTien = thanhTien - soLuong * parseInt(listGiaSanPham[index].innerText);
		document.querySelector('#thanh-tien').innerText = thanhTien.toString();
		
		listDongGioHang[index].classList.add('hieu-ung-dong-gio-hang');
		setTimeout(() => {
			for (var i = index + 1; i < listDongGioHang.length; i++) {
				listDongGioHang[i].classList.add('hieu-ung-danh-sach-gio-hang');
				listDongGioHang[i].style.borderTop = '1px solid #ebebeb';
			}
			phanTrangVaDonHang.classList.add('hieu-ung-danh-sach-gio-hang');
			setTimeout(() => {
				listDongGioHang[index].style.display = 'none';
				for (var i = index + 1; i < listDongGioHang.length; i++) {
					listDongGioHang[i].classList.remove('hieu-ung-danh-sach-gio-hang');
					listDongGioHang[i].style.borderTop = '';
				}
				phanTrangVaDonHang.classList.remove('hieu-ung-danh-sach-gio-hang');
			}, 800);
		}, 800);
		
		themCookie(listIdSanPham[index].innerText, 0, 3600000 * 24, 60, '/fruitshop/gio-hang');
		vongTronSoLuong.innerText = (parseInt(vongTronSoLuong.innerText) - 1).toString();
	}
});