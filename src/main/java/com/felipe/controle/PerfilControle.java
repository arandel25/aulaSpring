package com.felipe.controle;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.entidade.Perfil;
import com.felipe.repositorio.PerfilRepositorio;

@RestController
@RequestMapping("/perfil")
public class PerfilControle {

	@Autowired
	PerfilRepositorio perfilRepositorio;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Perfil salvar(@RequestBody Perfil perfil) {
		return perfilRepositorio.save(perfil);
	}
	
	@GetMapping("/todos")
	public List<Perfil> pesquisarTodo() {
		return perfilRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Perfil> pesquisarPorId(@PathVariable Long id) {
		Optional<Perfil> perf = perfilRepositorio.findById(id);
		if (perf.isPresent()) {
			return ResponseEntity.ok(perf.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/pesquisarPorNome/{nome}")
	public List<Perfil> pesquisarPorNome(@PathVariable String nome){
		return perfilRepositorio.findByNomeContaining(nome);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @RequestBody Perfil perfil) {
		if (perfilRepositorio.existsById(id)) {
			perfil.setId(id);
			perfilRepositorio.save(perfil);
			return ResponseEntity.ok(perfil);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Perfil> excluir(@PathVariable Long id) {

		if (!perfilRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		perfilRepositorio.deleteById(id);;
		return ResponseEntity.noContent().build();
	}
	
}