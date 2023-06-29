var listChonIcon = document.querySelectorAll('.chon-icon');
var listCountClick = [0];
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
	if (e.target != listChonIcon[0]) {
		listBangGiaTri[0].style.display = '';
		listCountClick[0] = 0;
		return;
	}
}

listGiaTri.forEach(item => {
	item.onclick = () => {
		var value = parseInt(item.innerText);
		if (value >= 2018 && value <= 2023) {
			listGiaTriDuocChon[0].innerText = value;
		}
		var nam = parseInt(listGiaTriDuocChon[0].innerText);
		if (isNaN(nam) == false) {
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
function veBieuDoTron(data, nam) {
	var canvas = document.querySelector('canvas');
	if (bieuDoTron != null) {
		bieuDoTron.destroy();
	}
	var labels = []
	for (var i = 1; i <= data.length; i++) {
		labels.push('Tháng ' + i);
	}
	var color = generateRandomColors(1);
	
	var config = {
		type: 'line',
		data: {
			labels: labels, // các nhãn trên trục hoành
			datasets: // Tập hợp các đường
			[
				{
					label: 'Doanh thu', // Tên của đường
					data: data, // dữ liệu của đường tương ứng với nhãn trên trục hoành
					backgroundColor: color, // màu của đường
					borderColor: color,
					fill: false
				}
			]
		},
		options: {
            responsive: true,
            scales: {
                x: {
                    display: true,
                    title: {
                        display: true,
                        text: 'Tháng trong năm'
                    }
                },
                y: {
                    display: true,
                    title: {
                        display: true,
                        text: 'Doanh thu (VNĐ)'
                    }
                }
            }
        }
	}
	
	bieuDoTron = new Chart(canvas.getContext('2d'), config);
}

nutTaoBieuDo.onclick = () => {
	var nam = parseInt(listGiaTriDuocChon[0].innerText);
	if (isNaN(nam) == false) {		
		var xhr = new XMLHttpRequest();		
		xhr.onreadystatechange = () => {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var data = JSON.parse(xhr.responseText);
				veBieuDoTron(data, nam);
			}
		}
		xhr.open('GET', `../api/admin/doanh-thu-theo-thang?nam=${nam}`, true);
		xhr.send();
	}
}