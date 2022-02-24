


function pesquisauser() {
	var nome = $("#nameuser").val();
	if (nome != null && nome.trim() != "") {
		$.ajax({
			method: "GET",
			url: "busacarpornome",
			data: "nome=" + nome,
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				$("#tebalaresultado > tbody > tr").remove();
				for (var i = 0; i < response.length; i++) {
					$("#tebalaresultado > tbody").append('<tr id="' + response[i].id + '"> <td>' + response[i].id + '</td><td>' + response[i].nome
						+ '</td><td>' + response[i].morada + '</td><td>' + response[i].contacto + '</td><td><button type="button" class="btn btn-primary" onclick="editar(' + response[i].id + ')">ver</button></td> <td><button type="button" class="btn btn-danger" onclick="deletarUser(' + response[i].id + ')"> DEL </butoon> </td></tr>');
				}
			}
		}).fail(function(xhr, status, errorthrown) {
			alert("Erro a pesquesiar ususario" + xhr.responseText);
		});

	}

}

function SalvarUsuario() {
	var nome = $("#nome").val();
	var idade = $("#idade").val();
	var morada = $("#morada").val();
	var contacto = $("#contacto").val();
	var id = $("#id").val();
	if (nome == null || nome != null && nome.trim() == "") {
		$("#nome").focus();
		alert("Informe o Nome ");
		return;
	}
	if (idade == null || idade != null && idade.trim() == "") {
		$("#idade").focus();
		alert("Informe a Idade ");
		return;
	}
	if (idade < 0) {
		$("#idade").focus();
		alert("Idade não é valida");
		return;
	}
	if (morada == null || morada != null && morada.trim() == "") {
		$("#morada").focus();
		alert("Informe a Morada");
		return;
	}
	if (contacto == null || contacto != null && contacto.trim() == "") {
		$("#contacto").focus();
		alert("Informe o Contacto");
		return;
	}
	if (contacto.length > 7 || contacto.length < 7) {
		$("#contacto").focus();
		alert("O contacto não valido")
		return;
	}
	if (id != null && id.trim() == "") {
		$.ajax({
			method: "POST",
			url: "salvar",
			data: JSON.stringify({ nome: nome, morada: morada, contacto: contacto, idade: idade }),
			contentType: "application/json; charset=utf-8",
			success: function(response) {

				alert("usuario cadastrado com sucesso")
				document.getElementById('cadastro').reset()
				$("#id").val(response.id);
			}

		}).fail(function(xhr, status, errorthrown) {
			alert("usuario não cadastrado" + xhr.responseText);
		});

	} else {
		$.ajax({
			method: "PUT",
			url: "atualizar",
			data: JSON.stringify({ id: id, nome: nome, morada: morada, contacto: contacto, idade: idade }),
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				$("#id").val(response.id);
				alert(response);
				document.getElementById('cadastro').reset()
			}
		}).fail(function(xhr, status, errorthrown) {
			alert("usuario não cadastrado" + xhr.responseText);
		});

	}


}


function deletarUser(id) {
	if (confirm("Deseja deletar?")) {
		$.ajax({
			method: "DELETE",
			url: "deletar",
			data: "idUser=" + id,
			success: function(response) {
				$("#" + id).remove();
				alert(response);
			}
		}).fail(function(xhr, status, errorthrown) {
			alert("ERRO  a deletar Usuario " + xhr.responseText);
		});
	}
}
function deletar_tela() {
	var id = $("#id").val();
	if (id != null || id.trim == "") {
		alert(" usuario não selecionado")
		return;
	}
	deletarUser(id);
	document.getElementById('cadastro').reset()
}


function editar(id) {

	$.ajax({
		method: "GET",
		url: "buscaruserid",
		data: "id=" + id,
		success: function(response) {

			$("#nome").val(response.nome);
			$("#idade").val(response.idade);
			$("#morada").val(response.morada);
			$("#contacto").val(response.contacto);
			$("#id").val(response.id);
			$('#pesquisaruser').modal('hide');
			document.getElementById('pesquisaruser').reset()
		}
	}).fail(function(xhr, status, errorthrown) {
		alert("Erro a pesquisar usuario por id " + xhr.responseText);
	});

}



