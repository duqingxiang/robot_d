<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Memorys</title>
	<link rel="stylesheet" type="text/css" href="/css/touch/normalize.css">
	<link rel="stylesheet" type="text/css" href="/css/touch/default.css">
	<% response.setContentType("text/html; charset=utf-8"); %>
	<style>
		@import url(http://fonts.useso.com/css?family=Raleway:200,500,700,800);
		@-webkit-keyframes animation{from{opacity:0; -webkit-transform:scale(1.2) rotateX(45deg);transform:scale(1.2) rotateX(45deg);} to{ }}
	
		@-webkit-keyframes animation2{from{opacity:0; -webkit-transform:scale(1.2) rotateX(45deg);transform:scale(1.2) rotateX(45deg);} to{ }}
		body {
		   background:#1c1c1c;
	       /*-webkit-perspective:1000px;*/
	       /*overflow:hidden;*/
		}
		.slider div p{color:#1c1c1c;position:absolute;bottom:-55px; font-family:Raleway; font-size:16px;}
		.slider{
		    -webkit-animation:animation ease 1s;animation-delay:.8s;animation-fill-mode:backwards;
		    margin: 0 auto;
		    height:580px;
		    width:420px;
		    padding:40px;
		    top:100px;
		    perspective:1000px;
		    transition:ease-in-out .2s;
		    /*-webkit-transform:rotateX(45deg);
		    -webkit-transform-style:preserve-3d;
		    position:absolute;*/
		}
		/*.slider:active{ -webkit-transform:rotateZ(10deg);}*/
		.slide img { text-align:center;width:100%; height:100%; -webkit-user-drag:none;user-drag:none;-moz-user-drag:none; border-radius:2px; }
		.slide{
		    -webkit-user-select:none;
		    user-select:none;
		    -moz-user-select:none;
		    position:absolute;
		    height:500px;
		    width:400px;
		    box-shadow: 0px 10px 30px 0px rgba(0,0,0,0.3);
		    background:#fcfcfc;
		    -webkit-transform-style:preserve-3d;
		    transform-style:preserve-3d;
		    -moz-transform-style:preserve-3d;
		    text-align:center;
		    /*overflow:hidden;*/
		    border:12px white solid;
		    box-sizing:border-box;
		    border-bottom:100px white solid;
		    border-radius:5px; 
		}
		.transition {
		    -webkit-transition: cubic-bezier(0,1.95,.49,.73) .4s ;
		    -moz-transition: cubic-bezier(0,1.95,.49,.73) .4s ;
		    transition: cubic-bezier(0,1.95,.49,.73) .4s ;
		}
	</style>
</head>
<body>
	<div class="htmleaf-container">
		
		<div class="htmleaf-content">
			<div class="slider">
		        <div class="slide" style="transform: translateZ(-350px) translateY(60px);"><img src="http://lonelyrobots.cn/domain/upload/photos/1.jpg"><p>Simple & Smile</p></div>
		        <div class="slide" style="transform: translateZ(-300px) translateY(50px);"><img src="http://lonelyrobots.cn/domain/upload/photos/4.jpg"><p>Simple & Smile</p></div>
		        <div class="slide" style="transform: translateZ(-250px) translateY(40px);"><img src="http://lonelyrobots.cn/domain/upload/photos/15.jpg"><p>The Happiness...</p></div>
		        <div class="slide" style="transform: translateZ(-200px) translateY(30px);"><img src="http://lonelyrobots.cn/domain/upload/photos/10.jpg"><p>Take Your Hand...</p></div>
		        <div class="slide" style="transform: translateZ(-150px) translateY(20px);"><img src="http://lonelyrobots.cn/domain/upload/photos/9.jpg"><p>You & Me</p></div>
		        <div class="slide" style="transform: translateZ(-10px) translateY(10px);"><img src="http://lonelyrobots.cn/domain/upload/photos/7-1.jpg"><p>春风十里，不如你...</p></div>
		        <div class="slide" style="transform: translateZ(-50px) translateY(0px);"><img src="http://lonelyrobots.cn/domain/upload/photos/8.jpg"><p>You & Me</p></div>
		        <div class="slide" style="transform: translateZ(0px) translateY(0px);"><img src="http://lonelyrobots.cn/domain/upload/robots.jpg"><p>Just a Robot's Memory,Begin...</p></div>
		    </div>
		</div>
		
	</div>
	
	<script src="/js/touch/stopExecutionOnTimeout.js"></script>
	<script>
		window.addEventListener('load', onWndLoad, false);
		function onWndLoad() {
		    var slider = document.querySelector('.slider');
		    var sliders = slider.children;
		    var initX = null;
		    var transX = 0;
		    var rotZ = 0;
		    var transY = 0;
		    var curSlide = null;
		    var Z_DIS = 50;
		    var Y_DIS = 10;
		    var TRANS_DUR = 0.4;
		    var images = document.querySelectorAll('img');
		    for (var i = 0; i < images.length; i++) {
		        if (window.CP.shouldStopExecution(1)) {
		            break;
		        }
		        images[i].onmousemove = function (e) {
		            e.preventDefault();
		        };
		        images[i].ondragstart = function (e) {
		            return false;
		        };
		    }
		    window.CP.exitedLoop(1);
		    function init() {
		        var z = 0, y = 0;
		        for (var i = sliders.length - 1; i >= 0; i--) {
		            if (window.CP.shouldStopExecution(2)) {
		                break;
		            }
		            sliders[i].style.transform = 'translateZ(' + z + 'px) translateY(' + y + 'px)';
		            z -= Z_DIS;
		            y += Y_DIS;
		        }
		        window.CP.exitedLoop(2);
		        attachEvents(sliders[sliders.length - 1]);
		    }
		    function attachEvents(elem) {
		        curSlide = elem;
		        curSlide.addEventListener('mousedown', slideMouseDown, false);
		        curSlide.addEventListener('touchstart', slideMouseDown, false);
		    }
		    init();
		    function slideMouseDown(e) {
		        if (e.touches) {
		            initX = e.touches[0].clientX;
		        } else {
		            initX = e.pageX;
		        }
		        document.addEventListener('mousemove', slideMouseMove, false);
		        document.addEventListener('touchmove', slideMouseMove, false);
		        document.addEventListener('mouseup', slideMouseUp, false);
		        document.addEventListener('touchend', slideMouseUp, false);
		    }
		    var prevSlide = null;
		    function slideMouseMove(e) {
		        var mouseX;
		        if (e.touches) {
		            mouseX = e.touches[0].clientX;
		        } else {
		            mouseX = e.pageX;
		        }
		        transX += mouseX - initX;
		        rotZ = transX / 20;
		        transY = -Math.abs(transX / 15);
		        curSlide.style.transition = 'none';
		        curSlide.style.webkitTransform = 'translateX(' + transX + 'px)' + ' rotateZ(' + rotZ + 'deg)' + ' translateY(' + transY + 'px)';
		        curSlide.style.transform = 'translateX(' + transX + 'px)' + ' rotateZ(' + rotZ + 'deg)' + ' translateY(' + transY + 'px)';
		        var j = 1;
		        for (var i = sliders.length - 2; i >= 0; i--) {
		            if (window.CP.shouldStopExecution(3)) {
		                break;
		            }
		            sliders[i].style.webkitTransform = 'translateX(' + transX / (2 * j) + 'px)' + ' rotateZ(' + rotZ / (2 * j) + 'deg)' + ' translateY(' + Y_DIS * j + 'px)' + ' translateZ(' + -Z_DIS * j + 'px)';
		            sliders[i].style.transform = 'translateX(' + transX / (2 * j) + 'px)' + ' rotateZ(' + rotZ / (2 * j) + 'deg)' + ' translateY(' + Y_DIS * j + 'px)' + ' translateZ(' + -Z_DIS * j + 'px)';
		            sliders[i].style.transition = 'none';
		            j++;
		        }
		        window.CP.exitedLoop(3);
		        initX = mouseX;
		        e.preventDefault();
		        if (Math.abs(transX) >= curSlide.offsetWidth - 30) {
		            document.removeEventListener('mousemove', slideMouseMove, false);
		            document.removeEventListener('touchmove', slideMouseMove, false);
		            curSlide.style.transition = 'ease 0.2s';
		            curSlide.style.opacity = 0;
		            prevSlide = curSlide;
		            attachEvents(sliders[sliders.length - 2]);
		            slideMouseUp();
		            setTimeout(function () {
		                slider.insertBefore(prevSlide, slider.firstChild);
		                prevSlide.style.transition = 'none';
		                prevSlide.style.opacity = '1';
		                slideMouseUp();
		            }, 201);
		            return;
		        }
		    }
		    function slideMouseUp() {
		        transX = 0;
		        rotZ = 0;
		        transY = 0;
		        curSlide.style.transition = 'cubic-bezier(0,1.95,.49,.73) ' + TRANS_DUR + 's';
		        curSlide.style.webkitTransform = 'translateX(' + transX + 'px)' + 'rotateZ(' + rotZ + 'deg)' + ' translateY(' + transY + 'px)';
		        curSlide.style.transform = 'translateX(' + transX + 'px)' + 'rotateZ(' + rotZ + 'deg)' + ' translateY(' + transY + 'px)';
		        var j = 1;
		        for (var i = sliders.length - 2; i >= 0; i--) {
		            sliders[i].style.transition = 'cubic-bezier(0,1.95,.49,.73) ' + TRANS_DUR / (j + 0.9) + 's';
		            sliders[i].style.webkitTransform = 'translateX(' + transX + 'px)' + 'rotateZ(' + rotZ + 'deg)' + ' translateY(' + Y_DIS * j + 'px)' + ' translateZ(' + -Z_DIS * j + 'px)';
		            sliders[i].style.transform = 'translateX(' + transX + 'px)' + 'rotateZ(' + rotZ + 'deg)' + ' translateY(' + Y_DIS * j + 'px)' + ' translateZ(' + -Z_DIS * j + 'px)';
		            j++;
		        }
		        document.removeEventListener('mousemove', slideMouseMove, false);
		        document.removeEventListener('touchmove', slideMouseMove, false);
		    }
		}
	</script>

</body></html>