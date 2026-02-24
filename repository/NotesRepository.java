package com.example.Notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Notes.models.*;

public interface NotesRepository extends JpaRepository<Note,Long>{

}