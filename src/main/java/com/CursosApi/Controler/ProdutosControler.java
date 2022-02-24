package com.CursosApi.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CursosApi.Model.Produtos;
import com.CursosApi.Repository.ProdutosRepository;

@Controller
public class ProdutosControler {

	@Autowired
	public ProdutosRepository produtosRepository;

	@PostMapping(value = "produtos")
	@ResponseBody
	public ResponseEntity<Produtos> Salvar(@RequestBody Produtos produtos) {
		//Categoria c = new Categoria();
		//c.setId(1);
		//produtos.setCategoria(c);

		produtos = produtosRepository.save(produtos);
		return new ResponseEntity<>(produtos, HttpStatus.CREATED);

	}

}
