package com.example.samuraitravel.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	List<Favorite> findByUserid(User user);


	
}
