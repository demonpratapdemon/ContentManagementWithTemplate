<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
   function child123(pid) {

      // document.getElementById("templateForm").elements = null;
      // document.forms["templateForm"].reset();
      // document.getElementById("templateForm").reset();'
      var eltList = document.getElementById("templateForm").elements;

      for(let j = 1; j < eltList.length;){
         if (typeof eltList[j] !=="undefined" && (eltList[j].id !==
               "undefined" &&  eltList[j].id != "create"))
            eltList[j].remove();
      }

      console.log("in the function");

      var dimensions = pid.split("X");
      var totalFields = dimensions[0]*dimensions[1];
      console.log(totalFields);
      const parent = document.getElementById("templateForm");
      var i;
      /* const hed = document.createElement('h3');
      hed.append("Insert your Section Name , Row Number and Column Number "); */
      for (i = 0; i < totalFields; i++) {

      //const lable = document.createElement('lable');
         console.log(i);

      const finput1 = document.createElement('input');

      const finput2 = document.createElement('input');

      const finput3 = document.createElement('input');

      finput1.setAttribute('type', 'string');
      finput1.setAttribute('placeholder', 'Section Name');
      finput1.setAttribute('name', 'IIITB');
      //finput1.setAttribute('value','Mtech');

      finput2.setAttribute('type', 'string');
      finput2.setAttribute('placeholder', 'Row Num');
      finput2.setAttribute('name', 'IIITB');

      finput3.setAttribute('type', 'string');
      finput3.setAttribute('placeholder', 'Col Num');
      finput3.setAttribute('name', 'IIITB');

      top++;
      

      const bre = document.createElement('br');

      /* parent.append(hed); */
      parent.append(bre);
      parent.append(finput1);
      parent.append(finput2);
      parent.append(finput3);
      }
      console.log(parent);
   }

   var val;
   function fromToXml(form) {

      console.log(document.getElementById(form));
      var xmldata = [ '<?xml version="1.0"?>' ];
      xmldata
            .push('<template xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" name="into">');

      var inputs = document.getElementById(form).elements;
      console.log(form);
      console.log(inputs.length);

      for (var i = 1; i < inputs.length; i = i + 3) {
         console.log(i + "<-value of -i");
         if (inputs[i].name) {
            console.log(i + "<-value of i");
            var el = document.createElement("SECTION");
            var j = i + 1;
            var k = i + 2;
            el.setAttribute("name", inputs[i].value);
            el.setAttribute("row", inputs[j].value);
            el.setAttribute("col", inputs[k].value);
            var g = "";
            el.setAttribute("value", g);

            // if(inputs[j].name)
            // el.setAttribute("row",inputs[j].value);
            // else
            // el.setAttribute("row",);

            xmldata.push(el.outerHTML);

         }

      }
      xmldata.push("</template>");
      val = xmldata.join("\n");
      console.log(val);
      return val;
   }

   function saveStaticDataToFile() {
      const url = 'http://localhost:8080/templateAuthorXml';
      const templateName = document.getElementById('templateName').value + ".xml";

      // post body data 
      const user = {
            body: val,
            filename : templateName
      };    

      // request options
      const options = {
          method: 'POST',
          body: JSON.stringify(user),
          headers: {
              'Content-Type': 'application/json'
          }
      }

      // send POST request
      fetch(url, options)
          .then(res => res.json())
          .then(res => {
              console.log(res)
              alert('Successfully created the Template!!')
          });
    }
</script>
<title>Template Creation</title>
</head>
<body>
	<h1>Welcome to Template Creation</h1>
	<div id="root">
		<input id="templateName" placeholder="Enter Template Name">
	</div>

	<div>
		<button class="button3" type="button" onclick="child123('2X1')"
			value="2X1" id="2X1">2x1</button>
		<button class="button3" type="button" onclick="child123('2X2')"
			value="2X2" id="2X2">2x2</button>
		<button class="button3" type="button" onclick="child123('3X3')"
			value="3X3" id="3X3">3X3</button>
		<button class="button3" type="button" onclick="child123('4X4')"
			value="4X4" id="4X4">4X4</button>

		<%--      <button value="Add Field" class="btn btn-success"--%>
		<%--         onclick="child123('shaily')">Add Field</button>--%>
		<hr>
	</div>
	<div id="templateDiv">
		<form id='templateForm'>
			<button class="button3" type="button"
				onclick="fromToXml('templateForm')" id="create">Create
				Template</button>
		</form>
	</div>
	<%--   <form id='2X3'>--%>
	<%--      <button class="button4" type="button" onclick="fromToXml('2X3')">Create--%>
	<%--         Template</button>--%>
	<%--   </form>--%>
	<%--   --%>


	<button class="button3" type="button" onclick="saveStaticDataToFile()">Save
		Template</button>

</body>
</html>