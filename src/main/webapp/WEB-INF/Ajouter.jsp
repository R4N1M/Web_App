<jsp:directive.page contentType="text/html;charset=UTF-8" />
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.util.TreeSet" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Ajouter un compteur</title>
		<link rel="stylesheet" href="css/foundation.css">
	  <link href="https://cdnjs.cloudflare.com/ajax/libs/foundation-datepicker/1.5.5/css/foundation-datepicker.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/app.css">
		<link href="https://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css" rel="stylesheet">
		<link href="css_Personel.css" rel="stylesheet">

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/foundation-datepicker/1.5.5/js/foundation-datepicker.min.js"></script>

		<script type="text/javascript">
			$(document).foundation()
		</script>

	</head>

	<body>

		<div class="row">
			<div class="large-12 columns">
				<h1>Ajouter un nouveau compteur: </h1>
			</div>
		</div>

		<form action="/Application/main" method="post" name="formulaire_Ajout">
			<div class="row">
				<div class="large-12 columns">
					<div id="rcorners2" class="callout">

						<div class="row">
							<div class="large-12 columns">
						      	<label>Titre :  </label>
						        <input name="titre" type="text"/>
							</div>
						</div>

						<div class="row">
							<div class="small-12 columns">
					      	<label>Date :</label>
									<input name="date" type="text" class="span2" value="02-12-1989 12:05" id="dpt">

							</div>
						</div>

						<div class="row">
							<div class="large-12 columns">
								<label>Locales :</label>
								<select name="Langues" placeholder="Select a locale">
	                <%
									 TreeSet<String> sortedZones = new TreeSet<>(ZoneId.getAvailableZoneIds());
	                 for (String zone : sortedZones) { %>
	                  <option value="<%out.print(zone);%>"><%out.print(zone);%></option>
	                <%
	                } %>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="large-4-right columns">
								<button type="submit" class="success button" >Enregistrer</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</form>

		<script>
		$(function(){
			$('#dpt').fdatepicker({
				format: 'mm-dd-yyyy//hh:ii:ss',
				disableDblClickSelection: true,
				language: 'vi',
				pickTime: true
			});
		});
		</script>
	</body>
</html>
