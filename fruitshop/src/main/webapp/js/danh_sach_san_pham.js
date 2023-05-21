document.querySelectorAll('.thanh-phan-phan-trang').forEach(item => {
	item.onclick = () => {
		item.firstElementChild.click();
	}
});