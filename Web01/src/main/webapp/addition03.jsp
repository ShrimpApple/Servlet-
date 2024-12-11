<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
	${num1}과 같은 표현은 JSP의 표현 언어(Expression Language 또는 EL)를 사용
	JSP의 EL은 JSP 페이지에서 데이터를 편리하게 출력하고 변수에 접근
	${num1}과 같은 EL 표현은 내장객체
	 -->
	 ${num1} + ${num2} = ${add}
</body>
</html>