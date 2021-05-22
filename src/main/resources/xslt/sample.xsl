<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<script
					src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js" />
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.js" />
				<script type="text/javascript">

					function saveStaticDataToFile(data) {
					<!-- data = data.join("\n"); var blob = new Blob([data], { type: "text/plain;charset=utf-8" 
						}); saveAs(blob, "static.xml"); console.log('here', data); -->
					const url = 'http://localhost:8080/contentAuthorXml';
					var postData
					="";
					for(let i = 0; i &lt; data.length;i++){
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

				</script>

				<script type="text/javascript">
					function fromToXml(form){
					<!-- console.log(form) -->
					var xmldata=['&lt;?xml version="1.0"?&gt;'];
					xmldata.push("&lt;template&gt;");
					var inputs=form.elements;
					j = 0;
					for(var i=0;i&lt;inputs.length;i=i+3){
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
                    <![CDATA[
                    value = value.replaceAll('<', '&lt;');     
                    value = value.replaceAll('>','&gt;');              
 					]]>
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
					xmldata.push("&lt;/template&gt;");
					console.log(xmldata);
					saveStaticDataToFile(xmldata);
					return
					xmldata.join("\n");

					}


				</script>


			</head>

			<body>

				<form id="myform">
					<xsl:for-each select="template/section">
						<xsl:variable name="level1Count"
							select="position() - 1" />
						<xsl:variable name="eltID" select="@name" />
						<xsl:variable name="rowEltID" select="@row" />
						<xsl:variable name="colEltID" select="@col" />
						<label for="{$level1Count}">
							<xsl:value-of select="$eltID" />
						</label>

						<input id="row{$level1Count}" disabled="true" value="{@row}">
							<xsl:value-of select="$rowEltID" />
						</input>

						<input id="col{$level1Count}" disabled="true" value="{@col}">
							<xsl:value-of select="$colEltID" />
						</input>

						<br></br>
						<!-- <input type="text" id="{$eltID}" name="{$eltID}"/><br></br> -->
						<textarea id="{$level1Count}" name="{$eltID}" rows="10"
							cols="80">
						</textarea>


					</xsl:for-each>
					<script type="text/javascript">
						var colATotal=
						<xsl:value-of select='count(template/section)' />;

						function printA(){
						z = parseInt(colATotal)
						console.log(z);
						for (let i
						= 0; i &lt; z; i++) {
						<!-- console.log(document.getElementById(i)); -->
						CKEDITOR.replace(document.getElementById(i));
						}
						}

						printA();

					</script>
					<button type="button" onclick="fromToXml(myform)">Create</button>
					<!-- <button type="button" onclick="saveStaticDataToFile()">Submit</button> -->

				</form>
			</body>
		</html>

	</xsl:template>
</xsl:stylesheet>
