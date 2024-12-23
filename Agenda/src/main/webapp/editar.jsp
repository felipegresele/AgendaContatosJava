<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="img/favicon.png">
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Editar Contato</h1>
	<form name="frmContato" action="update">
		<table>
		<!-- essa caixa do id tem q ser bloqueada para o usuário nao pode mudar o id -->
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="caixa" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="caixa" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="btn" onclick="validar()"> 
	</form>
	
<script src="validador.js"></script>
</body>
</html>