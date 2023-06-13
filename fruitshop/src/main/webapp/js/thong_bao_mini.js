if (flag == 0) {
	setTimeout(() => {
		var styleElement = document.createElement('style');
		styleElement.type = 'text/css';
		var thongBaoMini = document.querySelector('.thong-bao-mini');
		var nutHuyThongBaoMini = document.querySelector('.nut-huy-thong-bao-mini');
		thongBaoMini.style.display = 'flex';
		var keyframes = `
		@keyframes truotTuPhaiSang-thongBaoMini {
			from {
				transform: translateX(calc(${thongBaoMini.offsetWidth}px + 60px));
			}
			to {
				transform: translateX(0);
			}
		}
		
		@keyframes truotTuTraiSang-thongBaoMini {
			from {
				transform: translateX(0);
			}
			to {
				transform: translateX(calc(${thongBaoMini.offsetWidth}px + 60px));
			}
		}
		`
		styleElement.innerHTML = keyframes;
		var body = document.querySelector('body').insertAdjacentElement('afterbegin', styleElement);
		thongBaoMini.style.animation = 'truotTuPhaiSang-thongBaoMini 0.8s ease forwards';
		
		nutHuyThongBaoMini.onclick = () => {
			themCookie('flag', 1, 3600000 * 24, 60, '/fruitshop' + url.slice(url.lastIndexOf('/')));
			thongBaoMini.style.animation = 'truotTuTraiSang-thongBaoMini 0.8s ease forwards';
			setTimeout(() => {
				thongBaoMini.style.display = 'none';
			}, 800);
		}
		
		setTimeout(() => {
			themCookie('flag', 1, 3600000 * 24, 60, '/fruitshop' + url.slice(url.lastIndexOf('/')));
			thongBaoMini.style.animation = 'truotTuTraiSang-thongBaoMini 0.8s ease forwards';
			setTimeout(() => {
				thongBaoMini.style.display = 'none';
			}, 800);
		}, 5000);
	}, 1200);
}