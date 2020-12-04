<%@include file="/WEB-INF/views/cabecalho.jsp"%>
<div class="container formulario-cadastro-usuario">
	<h1>Depoimentos</h1>
	<form:form action="${s:mvcUrl('DC#enviaMensagem').build()}"
		commandName="depoimentos" method="post">
		<div class="form-group">
			<label for="titulo">T�tulo:</label> 
			<form:errors path="titulo" cssClass="error"/>
			<input type="text"
				class="form-control" id="titulo" name="titulo" placeholder="T�tulo" required="required">
		</div>
		<div class="form-group">
			<label for="textarea">Mensagem:</label>
			<form:errors path="mensagem" cssClass="error"/>
			<textarea class="form-control" id="textarea" name="mensagem"
				placeholder="Depoimento" rows="8" required="required"></textarea>
		</div>

		<button type="submit" class="btn btn-success pull-right">Enviar</button>
	</form:form>

	<c:if test="${not empty lista}">
		<h2 style="margin-top: 10%">Postagens</h2>
		<table class="table">
			<thead>
				<tr>
					<th>T�tulo</th>
					<th>Mensagem</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="depoimento" items="${lista}">
					<tr>
						<td>${depoimento.titulo}</td>
						<td>${depoimento.mensagem}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>
<%@include file="/WEB-INF/views/rodape.jsp"%>
