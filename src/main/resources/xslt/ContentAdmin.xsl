<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"


	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<b>Admissions Template</b>
				<script
					src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js" />
			</head>
			<body>
				<!-- <xsl:for-each select="form/element"> <xsl:value-of select="@name" 
					/> <xsl:value-of select="@value" /> </xsl:for-each> -->
				<form id="myform">
					<xsl:for-each select="form/element">
						<xsl:variable name="level1Count"
							select="position() - 1" />
						<xsl:value-of select="@name" />
						<xsl:variable name="rowEltID" select="@row" />
						<xsl:variable name="colEltID" select="@col" />
						<br></br>
						<div class="row">
							<div class="col-md-{@col}">
								<textarea id="{$level1Count}" rows="10" cols="80">
									<xsl:value-of select="@value" />
								</textarea>
							</div>
						</div>
					</xsl:for-each>
					<script type="text/javascript">
						var colATotal=
						<xsl:value-of select='count(form/element)' />;

						function printA(){
						z = parseInt(colATotal)
						console.log(z);
						for (let i
						= 0; i &lt; z; i++) {

						value =
						document.getElementById(i).value;
						editInstance = CKEDITOR.replace(document.getElementById(i),
						{readOnly:true});
						<!-- CKEDITOR.instances[editInstance].setData(value); -->
                        <![CDATA[
		                    value = value.replaceAll('&lt;', '<');
		                    value = value.replaceAll('&gt;', '>');
		 				]]>
						console.log(value);
						editInstance.setData(value);
						}
						}

						printA();

					</script>

				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
