<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>연산 요청 결과</h2>
<hr>
<span>${result.hour}시 ${result.minute}분에 당첨!! 추카추카</span><br><br>
<image src="images/apple.gif" />
<a href=${header.referer}>로또 응모 링크</a>
</body>
</html>