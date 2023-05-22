<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/styles.css"/>'>
<title>Depto</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp"/>
	</div>
	<br />
	<div align="center">
		<form action="depto" method="post">
			<table>
				<tr>
					<td>
						<input class="id_input_data" type="number" min="0"
							step="1" id="codigo" name="codigo" placeholder="COD">
					</td>
					<td colspan = 2>
						<input class="input_data" type="text" id="nome" name="nome" placeholder="Nome">
					</td>					
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="botao" name="botao" value="Inserir">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Atualizar">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Excluir">
					</td>										
					<td>
						<input type="submit" id="botao" name="botao" value="Listar">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty lista }">
			<table class="table_round">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Nome</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="d" items="${lista }">
						<tr>
							<td><c:out value="${d.codigo }"/></td>
							<td><c:out value="${d.nome }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty depto }">
			<table class="table_round">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Nome</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${depto.codigo }"/></td>
						<td><c:out value="${depto.nome }"/></td>
					</tr>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>