<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
span { color : #FF0066; }
</style>
</head>
<body>
<h2>연산 요청 결과</h2>
<hr>
<span>${result.hour}시 ${result.minute}분에 당첨 실패!! 아쉽아쉽</span><br><br>
<image src="images/banana.jpg" />
<a href=${header.referer}>로또 응모 링크</a>
</body>
</html>