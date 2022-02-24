package com.CursosApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CursosApi.Model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	@Query(value = "select u from Usuario u where upper (trim(u.nome)) like %?1% ")
	List<Usuario> buscapornome(String nome);
	
	

}
