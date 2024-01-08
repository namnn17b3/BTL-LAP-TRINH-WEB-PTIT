var url = window.location.href;
var csrfToken = url.substring(url.lastIndexOf('csrf-token=') + 11, url.length);
var listChonIcon = document.querySelectorAll('.chon-icon');
var listCountClick = [0];
var listBangGiaTri = document.querySelectorAll('.bang-gia-tri');
var body = document.querySelector('body');
var listGiaTriDuocChon = document.querySelectorAll('.gia-tri-duoc-chon');
var listGiaTri = document.querySelectorAll('.gia-tri');
var bangKetQua = document.querySelector('.bang-ket-qua');
var tBody = document.querySelector('tbody');
var tieuDeTieuChi = document.querySelector('.tieu-de-tieu-chi');

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

function taoBangDuLieu(choose) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = JSON.parse(xhr.responseText);
			while (tBody.firstElementChild) {
				tBody.removeChild(tBody.firstElementChild);
			}
			var html = '';
			for (var i = 0; i < data.length; i++) {
				var dongData = 
				`
				<tr class="dong-du-lieu" ${i % 2 == 1 ? 'style="background-color: #f0f0f0;"' : ''}>
					<td>
						<div class="chi-tiet-du-lieu">
							<span style="color: #3d464d; font-weight: 600">${i + 1}</span>
						</div>
					</td>
					<td>
						<div class="chi-tiet-du-lieu">
							<span>${data[i].id}</span>
						</div>
					</td>
					<td>
						<div class="chi-tiet-du-lieu">				
							<span><img src="../${data[i].anh}" style="width: 80px; height: 80px;"/></span>
						</div>
					</td>
					<td>
						<div class="chi-tiet-du-lieu">
							<span>
								<a href="../chi-tiet-san-pham?id=${data[i].id}&csrf-token=${csrfToken}" target="_blank">${data[i].ten}</a>
							</span>
						</div>
					</td>
					<td>
						<div class="chi-tiet-du-lieu">				
							<span style="margin: auto; font-weight: 600"><span style="color: #ff2626;" class="tien-format">${data[i].tienTrenDonVi}</span>/${data[i].donVi}</span>
						</div>
					</td>
					<td>
						<div class="chi-tiet-du-lieu">
							<span ${choose == 0 ? 'style="color: #ff2626; font-weight: 600;" class="tien-format"' : ''}>${(choose == 0) ? (data[i].tienTrenDonVi * data[i].soLuongBan) : (data[i].soLuongBan)}</span>
						</div>
					</td>
				</tr>
				`
				html += dongData;
			}
			tBody.insertAdjacentHTML('afterbegin', html);
			if (choose == 0) {
				tieuDeTieuChi.innerText = 'Doanh Thu';
			}
			else {
				tieuDeTieuChi.innerText = 'Số lượng đã bán';
			}
			document.querySelectorAll('.tien-format').forEach(item => {
				var gia = parseInt(item.innerText).toLocaleString();
				item.innerHTML = gia.replaceAll(',', '.') + (choose == 0 ? ' <span style="color: #3d464d;">VNĐ</span>' : '');
			});
		}
	}
	xhr.open('GET', `../../api/admin/top-10-san-pham?choose=${choose}`, true);
	xhr.send();
}

listGiaTri.forEach((item, index) => {
	item.onclick = () => {
		listGiaTriDuocChon[0].innerText = item.innerText;
		bangKetQua.style.display = 'table';
		taoBangDuLieu(index);
	}
});