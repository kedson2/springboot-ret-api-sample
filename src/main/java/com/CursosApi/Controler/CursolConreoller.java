package com.CursosApi.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CursosApi.Model.Usuario;
import com.CursosApi.Repository.UsuarioRepositorio;

@RestController
public class CursolConreoller {
	@Autowired // injeção da indepedencia
	public UsuarioRepositorio usuariorepositorio;

	@RequestMapping(value = "/api/curso/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)

	public String MSM(@PathVariable String nome) {
		return " Curso de Spring Boot :  " + nome + "!";

	}

	@RequestMapping(value = "/curso/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String olamundo(@PathVariable String nome) {
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usuariorepositorio.save(usu);
		return "Ola Mundo " + nome;

	}

	@GetMapping(value = "listartodos") // primeiro metudo de api
	@ResponseBody // retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuario = usuariorepositorio.findAll(); // executa a consulta no banco de dados
		return new ResponseEntity<>(usuario, HttpStatus.OK);// faz consulta no banco de dados e retorna um json

	}

	@PostMapping(value = "salvar") // mapeamento a url
	@ResponseBody // descriação da resposta
	public ResponseEntity<Usuario> Salvar(@RequestBody Usuario usuario) { // recebe os dados para salvar
		Usuario user = usuariorepositorio.save(usuario);
		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "deletar") // mapeamento a url
	@ResponseBody // descriação da resposta
	public ResponseEntity<String> deletar(@RequestParam Long idUser) { // recebe os dados para deletar
		usuariorepositorio.deleteById(idUser);
		return new ResponseEntity<String>("Usuario Deletado com sucesso", HttpStatus.OK);

	}

	@GetMapping(value = "buscaruserid") // mapeamento a url
	@ResponseBody // descriação da resposta
	public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "id") Long id) { // recebe os dados para consultar
		Usuario usuario = usuariorepositorio.findById(id).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}
	@GetMapping(value = "busacarpornome") // mapeamento a url
	@ResponseBody // descriação da resposta
	public ResponseEntity<List<Usuario>> buscarpornome(@RequestParam(name = "nome") String nome) { // recebe os dados para consultar
		List<Usuario> usuario = usuariorepositorio.buscapornome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}

	@PutMapping(value = "atualizar") // mapeamento a url
	@ResponseBody // descriação da resposta
	public ResponseEntity<?> Atualizar(@RequestBody Usuario usuario) { // recebe os dados para Alterar
		if (usuario.getId() == 0) {	
			return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
		}
		usuariorepositorio.saveAndFlush(usuario);
		return new ResponseEntity<String>("Usuario Atualizado com sucesso", HttpStatus.OK);

	}
}
