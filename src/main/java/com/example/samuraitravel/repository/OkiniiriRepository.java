package com.example.samuraitravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Okiniiri;

public interface OkiniiriRepository extends JpaRepository<Okiniiri, Integer> {
	public Okiniiri findByName(String name); 
}
