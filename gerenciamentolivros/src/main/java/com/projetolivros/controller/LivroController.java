package com.projetolivros.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.projetolivros.entities.Livro;
import com.projetolivros.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "livro", description = "API REST DE GERECIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/livro")
public class LivroController {

	private final LivroService livroService;

	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um livro por ID")
	public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
		Livro Livro = livroService.getLivroById(id);
		if (Livro != null) {
			return ResponseEntity.ok(Livro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os livros")
	public ResponseEntity<List<Livro>> getAllLivro() {
		List<Livro> Livro = livroService.getAllLivro();
		return ResponseEntity.ok(Livro);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um livro")
	public ResponseEntity<Livro> criarLivro(@RequestBody @Valid Livro Livro) {
		Livro criarLivro = livroService.salvarLivro(Livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarLivro);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um livro")
	public ResponseEntity<Livro> updateCliente(@PathVariable Long id, @RequestBody @Valid Livro livro) {
		Livro updatedLivro = livroService.updateLivro(id, livro);
		if (updatedLivro != null) {
			return ResponseEntity.ok(updatedLivro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um livro")
	public ResponseEntity<String> deleteLivro(@PathVariable Long id) {
		boolean deleted = livroService.deleteLivro(id);
		if (deleted) {
			return ResponseEntity.ok().body("O cliente foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
