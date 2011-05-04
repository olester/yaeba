<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="sidebar">
	<ul>
		<li><p>
				<sec:authorize access="isAuthenticated()">
					<a href='<c:url value="/j_spring_security_logout" />'>Déconnexion</a>
				</sec:authorize>
			</p>
		</li>
	</ul>

	<!--		<ul>-->
	<!--			<li>-->
	<!--			<h2>Veroeros sit dolore</h2>-->
	<!--			<p>-->
	<!--				<strong>Donec turpis orci</strong> facilisis et ornare eget, sagittis eu massa. Quisque dui diam, euismod et-->
	<!--				lobortis sed etiam lorem ipsum dolor etiam nullam et faucibus. <a href="#">More&#8230;</a>-->
	<!--			</p>-->
	<!--		</li>-->
	<!--		<li>-->
	<!--			<h2>Categories</h2>-->
	<!--			<ul>-->
	<!--				<li><span>09.13.09</span><a href="#">Vestibulum risus vitae</a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Condimentum et molestie</a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Facilisis sed vestibulum</a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Ipsum primis et sed luctus </a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Ultrices posuere nulla </a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Accumsan lorem sodales </a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Scelerisque consectetur </a></li>-->
	<!--				<li><span>09.13.09</span><a href="#">Maecenas quam aliquet</a></li>-->
	<!--			</ul>-->
	<!--		</li>-->
	<!--	</ul>-->
</div>