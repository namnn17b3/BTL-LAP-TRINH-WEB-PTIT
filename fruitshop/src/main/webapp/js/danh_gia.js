var inputDanhGia = document.querySelector('.input-danh-gia');

setTimeout(() => {	
	inputDanhGia.value = inputDanhGia.value.replaceAll('\\n', '\n');
	inputDanhGia.style.height = `${inputDanhGia.scrollHeight}px`;
}, 1200);

inputDanhGia.onkeyup = (e) => {
	inputDanhGia.style.height = '60px';
	inputDanhGia.style.height = `${e.target.scrollHeight}px`;
}

var inputSoSaoVote = document.querySelector('#so-sao-vote');
document.querySelectorAll('.sao-vote').forEach((item, index) => {
	item.addEventListener('click', () => {
		item.style.backgroundColor = '#f0f8ff';
		item.style.border = '1px solid #1a94ff';
		item.style.color = '#0b74e5';
		inputSoSaoVote.value = index.toString();
		if (phanTuTruoc != null && phanTuTruoc != item) {
			phanTuTruoc.style.backgroundColor = '#f5f5fa';
			phanTuTruoc.style.border = 'none';
			phanTuTruoc.style.color = '#3d464d';
		}
		phanTuTruoc = item;
	});
});

var inputThaoTac = document.querySelector('#thao-tac');
try {
	document.querySelector('button.not-button-xoa').onclick = (e) => {
		inputThaoTac.value = "ok";
		if (inputSoSaoVote.value == '-1') {
			e.preventDefault();
			document.querySelector('.thong-bao-chua-vote-sao').style.display = 'block';
		}
	}
}
catch (e) {}

try {
	document.querySelector('button.xoa-danh-gia').onclick = () => {
		inputThaoTac.value = "xoa";
	}
}
catch (e) {}