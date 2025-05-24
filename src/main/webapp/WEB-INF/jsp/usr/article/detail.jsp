<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Detail Content View" />

<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<section class="mt-8">
	<div class="container mx-auto">
		<div class="table-box">
			<div class="overflow-x-auto rounded-box border border-base-content/5 bg-base-100 w-2/3 mx-auto">
				<table class="table">
					<tr>
						<th>번호</th>
						<td>${article.getId() }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${article.getRegDate().substring(2, 16) }</td>
					</tr>
					<tr>
						<th>수정일</th>
						<td>${article.getUpdateDate().substring(2, 16) }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.getMemberId() }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.getTitle() }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.getContent() }</td>
					</tr>
					<tr>
						<th>좋아요 수</th>
						<td>${article.getLikesCnt() }</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="mt-3 text-sm btns flex justify-between w-2/3 mx-auto">
			<div class="flex">
				<button class="btn btn-ghost" onclick="history.back();">뒤로가기</button>
				<c:if test="${article.getMemberId() == req.getLoginedMember().getId() }">
					<div class="mx-2"><a class="btn btn-ghost" href="modify?id=${article.getId() }">수정</a></div>
					<div><a class="btn btn-ghost" href="delete?id=${article.getId() }" onclick="if(confirm('게시물을 삭제하시겠습니까?') == false) return false;">삭제</a></div>
				</c:if>
			</div>
			<div class="flex">
				<a class="btn btn-ghosts" href="doLike?id=${article.getId() }"><i class="fa-solid fa-heart"></i>Like</a>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>