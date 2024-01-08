//var csrfToken = document.querySelector('#csrf-token').innerText;
var listAdminChucNang = document.querySelectorAll('.chuc-nang');
const functionStrings = [
	'main',
	'doanh-thu-theo-ngay',
	'doanh-thu-theo-thang',
	'quan-ly-don-hang',
	'quan-ly-san-pham',
	'quan-ly-tai-khoan',
	'top-10-san-pham',
	'top-5-khach-hang'
];
var link = [
	'./admin/if/main',
	'./admin/if/doanh-thu-theo-ngay',
	'./admin/if/doanh-thu-theo-thang',
	'./admin/if/quan-ly-don-hang',
	'./admin/if/quan-ly-san-pham',
	'./admin/if/quan-ly-tai-khoan',
	'./admin/if/top-10-san-pham',
	'./admin/if/top-5-khach-hang'
];
var listFunctionCallback = [1, 2, 3, 4, 5, 6, 7, 8];

var iframe = document.querySelector('iframe');
var phanTuHienTai = null;
var indexHienTai = null;
var chucNang = parseInt(document.querySelector('#chuc-nang').innerText);
var homeAtag = document.querySelector('.quay-lai-home').lastElementChild;

if (isNaN(chucNang) == false && chucNang >= 0 && chucNang < link.length) {
	phanTuHienTai = listAdminChucNang[chucNang];
	phanTuHienTai.style.backgroundColor = '#8ab4f8';
	phanTuHienTai.style.color = '#fff';
}
else {
	chucNang = 0;
}

function changeStateURL(index) {
	var ok = false;
	for (var i = 0; i < functionStrings.length; i++) {
		if (window.location.href.lastIndexOf(functionStrings[i]) != -1) {
			ok = true;
		}
	}
	if (ok == true) {
		window.history.pushState(null, null, `${functionStrings[index]}`);
	}
	else {		
		window.history.pushState(null, null, `admin/${functionStrings[index]}`);
	}
	iframe.src = `../${link[index]}`;
	homeAtag.href = `../home`;
}

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
			changeStateURL(index);
		}
        phanTuHienTai = item;
        chucNang = index;	
        switch (index) {
			case 3:
				break;
			case 4:
				break;
			case 5:				
		        listFunctionCallback[index] = quanLyTaiKhoan;
				break;
		}	
		iframe.onload = () => {
			if (typeof(listFunctionCallback[index]) == 'function') {			
				listFunctionCallback[index]();
			}
		}
	}
});

document.querySelector('.quay-lai-home').onclick = () => {
	setTimeout(() => {
		homeAtag.click();
	}, 200);
}

for (var i = 0; i < functionStrings.length; i++) {
	if (window.location.href.lastIndexOf(functionStrings[i]) != -1) {
		listAdminChucNang[i].click();
		listAdminChucNang[i].style.backgroundColor = '#8ab4f8';
		listAdminChucNang[i].style.color = '#fff';
		break;
	}
}

function previewImage(wapper, fileInput, img) {
	wapper.style.display = 'flex';
	fileInput.onchange = () => {
		var fileSelected = fileInput.files;
	    if (fileSelected.length > 0) {
	        var fileToLoad = fileSelected[0];
	        var fileReader = new FileReader();
	        fileReader.onload = (fileLoaderEvent) => {
	            var srcData = fileLoaderEvent.target.result;
	            img.src = srcData;
	        }
	        fileReader.readAsDataURL(fileToLoad);
	    }
	    else {
			closePreviewImage(inputImage, userAvatar);
		}
	}
}

function closePreviewImage(wapper, fileInput) {
	wapper.style.display = 'none';
	fileInput.value = '';
	userCloseImage.value = '1';
}

var wapperForm = document.querySelector('.wapper-form');
var formUser = document.querySelector('.form-user');
var inputImage = document.querySelector('.input-img');
var userAvatar = document.querySelector('#user-avatar');
var closeImage = document.querySelector('.close-img');
var imageUser = document.querySelector('.input-img div img');
var btnUserCancel = document.querySelector('.user-btn-cancel');
var btnUserOK = document.querySelector('.user-btn-ok');
var titleFormUser = document.querySelector('.form-user h1');

var userEmail = document.querySelector('#user-email');
var userTen = document.querySelector('#user-ten');
var userMatKhau = document.querySelector('#user-mat-khau');
var userId = document.querySelector('#user-id');
var userCloseImage = document.querySelector('#user-close-image');

closeImage.onclick = () => {
	closePreviewImage(inputImage, userAvatar);
}

userAvatar.oninput = () => {
	previewImage(inputImage, userAvatar, imageUser);
}

btnUserCancel.onclick = () => {
	wapperForm.style.display = 'none';
	formUser.style.display = 'none';
	
	userEmail.value = '';
	userTen.value = '';
	userMatKhau.value = '';
	userAvatar.value = '';
	userId.value = '';
	userCloseImage.value = '0';
	
	closePreviewImage(inputImage, userAvatar);
}

btnUserOK.onclick = () => {
	const email = userEmail.value;
	const ten = userTen.value;
	const matKhau = userMatKhau.value;
	const id = userId.value;
	const discardImage = userCloseImage.value;
	
	var data = new FormData();
	data.append("email", email);
	data.append("ten", ten);
	data.append("matKhau", matKhau);
	data.append("discardImage", discardImage);
	data.append("avatar", userAvatar.files[0]);

	var method = 'POST';
	if (titleFormUser.innerText == 'Chỉnh sửa tài khoản') {
		method = 'PUT';
		data.append("id", id);
	}
	
	const apiURL = new URL('./../api/admin/quan-ly-tai-khoan', window.location.href).href;
	const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
		if (this.readyState == 4) {
			const dataResponse = JSON.parse(this.responseText);
			alert(dataResponse['message']);
			if (this.status == 200) {	
				wapperForm.style.display = 'none';
				formUser.style.display = 'none';
				
				userEmail.value = '';
				userTen.value = '';
				userMatKhau.value = '';
				userAvatar.value = '';
				userId.value = '';
				userCloseImage.value = '0';
				if (method == 'PUT') {
					updateRowTableUser(id, iframe, dataResponse['newUser']);
				}
			}
		}
	});

    xhr.open(method, apiURL, true);
    xhr.send(data);
};