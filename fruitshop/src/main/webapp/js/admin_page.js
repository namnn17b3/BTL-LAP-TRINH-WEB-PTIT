var csrfToken = document.querySelector('#csrf-token').innerText;
var listAdminChucNang = document.querySelectorAll('.chuc-nang');
var link = ['./admin/main', './admin/doanh-thu-theo-ngay', './admin/doanh-thu-theo-thang', './admin/quan-ly-don-hang', './admin/quan-ly-san-pham', './admin/quan-ly-tai-khoan', './admin/top-10-san-pham', './admin/top-5-khach-hang'].map((item, index) => {
	return `${item}?csrf-token=${encodeURIComponent(csrfToken)}`;
});
var iframe = document.querySelector('iframe');
var phanTuHienTai = null;
var indexHienTai = null;
var chucNang = parseInt(document.querySelector('#chuc-nang').innerText);

if (isNaN(chucNang) == false && chucNang >= 0 && chucNang < link.length) {
	phanTuHienTai = listAdminChucNang[chucNang];
	phanTuHienTai.style.backgroundColor = '#8ab4f8';
	phanTuHienTai.style.color = '#fff';
}
else {
	chucNang = 0;
}

window.addEventListener('load', () => {
	var url = window.location.href;
	var index1 = url.indexOf('chuc-nang');
	var index2 = url.indexOf('csrf-token');
	if (index1 >= 0) {
		url = url.replace(url.substring(index1, index2), `chuc-nang=${chucNang}$`);
	}
	window.location.href = url;
});

iframe.src = link[chucNang];
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
        }
    }
    
    item.onclick = () => {
		if (item != phanTuHienTai) {
			iframe.src = link[index];
		}
        phanTuHienTai = item;
        chucNang = index;
	}
});

document.querySelector('.quay-lai-home').onclick = () => {
	setTimeout(() => {		
		document.querySelector('.quay-lai-home').lastElementChild.click();
	}, 200);
}