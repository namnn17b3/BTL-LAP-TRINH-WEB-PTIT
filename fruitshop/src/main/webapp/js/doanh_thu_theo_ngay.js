var listChonIcon = document.querySelectorAll('.chon-icon');
var listCountClick = [0, 0];
var listBangGiaTri = document.querySelectorAll('.bang-gia-tri');
var body = document.querySelector('body');
var listGiaTriDuocChon = document.querySelectorAll('.gia-tri-duoc-chon');
var listGiaTri = document.querySelectorAll('.gia-tri');
var nutTaoBieuDo = document.querySelector('.nut-tao-bieu-do');

listChonIcon.forEach((item, index) => {
	item.onclick = () => {
		if (listCountClick[index] == 0) {
			listBangGiaTri[index].style.display = 'block';
			listCountClick[index] = 1;
		}
		else {
			listBangGiaTri[index].style.display = '';
			listCountClick[index] = 0;
		}
	}
});

body.onclick = (e) => {
	if (e.target != listChonIcon[0] && e.target != listChonIcon[1]) {
		listBangGiaTri[0].style.display = '';
		listBangGiaTri[1].style.display = '';
		listCountClick[0] = 0;
		listCountClick[1] = 0;
		return;
	}
	if (e.target != listChonIcon[0]) {
		listBangGiaTri[0].style.display = '';
		listCountClick[0] = 0;
		return;
	}
	if (e.target != listChonIcon[1]) {
		listBangGiaTri[1].style.display = '';
		listCountClick[1] = 0;
		return;
	}
}

listGiaTri.forEach(item => {
	item.onclick = () => {
		var value = parseInt(item.innerText);
		if (value >= 2018 && value <= 2023) {
			listGiaTriDuocChon[0].innerText = value;
		}
		if (value >= 1 && value <= 12) {
			listGiaTriDuocChon[1].innerText = value;
		}
		var nam = parseInt(listGiaTriDuocChon[0].innerText);
		var thang = parseInt(listGiaTriDuocChon[1].innerText);
		if (isNaN(nam) == false && isNaN(thang) == false) {
			var styleElement = document.createElement('style');
			styleElement.type = 'text/css';
			styleElement.innerHTML = `
				.nut-tao-bieu-do {
					cursor: pointer;
					opacity: 1;
				}
				.nut-tao-bieu-do:hover {
					background-color: #3d464d;
					color: #fff;
				}
			`;
			body.insertAdjacentElement('afterbegin', styleElement);
		}
	}
});

function generateRandomColors(length) {
    var colors = new Set();
    while (colors.size < length) {
        var color = '#' + Math.floor(Math.random() * 16777215).toString(16);
        if (color != '#ffffff') {
			colors.add(color);
		}
    }
    return Array.from(colors);
}

var bieuDoTron = null;
function veBieuDoTron(data, thang, nam) {
	var canvas = document.querySelector('canvas');
	if (bieuDoTron != null) {
		bieuDoTron.destroy();
	}
	var labels = []
	for (var i = 1; i <= data.length; i++) {
		labels.push('Ngày ' + i);
	}
	
	var config = {
		type: 'pie',
		data: {
			labels: labels,
			datasets:
			[
				{
					label: 'Doanh thu',
					data: data,
					backgroundColor: generateRandomColors(data.length)
				}
			]
		},
		options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: `Doanh thu theo ngày trong tháng ${thang} năm ${nam} (VNĐ)`
                }
            }
        }
	}
	
	bieuDoTron = new Chart(canvas.getContext('2d'), config);
}

nutTaoBieuDo.onclick = () => {
	var nam = parseInt(listGiaTriDuocChon[0].innerText);
	var thang = parseInt(listGiaTriDuocChon[1].innerText);
	if (isNaN(nam) == false && isNaN(thang) == false) {		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = () => {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var data = JSON.parse(xhr.responseText);
				veBieuDoTron(data, thang, nam);
			}
		}
		xhr.open('GET', `../api/admin/doanh-thu-theo-ngay?nam=${nam}&thang=${thang}`, true);
		xhr.send();
	}
}