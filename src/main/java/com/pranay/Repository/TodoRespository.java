package com.pranay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranay.model.Todo;

public interface TodoRespository extends JpaRepository<Todo, Integer> {}

