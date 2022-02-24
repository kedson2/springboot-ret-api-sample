package com.CursosApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.CursosApi.Model.Produtos;


@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{

	

}
