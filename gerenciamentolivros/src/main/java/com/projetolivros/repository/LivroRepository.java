package com.projetolivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetolivros.entities.Livro;

public interface LivroRepository  extends JpaRepository<Livro, Long>{

}
