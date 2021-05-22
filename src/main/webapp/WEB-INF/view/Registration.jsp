<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">

<title>Registration</title>
</head>
<body>

	<form action="RegistrationServlet" method="post">
		<div>
			<label class="form-label">Enter First Name</label> <input
				name="first_name" required type="text" class="form-control">
		</div>

		<div>
			<label class="form-label">Enter Last Name</label> <input
				name="last_name" required type="text" class="form-control">
		</div>

		<div>
			<label class="form-label">Enter email</label> <input name="email"
				required type="email" class="form-control">
		</div>

		<div>
			<label class="form-label">Enter password</label> <input
				name="password" required type="password" class="form-control">
		</div>

		<div>
			<br> <label for="role">Choose a Role:</label> <select
				name="role" id="role">
				<option value="template-author">Template Author</option>
				<option value="content-author">Content Author</option>
				<option value="content-admin">Content Admin</option>
			</select>

		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form>

	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    -->
</body>
</html>