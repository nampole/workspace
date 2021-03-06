<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<html>
<head>
<title><spring:message code="itemAdd.title" /></title>
</head>
<body>
<form:form modelAttribute="item" action="register.html"
	enctype="multipart/form-data">
	<div align="center" class="body">
	<h2><font color="green"><spring:message
		code="itemAdd.title" /></font></h2>
	<table>
		<tr height="40px">
			<td>상품명</td>
			<td><form:input path="itemName" cssClass="itemName"
				maxlength="20" /></td>
			<td><font color="red"><form:errors path="itemName" /></font></td>
		</tr>
		<tr height="40px">
			<td>가격</td>
			<td><form:input path="price" cssClass="price" maxlength="6" />&nbsp;원
			</td>
			<td><font color="red"><form:errors path="price" /></font></td>
		</tr>
		<tr height="40px">
			<td>파일</td>
			<td><input type="file" name="picture" /></td>
			<td></td>
		</tr>
		<tr height="40px">
			<td>개요</td>
			<td><form:textarea path="description" cssClass="description" />
			</td>
			<td><font color="red"><form:errors path="description" /></font></td>
		</tr>
	</table>
	<br>
	<input type="submit" value="등록" /> <input type="reset" value="리셋" /><br>
	<br>
	<a href="index.html">돌아가기</a> <br>
	<a href="../j_spring_security_logout">■로그아웃하기</a></div>
</form:form>
</body>
</html>