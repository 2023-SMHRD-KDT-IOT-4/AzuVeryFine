<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
	<!-- Navbar Brand-->
	<a class="navbar-brand ps-3" href="index">AzuVeryFine</a>
	<!-- Sidebar Toggle-->
	<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
		id="sidebarToggle" href="#!">
		<i class="fas fa-bars"></i>
	</button>
	<!-- Navbar-->
	<ul class="navbar-nav ms-auto me-3 me-lg-4">
			<li class="nav-item">
                <span class="nav-link"><%= session.getAttribute("email") %>님 환영합니다.</span>
            </li>
	    <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            <i class="fas fa-user fa-fw"></i>
	        </a>
	        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown" id="navbarDropdownMenu">
	            <li><hr class="dropdown-divider" /></li>
	            <li><a class="dropdown-item" href="loginpage" onclick="deleteTokenFromCookie();">로그아웃</a></li>
	        </ul>
	    </li>
	</ul>
</nav>
<script>

function deleteTokenFromCookie() {
    document.cookie = "accessToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    document.cookie = "refreshToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}
</script>