document.querySelectorAll('.dong-gio-hang').forEach((item, index) => {
	if (index % 2 == 1) {
		item.style.backgroundColor = 'rgba(240, 240, 240, 0.38)';
	}
});

document.querySelectorAll('.thanh-phan-phan-trang').forEach(item => {
	item.onclick = () => {
		themCookie('clickGioHang', 1, 3600000 * 24, 60, '/fruitshop/gio-hang');
		item.firstElementChild.click();
	}
});

document.querySelector('.cap-nhat-gio-hang__gio-hang').onclick = () => {
	themCookie('clickCapNhatGioHang', 1, 3600000 * 24, 60, '/fruitshop/gio-hang');
	themCookie('flag', 0, 3600000 * 24, 60, '/fruitshop/gio-hang');
};

document.querySelector('.thanh-toan__gio-hang').onclick = () => {
	themCookie('clickTienHanhThanhToan', 1, 3600000 * 24, 60, '/fruitshop/thanh-toan');
}

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
var listGiaSanPham = document.querySelectorAll('span.gia-san-pham');
var listTongTien = document.querySelectorAll('.tong-tien-san-pham span span');
var thanhTien = parseInt(document.querySelector('#thanh-tien').innerText.replaceAll('.', ''));
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

//listNutCong.forEach((item, index) => {
//	item.onmousemove = () => {
//		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
//		var soLuong = soLuongPre + 1;
//		var soLuongSanPhamConLai = parseInt(listSoLuongSanPhamConLai[index].innerText);
//		if (soLuong > soLuongSanPhamConLai) {
//			listNutCong[index].style.cursor = 'not-allowed';
//		}
//		else {
//			listNutCong[index].style.cursor = 'pointer';
//		}
//	}
//});

listNutTru.forEach((item, index) => {
	item.onclick = () => {
		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
		var soLuong = soLuongPre - 1;
		var soLuongSanPhamConLai = parseInt(listSoLuongSanPhamConLai[index].innerText);
		if (soLuong > 0) {
			listSoLuongSanPham[index].innerText = soLuong.toString();
			listNutTru[index].style.cursor = 'pointer';
			if (soLuong <= soLuongSanPhamConLai) {
				listTrangThaiSanPham[index].innerText = 'Còn hàng';
				listTrangThaiSanPham[index].style.color = '#28a745';
				// listNutCong[index].style.cursor = 'pointer';
			}
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
			listNutTru[index].style.cursor = 'not-allowed';
		}
		listTongTien[index].innerText = (soLuong * parseInt(listGiaSanPham[index].innerText.replaceAll('.', ''))).toLocaleString().replaceAll(',', '.');
		thanhTien = thanhTien + (soLuong - soLuongPre) * parseInt(listGiaSanPham[index].innerText.replaceAll('.', ''));
		document.querySelector('#thanh-tien').innerText = thanhTien.toLocaleString().replaceAll(',', '.');
		listHienThiSoLuong[index].title = soLuong.toString();
		themCookie(listIdSanPham[index].innerText, soLuong, 3600000 * 24, 60, '/fruitshop/gio-hang');
	}
});

listNutCong.forEach((item, index) => {
	item.onclick = () => {
		var soLuongPre = parseInt(listSoLuongSanPham[index].innerText);
		var soLuong = soLuongPre + 1;
		var soLuongSanPhamConLai = parseInt(listSoLuongSanPhamConLai[index].innerText);
		if (soLuong > 0) {
			listNutTru[index].style.cursor = 'pointer';
		}
		if (soLuong > soLuongSanPhamConLai) {
			// soLuong = soLuongSanPhamConLai;
			if (soLuongSanPhamConLai == 0) {
				listTrangThaiSanPham[index].innerText = 'Hết hàng';
			}
			else {
				listTrangThaiSanPham[index].innerText = 'Vượt quá số lượng trong kho';
			}
			listTrangThaiSanPham[index].style.color = '#dc3545';
			// listNutCong[index].style.cursor = 'not-allowed';
		}
		if (soLuong < 99) {
			listHienThiSoLuong[index].innerText = soLuong.toString();
		}
		else {
			listHienThiSoLuong[index].innerText = '99+';
		}
		listTongTien[index].innerText = (soLuong * parseInt(listGiaSanPham[index].innerText.replaceAll('.', ''))).toLocaleString().replaceAll(',', '.');
		thanhTien = thanhTien + (soLuong - soLuongPre) * parseInt(listGiaSanPham[index].innerText.replaceAll('.', ''));
		document.querySelector('#thanh-tien').innerText = thanhTien.toLocaleString().replaceAll(',', '.');
		listSoLuongSanPham[index].innerText = soLuong.toString();
		listHienThiSoLuong[index].title = soLuong.toString();
		themCookie(listIdSanPham[index].innerText, soLuong, 3600000 * 24, 60, '/fruitshop/gio-hang');
	}
});

listNutLoaiBoSanPham.forEach((item, index) => {
	item.onclick = () => {
		var soLuong = parseInt(listSoLuongSanPham[index].innerText);
		thanhTien = thanhTien - soLuong * parseInt(listGiaSanPham[index].innerText.replaceAll('.', ''));
		document.querySelector('#thanh-tien').innerText = thanhTien.toLocaleString().replaceAll(',', '.');
		
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