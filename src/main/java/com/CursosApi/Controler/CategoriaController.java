package com.CursosApi.Controler;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.CursosApi.Model.Categoria;
import com.CursosApi.Repository.CategoriaRepository;

@Controller
public class CategoriaController {
	@Autowired
	public CategoriaRepository categoriarepositorio;
	
	@PostMapping(value = "categoria")
	@ResponseBody
	public ResponseEntity<Categoria> Salavar(@RequestBody Categoria categoria){
		categoria= categoriarepositorio.save(categoria);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);	
		
	}
	
}
