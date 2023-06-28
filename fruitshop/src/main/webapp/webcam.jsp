<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="./css/webcam.css">

<div class="webcam">
 	<div class="header-webcam">
 		<span style="margin: auto; font-size: 24px; font-weight: 600; cursor: default; user-select: none;">FruitShop Camera</span>
 		<div class="tat-webcam">
 			<i class="fa-solid fa-xmark" style="margin: auto;"></i>
 		</div>
 	</div>
	<div style="height: 600px;">
		<video autoplay playsinline width="800" height="600"></video>
		<canvas></canvas>
	</div>
	<div class="footer-webcam">
		<div style="width: 215px; margin: auto; display: flex; justify-content: space-between;">		
			<a class="dowload-photo" download>
				<i class="fa-solid fa-camera" style="margin: auto;"></i>
			</a>
			
			<div class="chup-lai-anh">
				<i class="fa-solid fa-rotate-left" style="margin: auto;"></i>
			</div>
		</div>
	</div>
</div>