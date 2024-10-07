package com.regain.adminzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.regain.adminzn.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

