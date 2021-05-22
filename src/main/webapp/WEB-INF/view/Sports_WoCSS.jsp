<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script><script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.js"></script><script type="text/javascript">

					function saveStaticDataToFile(data) {
					
					const url = 'http://localhost:8080/contentAuthorXml';
					var postData
					="";
					for(let i = 0; i < data.length;i++){
					postData += data[i];
					}
					// post body data
					const user = {
					body: postData,
					filename : ""
					};

					// request options
					const options = {
					method: 'POST',
					body: JSON.stringify(user),
					headers: {
					'Content-Type':
					'application/json'
					}
					}

					// send POST request
					fetch(url, options)
					.then(res => res.json())
					.then(res => {
					console.log(res)
					alert('Successfully saved data for the Template!!')
					});

					}

				</script><script type="text/javascript">
					function fromToXml(form){
					
					var xmldata=['<?xml version="1.0"?>'];
					xmldata.push("<template>");
					var inputs=form.elements;
					j = 0;
					for(var i=0;i<inputs.length;i=i+3){
					var
					el=document.createElement("section");
					console.log(inputs[i+2]);
					if
					(typeof inputs[i+2] ==="undefined" || inputs[i+2].id ===
					"undefined"){
					break;
					}
					if (inputs[i+2].id){
					el.setAttribute("name",inputs[i+2].name);
					value =
					CKEDITOR.instances[inputs[i+2].id].getData();
                    
                    value = value.replaceAll('<', '&lt;');     
                    value = value.replaceAll('>','&gt;');              
 					
					console.log('Final Value =', value);
					el.setAttribute("value",value);
					row = 'row'+j;
					col = 'col' +j;
					el.setAttribute("row", document.getElementById(row).value);
					el.setAttribute("col", document.getElementById(col).value);
					xmldata.push(el.outerHTML);
					j++;
					}
					}
					xmldata.push("</template>");
					console.log(xmldata);
					saveStaticDataToFile(xmldata);
					return
					xmldata.join("\n");

					}


				</script>
</head>
<body>
<form id="myform">
<label for="0">Name</label><input id="row0" disabled="true" value="1">1</input><input id="col0" disabled="true" value="1">1</input>
<br>
<textarea id="0" name="Name" rows="10" cols="80"></textarea><label for="1">Location</label><input id="row1" disabled="true" value="2">2</input><input id="col1" disabled="true" value="1">1</input>
<br>
<textarea id="1" name="Location" rows="10" cols="80"></textarea><label for="2">Team Size</label><input id="row2" disabled="true" value="1">1</input><input id="col2" disabled="true" value="2">2</input>
<br>
<textarea id="2" name="Team Size" rows="10" cols="80"></textarea><label for="3">Tournament</label><input id="row3" disabled="true" value="2">2</input><input id="col3" disabled="true" value="2">2</input>
<br>
<textarea id="3" name="Tournament" rows="10" cols="80"></textarea><script type="text/javascript">
						var colATotal=
						4;

						function printA(){
						z = parseInt(colATotal)
						console.log(z);
						for (let i
						= 0; i < z; i++) {
						
						CKEDITOR.replace(document.getElementById(i));
						}
						}

						printA();

					</script><button type="button" onclick="fromToXml(myform)">Create</button>
</form>
</body>
</html>
