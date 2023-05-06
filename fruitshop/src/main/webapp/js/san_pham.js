[...document.querySelectorAll('.anh-san-pham')].concat([...document.querySelectorAll('.ten-san-pham')]).forEach(item => {
	item.onclick = () => {
		item.parentElement.firstElementChild.click();
	}
});