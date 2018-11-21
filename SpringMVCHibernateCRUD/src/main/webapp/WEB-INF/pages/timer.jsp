<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/fragments/headerTeacher.jspf" %>
</head>
<body>

<div class="container" style="margin:0 auto;">
		<table style="margin:0 auto;">
			<tr>
				<td>
				<form id="form1" method="get" action="goUpMinutes">
					<button class="btn btn-success btn-timer" name="timeCountDown" value="${timeCountDown}" style="width:200px; margin-left:20px;" >
						<span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
					</button>
				</form>
				
				</td>
				<td>
				<form id="form2" method="get" action="goUpSeconds">
					<button class="btn btn-success btn-timer" name="timeCountDown" value="${timeCountDown}" style="width:200px; margin-left:-50px;">
						<span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
					</button>
				</form>
				</td>
			</tr>
			<tr>
				<td colspan="2">
 		        	<div id="CountDownTimer" data-timer="${timeCountDown}" style="width: 550px; height: 250px;"></div>
				</td>
			</tr>
			<tr>
				<td>
				<form id="form3" method="get" action="goDownMinutes">
					<button class="btn btn-success" name="timeCountDown" value="${timeCountDown}" style="width:200px; margin-left:20px;">
						<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
					</button>
				</form>
				</td>
				<td>
				<form id="form4" method="get" action="goDownSeconds">
					<button class="btn btn-success" name="timeCountDown" value="${timeCountDown}" style="width:200px; margin-left:-50px;" >
						<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
					</button>
				</form>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
				<br>
				<div style="padding-left:20px;">
 		        	<button class="btn btn-success startTimer">Start Timer</button>
					<button class="btn btn-danger stopTimer">Stop Timer</button>
					<button class="btn btn-info resetTimer">Reset Timer</button>
					</div>
				</td>
			</tr>

		</table>

        </div>
        <script>
            $("#DateCountdown").TimeCircles();
            $("#CountDownTimer").TimeCircles({ time: { Days: { show: false }, Hours: { show: false } }});
            $("#CountDownTimer").TimeCircles().stop();
            $("#PageOpenTimer").TimeCircles();
            
            var updateTime = function(){
                var date = $("#date").val();
                var time = $("#time").val();
                var datetime = date + ' ' + time + ':00';
                $("#DateCountdown").data('date', datetime).TimeCircles().start();
            }
            $("#date").change(updateTime).keyup(updateTime);
            $("#time").change(updateTime).keyup(updateTime);
            
            // Start and stop are methods applied on the public TimeCircles instance
            $(".startTimer").click(function() {
                $("#CountDownTimer").TimeCircles().start();
            });
            $(".stopTimer").click(function() {
                $("#CountDownTimer").TimeCircles().stop();
            });
            $(".resetTimer").click(function() {
            	
                $("#CountDownTimer").TimeCircles().restart();
                $("#CountDownTimer").TimeCircles().stop();
            });

            // Fade in and fade out are examples of how chaining can be done with TimeCircles
            $(".fadeIn").click(function() {
                $("#PageOpenTimer").fadeIn();
            });
            $(".fadeOut").click(function() {
                $("#PageOpenTimer").fadeOut();
            });
            
        </script>       
        
</body>
</html>