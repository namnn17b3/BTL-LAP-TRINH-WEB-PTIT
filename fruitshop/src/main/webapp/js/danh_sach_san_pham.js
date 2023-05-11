document.querySelectorAll('.thanh-phan-phan-trang-san-pham').forEach(item => {
	item.onclick = () => {
		item.firstElementChild.click();
	}
});