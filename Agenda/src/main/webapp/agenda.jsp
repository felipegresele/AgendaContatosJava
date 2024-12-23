<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
//for ( int i =0; i < lista.size(); i++) {
//	out.println(lista.get(i).getIdcon());
//	out.println(lista.get(i).getNome());
//	out.println(lista.get(i).getFone());
//	out.println(lista.get(i).getEmail());
//	}
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="img/favicon.png">
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="btn">Novo Contato</a>
	<table class="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
			</tr>
		</thead>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++) {%>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="btn">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getIdcon() %>)"
					class="btn2">Excluir</a></td>
				</tr>
			<%} %>
		<tbody/>
	</table>
	<script src="confirmar.js"></script>
</body>
</html>