function quanLyTaiKhoan() {
	const queryTextInput = iframe.contentDocument.querySelector('#query-text');
	const bangKetQua = iframe.contentDocument.querySelector('.bang-ket-qua');
	const tBody = iframe.contentDocument.querySelector('tbody');
	const thongTinThongKe = iframe.contentDocument.querySelector('.thong-tin-thong-ke');
	const tongSoTrang = iframe.contentDocument.querySelector('.thong-tin-thong-ke .tong-so-trang span');
	const tongSoBanGhi = iframe.contentDocument.querySelector('.thong-tin-thong-ke .tong-so-ban-ghi span');

	let page = 1;
	const itemInPage = 10;
	const pageInWebview = 5;
	const wapperPhanTrang = iframe.contentDocument.querySelector('.wapper-phan-trang');

	function taoBangDuLieu(data) {
		while (tBody.firstElementChild) {
			tBody.removeChild(tBody.firstElementChild);
		}
		const listUser = data.listUser;
		var html = '';
		for (var i = 0; i < listUser.length; i++) {
			var dongData =
				`
			<tr id="user-${listUser[i].id}" class="dong-du-lieu" ${i % 2 == 1 ? 'style="background-color: #f0f0f0;"' : ''}>
				<td>
					<div class="chi-tiet-du-lieu">
						<span style="color: #3d464d; font-weight: 600">${i + 1}</span>
					</div>
				</td>
				<td>
					<div class="chi-tiet-du-lieu">
						<span>${listUser[i].id}</span>
					</div>
				</td>
				<td>
					<div class="chi-tiet-du-lieu">				
						<span><img src="../${listUser[i].anh}" style="width: 80px; height: 80px; border-radius: 50%; border: 3px solid orange;"/></span>
					</div>
				</td>
				<td>
					<div class="chi-tiet-du-lieu">
						<span style="font-weight: 600;" class="td-div-username">${listUser[i].ten}</span>
					</div>
				</td>
				<td>
					<div class="chi-tiet-du-lieu">				
						<span>${listUser[i].email}</span>
					</div>
				</td>
				<td>
					<div class="chi-tiet-du-lieu">
						<div class="table-edit"><i class="fa-solid fa-pen-to-square"></i> <span>Edit</span></div>
						<div class="table-delete"><i class="fa-solid fa-trash"></i> <span>Delete</span></div>
					</div>
				</td>
			</tr>
			`
			html += dongData;
		}
		tBody.insertAdjacentHTML('afterbegin', html);
		tongSoTrang.innerText = data.soLuongPage;
		tongSoBanGhi.innerText = data.soLuongBanGhi;

		iframe.contentDocument.querySelectorAll('.td-div-username').forEach((item, index) => {
			item.innerText = listUser[index].ten;
		});

		iframe.contentDocument.querySelectorAll('.table-edit').forEach((item, index) => {
			item.onclick = () => { tableUserEdit(listUser[index]) };
		});

		iframe.contentDocument.querySelectorAll('.table-delete').forEach((item, index) => {
			item.onclick = () => { tableUserDelete(listUser[index].id) };
		});
	}

	function taoPhanTrang(data) {
		wapperPhanTrang.style.display = 'flex';
		let startPageWebview = data.startPageWebview;
		let endPageWebview = data.endPageWebview;
		let htmlPhanTrang = '<div class="phan-trang">';

		if (page - 1 > 0) {
			htmlPhanTrang = htmlPhanTrang + `
			<div class="nut-phan-trang nut-prev"><i class="fa-solid fa-chevron-left" style="margin: auto"></i><span style="display: none;">${page - 1}</span></div>
			`;
		}

		for (let i = startPageWebview; i <= endPageWebview; i++) {
			if (i != page) {
				htmlPhanTrang = htmlPhanTrang + `
				<div class="nut-phan-trang none-active"><span>${i}</span></div>
				`;
			}
			else {
				htmlPhanTrang = htmlPhanTrang + `
				<div class="nut-phan-trang active"><span>${page}</span></div>
				`;
			}
		}

		if (page + 1 <= data.soLuongPage) {
			htmlPhanTrang = htmlPhanTrang + `
			<div class="nut-phan-trang nut-next"><i class="fa-solid fa-chevron-right" style="margin: auto"></i><span style="display: none;">${page + 1}</span></div>
			`;
		}

		htmlPhanTrang = htmlPhanTrang + '</div>';
		wapperPhanTrang.innerHTML = htmlPhanTrang;

		iframe.contentDocument.querySelectorAll('.nut-phan-trang').forEach(item => {
			item.onclick = () => {
				page = parseInt(item.lastElementChild.lastChild.data);
				timKiem(page);
			}
		});
	}

	function tableUserDelete(id) {
		Swal.fire({
			title: "Bạn có chắc chắn muốn xóa?",
			text: "Bạn sẽ không thể khôi phục lại được sau khi xóa!",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "Delete"
		}).then((result) => {
			if (result.isConfirmed) {
				const apiURL = new URL(`./../api/admin/quan-ly-tai-khoan?id=${id}`, window.location.href).href;
				callAPI(apiURL, 'DELETE', data = null, function() {
					if (this.readyState == 4) {
						const dataResponse = JSON.parse(this.responseText);
						if (this.status == 200) {
							Swal.fire({
								title: "Đã xóa thành công!",
								text: dataResponse['message'],
								icon: "success"
							});
							timKiem(page);
						}
						else {
							Swal.fire({
								title: "Đã có lỗi xảy ra!",
								text: dataResponse['message'],
								icon: "error"
							});
						}
					}
				});
			}
		});
	}

	function timKiem(currentPage) {
		let queryText = queryTextInput.value;
		if (!queryText) {
			queryText = "";
		}
		const apiURL = new URL('./../api/admin/quan-ly-tai-khoan', window.location.href).href;
		callAPI(`${apiURL}?itemInPage=${itemInPage}&pageInWebview=${pageInWebview}&page=${currentPage}&queryText=${encodeURIComponent(queryText)}`, 'GET', '', function() {
			if (this.readyState == 4) {
				if (this.status == 200) {
					const data = JSON.parse(this.responseText);
					if (data.listUser && data.listUser.length == 0) {
						alert('Không tìm thấy user');
						return;
					}
					bangKetQua.style.display = 'table';
					thongTinThongKe.style.display = 'flex';
					taoBangDuLieu(data);
					if (data.soLuongPage > 1) {
						taoPhanTrang(data);
					}
					else {
						wapperPhanTrang.style.display = 'none';
					}
				}
				else {
					console.log(this.responseText);
				}
			}
		});
	}

	iframe.contentDocument.querySelector('.nut-tim-kiem-user').onclick = () => { timKiem(1) };
	iframe.contentDocument.querySelector('.nut-them-user').onclick = () => {
		titleFormUser.innerText = 'Thêm tài khoản';
		wapperForm.style.display = 'block';
		formUser.style.display = 'flex';
		
		userEmail.value = '';
		userTen.value = '';
		userMatKhau.value = '';
		userAvatar.value = '';
		userId.value = '';
		userCloseImage.value = '0';
		closePreviewImage(inputImage, userAvatar);
	};
}

function tableUserEdit(user) {
	titleFormUser.innerText = 'Chỉnh sửa tài khoản';
	wapperForm.style.display = 'block';
	formUser.style.display = 'flex';

	userEmail.value = user.email;
	userTen.value = user.ten;
	userMatKhau.value = '';
	userId.value = user.id;
	userCloseImage.value = '0';
	imageUser.src = `${user.anh}`;

	if (imageUser.src.includes('fb-no-img.png') == false) {
		previewImage(inputImage, userAvatar, imageUser);
	}
}

function updateRowTableUser(id, iframe, newUser) {
	const rowTableUser = iframe.contentDocument.querySelector(`#user-${id}`);
	rowTableUser.innerHTML = `
	<td>
		<div class="chi-tiet-du-lieu">
			<span style="color: #3d464d; font-weight: 600">${i + 1}</span>
		</div>
	</td>
	<td>
		<div class="chi-tiet-du-lieu">
			<span>${newUser.id}</span>
		</div>
	</td>
	<td>
		<div class="chi-tiet-du-lieu">				
			<span><img src="../${newUser.anh}" style="width: 80px; height: 80px; border-radius: 50%; border: 3px solid orange;"/></span>
		</div>
	</td>
	<td>
		<div class="chi-tiet-du-lieu">
			<span style="font-weight: 600;" class="td-div-username">${newUser.ten}</span>
		</div>
	</td>
	<td>
		<div class="chi-tiet-du-lieu">				
			<span>${newUser.email}</span>
		</div>
	</td>
	<td>
		<div class="chi-tiet-du-lieu">
			<div class="table-edit"><i class="fa-solid fa-pen-to-square"></i> <span>Edit</span></div>
			<div class="table-delete"><i class="fa-solid fa-trash"></i> <span>Delete</span></div>
		</div>
	</td>
	`;
	iframe.contentDocument.querySelector(`#user-${id} .table-edit`).onclick = () => {
		tableUserEdit(newUser);
	};
}