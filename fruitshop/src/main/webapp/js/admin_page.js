var csrfToken = document.querySelector('#csrf-token').innerText;
var phanTuHienTai = null;
var listAdminChucNang = document.querySelectorAll('.chuc-nang');
var link = ['./admin/main', './admin/doanh-thu-theo-ngay', './admin/doanh-thu-theo-thang', './admin/quan-ly-don-hang', './admin/quan-ly-san-pham', './admin/quan-ly-tai-khoan', './admin/top-10-san-pham', './admin/top-5-khach-hang'].map(item => {
	return `${item}?csrf-token=${encodeURIComponent(csrfToken)}`;
});
console.log(link);
var iframe = document.querySelector('iframe');

iframe.src = `./admin/main?csrf-token=${encodeURIComponent(csrfToken)}`;

listAdminChucNang.forEach((item, index) => {
    hieuUngClickChuot =
    `
        <div class="hieu-ung"></div>
    `
    item.onmousedown = () => {
        if (item !== phanTuHienTai) {
            item.style.backgroundColor = 'rgba(233, 233, 233, 0.5)';
            item.insertAdjacentHTML('afterbegin', hieuUngClickChuot);
            item.style.backgroundColor = '';
            setTimeout(() => {
                item.removeChild(item.firstElementChild);
            }, 200);
        }
    }

    item.onmouseup = () => {
        if (item !== phanTuHienTai) {
            item.style.backgroundColor = '#8ab4f8';
            item.style.color = '#fff';
            if (phanTuHienTai !== null) {
                phanTuHienTai.style.backgroundColor = '';
                phanTuHienTai.style.color = '#3d464d';
            }
            phanTuHienTai = item;
        }
    }
    
    item.onclick = () => {
		iframe.src = link[index];
	}
});

document.querySelector('.quay-lai-home').onclick = () => {
	setTimeout(() => {		
		document.querySelector('.quay-lai-home').lastElementChild.click();
	}, 200);
}