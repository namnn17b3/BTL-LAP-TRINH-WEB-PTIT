var nutChuyenSlide = document.querySelectorAll('.nut-chuyen-slide');
var chiMucHienTai = 0;
nutChuyenSlide[chiMucHienTai].style.backgroundColor = '#ffd333';
var bangThanhPhanSlideShow = document.querySelector('.bang-thanh-phan-slide-show');

function taoElement(html) {
    var elementHienTai = document.createElement('div');
    elementHienTai.className = 'thanh-phan-slide-show';
    elementHienTai.innerHTML = html;
    return elementHienTai;
}

var thanhPhanSlideShow = [
    `
    <div class="anh-va-ten-doi-tac">
        <img src="./img/hiep_hoi_cherry_my.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Hiệp hội cherry Mỹ</h3></div>
    </div class="anh-va-ten-doi-tac">
    <div class="anh-va-ten-doi-tac">
        <img src="./img/sunview.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Sun View - Mỹ</h3></div>
    </div>
    <div class="anh-va-ten-doi-tac">
        <img src="./img/super_fresh.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Super Fresh - Mỹ</h3></div>
    </div>
    `,
    `
    <div class="anh-va-ten-doi-tac">
        <img src="./img/nonghyup.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Nonghyup - Hàn Quốc</h3></div>
    </div class="anh-va-ten-doi-tac">
    <div class="anh-va-ten-doi-tac">
        <img src="./img/ever_good.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Everygood - Hàn Quốc</h3></div>
    </div>
    <div class="anh-va-ten-doi-tac">
        <img src="./img/Fruitico.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Fruitico - Úc</h3></div>
    </div>
    `,
    `
    <div class="anh-va-ten-doi-tac">
        <img src="./img/enza.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Nonghyup - Hàn Quốc</h3></div>
    </div class="anh-va-ten-doi-tac">
    <div class="anh-va-ten-doi-tac">
        <img src="./img/koru.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Everygood - Hàn Quốc</h3></div>
    </div>
    <div class="anh-va-ten-doi-tac">
        <img src="./img/Costa_Rica.png" alt="" class="anh-dai-dien-doi-tac">
        <div class="ten-doi-tac"><h3>Fruitico - Úc</h3></div>
    </div>
    `
].map((item) => {
    return taoElement(item);
});

setInterval(() => {
    nutChuyenSlide[chiMucHienTai].style.backgroundColor = '#e0e0e0';
    chiMucHienTai++;
    chiMucHienTai %= 3;
    bangThanhPhanSlideShow.insertAdjacentElement('beforeend', thanhPhanSlideShow[chiMucHienTai]);
    document.querySelectorAll('.thanh-phan-slide-show').forEach((item) => {
        item.style.animation = 'truotTuPhaiQua ease-in 1s forwards';
    });
    nutChuyenSlide[chiMucHienTai].style.backgroundColor = '#ffd333';
    setTimeout(() => {
        bangThanhPhanSlideShow.innerHTML = '';
        thanhPhanSlideShow[chiMucHienTai].style.animation = '';
        bangThanhPhanSlideShow.appendChild(thanhPhanSlideShow[chiMucHienTai]);
    }, 1000);
}, 2000);