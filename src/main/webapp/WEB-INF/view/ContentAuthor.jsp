<!doctype html>
<%@page import="java.util.Random"%>
<%@ page import="java.io.File"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">

<title>Templates</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<br>
	<br>
	<h1 class="text-center">Available Templates :</h1>
	<br>
	<br>
	<!-- <div class="card"> -->
		<%
		String folderPath = System.getenv("INPUT") + File.separator + "ContentManagement" + File.separator + "templates";
		File templatesFolder = new File(folderPath);
		String[] templates = templatesFolder.list();
		for (String template : templates) {
		%>
		<div class="card">
			<div>
				<br> <br>
			</div>
			<div class="card-header">
				<%
				out.print(template);
				%>
			</div>
			<div class="card-body">
				<h5 class="card-title">
					Template for
					<%
				out.print(template);
				%>
				</h5>
				<p class="card-text">With supporting text below as a natural
					lead-in to additional content.</p>
				<form action="AdmissionServlet" method="post">

					<input id="btn" type="submit" value=Submit class="btn btn-primary" />
					<%
					String xmlFile = folderPath + File.separator + template + File.separator
							+ new File(folderPath + File.separator + template).list()[0];
					%>
					<input type="hidden" id="template" name="template"
						value="<%out.print(xmlFile);%>">
				</form>
			</div>
			</div>
			<%
			}
			%>
	<!-- </div> -->

	<!-- Optional JavaScript; choose one of the two! -->

		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
			crossorigin="anonymous"></script>

		<!-- Option 2: Separate Popper and Bootstrap JS -->
		<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    -->
</body>
</html>