package com.example.mybookstor.repositories;

import com.example.mybookstor.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
