package com.example.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;
// T là entity, ID là kiểu dữ liệu của khoá chính.
public interface CategoryRepository extends JpaRepository<Category, Long>  {
	Optional<Category> findByName (String name);
	Page<Category> findByNameContaining(String name, Pageable pageable);
}
