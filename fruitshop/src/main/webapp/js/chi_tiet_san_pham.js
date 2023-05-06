var thanhGachChan = document.querySelector('.thanh-gach-chan-item');
var itemMoTa = document.querySelector('.item-mo-ta');
var itemDanhGia = document.querySelector('.item-danh-gia');
var moTaChiTietSanPham = document.querySelector('.mo-ta-chi-tiet-san-pham');

itemMoTa.onclick = () => {
	thanhGachChan.style.animation = 'sangTrai 0.2s ease forwards';
//	moTaChiTietSanPham.style.animation = '';
//	setTimeout(() => {
//		moTaChiTietSanPham.style.display = 'block';
//	}, 200);
}

itemDanhGia.onclick = () => {
	thanhGachChan.style.animation = 'sangPhai 0.2s ease forwards';
//	moTaChiTietSanPham.style.animation = 'moDan 0.2s ease forwards';
//	setTimeout(() => {
//		moTaChiTietSanPham.style.display = 'none';
//	}, 200);
}