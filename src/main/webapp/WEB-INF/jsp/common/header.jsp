<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- 테일윈드, daisyUI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- 공용 CSS -->
<link rel="stylesheet" href="/resource/common.css" />
<meta charset="UTF-8">
<title>${pageTitle }</title>
</head>
<body>

	<div class="navbar bg-base-100 shadow-sm">
		<div class="navbar-start">
			<div class="dropdown">
				<div class="flex-none">
					<button class="btn btn-square btn-ghost ml-5">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block h-5 w-5 stroke-current">
							<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
						</svg>
					</button>
				</div>
				<ul tabindex="0" class="menu menu-sm dropdown-content bg-base-100 rounded-box z-1 mt-3 w-52 p-2 shadow">
					<li><a href="/usr/article/list?boardId=1">Notice</a></li>
					<li><a href="/usr/article/list?boardId=2">Free board</a></li>
					<li><a href="/usr/article/list?boardId=3">QnA</a></li>
				</ul>
			</div>
		</div>
		<div class="navbar-center">
			<a class="btn btn-ghost text-xl" href="/">Article</a>
		</div>
		<div class="navbar-end">
			<ul class="flex">
				<c:if test="${req.getLoginedMember().getId() == 0 }">
					<li><a role="button" class="btn btn-ghost p-5 mr-5" href="/usr/member/login">Login</a></li>
					<li><a role="button" class="btn btn-ghost p-5 mr-5" href="/usr/member/join">Sign Up</a></li>
				</c:if>
				<c:if test="${req.getLoginedMember().getId() != 0 }">
					<li><a role="button" class="btn btn-ghost p-5 mr-5" href="/usr/member/logout">Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>