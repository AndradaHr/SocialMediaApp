package com.app.project.dao;

import com.app.project.model.CR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRDAO extends JpaRepository<CR, Long> {
}
