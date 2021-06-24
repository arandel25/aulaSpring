package com.felipe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipe.entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findByNomeContaining(String nome);
	
	@Query("from Usuario where nome like %:nome%")
	List<Usuario> pesquisarPorNome(String nome);
}