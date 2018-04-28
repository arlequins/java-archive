<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>서버 확인 페이지</title>
<link rel="icon" href="<c:url value='/static/images/ffxiv_32.png' />">
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/static/media/" var="MEDIA" />
<spring:url value="/static/js/" var="JS" />
<script src="${JS}jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${MEDIA}js/jquery.dataTables.min.js"></script>
<link href="${MEDIA}css/jquery.dataTables.css" rel="stylesheet">
</head>
<body>
	<div class="container theme-showcase">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div class="col-xs-12 col-sm-12 col-md-12" style="padding: 0px; padding-right: 10px">
				<div class="success">
					<h2>웹 페이지 컨트롤러</h2>
					<h3>${nowdate}</h3>
					<br />
					<table class="table" id="table5_go">
						<thead>
							<tr>
								<th>type</th>
								<th>target</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td><a href="<c:url value="/7days_save"/>">데이터 수집(수동)</a></td>
									<td><a href="<c:url value="/convert_hours"/>">시간 단위 정렬(수동)</a></td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>