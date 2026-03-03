package com.example.Notes.service;

import com.example.Notes.models.Note;
import com.example.Notes.models.User;
import com.example.Notes.repository.NotesRepository;
import com.example.Notes.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User temp) {
        return userRepo.save(temp);
    }

    public User getId(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
