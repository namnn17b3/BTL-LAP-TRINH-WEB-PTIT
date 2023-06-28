var fruishopWebcam = document.querySelector('.webcam');
var video = document.querySelector('video');
var canvas = document.querySelector('canvas');
var webcam = new Webcam(video, "fruitshop", canvas);
var dowLoadAnh = document.querySelector('.dowload-photo');
var tatWebcam = document.querySelector('.tat-webcam');
var chupLaiAnh = document.querySelector('.chup-lai-anh');
var chupAnh = document.querySelector('.chup-anh');
var body = document.querySelector('body');
var eventX = 0;
var eventY = 0;
var currentEventX = 0;
var currentEventY = 0;
var fruishopWebcamLeft = 0;
var fruishopWebcamTop = 0;
var nhanChuot = false;

chupAnh.onclick = () => {
	webcam.start();
	fruishopWebcam.style.display = 'block';
	fruishopWebcam.style.animation = 'xuatHienTuTamRa ease 0.5s';
}

dowLoadAnh.onclick = () => {
	var anh = webcam.snap();
	dowLoadAnh.href = anh;
	canvas.style.display = 'block';
	video.style.display = 'none';
	webcam.stop();
}

chupLaiAnh.onclick = () => {
	canvas.style.display = 'none';
	video.style.display = 'block';
	webcam.start();
}

tatWebcam.onclick = () => {
	fruishopWebcam.style.animation = 'diTuTamVao ease 0.5s';
	setTimeout(() => {
		fruishopWebcam.style.display = 'none';
		fruishopWebcam.style.animation = '';
		fruishopWebcam.style.left = '50%';
		fruishopWebcam.style.top = '50%';
		fruishopWebcam.style.transform = 'translate(-50%, -50%)';
		canvas.style.display = '';
		video.style.display = 'block';
		webcam.stop();
	}, 500);
}

fruishopWebcam.onmousedown = (e) => {
	nhanChuot = true;
	
	eventX = e.clientX;
	eventY = e.clientY;
	
	fruishopWebcamLeft = fruishopWebcam.offsetLeft;
	fruishopWebcamTop = fruishopWebcam.offsetTop;
}

fruishopWebcam.onmousemove = (e) => {
	if (nhanChuot == false) {
		return;
	}
	currentEventX = e.clientX;
	currentEventY = e.clientY;
	
	var fruishopWebcamLeftNew = fruishopWebcamLeft + (currentEventX - eventX);
	var fruishopWebcamTopNew = fruishopWebcamTop + (currentEventY - eventY);
	
	fruishopWebcam.style.left = `${fruishopWebcamLeftNew}px`;
	fruishopWebcam.style.top = `${fruishopWebcamTopNew}px`;
}
	
fruishopWebcam.onmouseup = () => {
	nhanChuot = false;
}