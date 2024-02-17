package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;

import com.example.samuraitravel.entity.Okiniiri;
import com.example.samuraitravel.repository.OkiniiriRepository;

import jakarta.transaction.Transactional;

@Service
public class OkiniiriService {
	private final OkiniiriRepository okiniiriRepository = null;
	
	@Transactional
	public Okiniiri saveOkiniiri(Okiniiri okiniiri) {
		return okiniiriRepository.save(okiniiri);
	}
}
