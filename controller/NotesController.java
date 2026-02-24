package com.example.Notes.controller;

import com.example.Notes.models.Note;
import com.example.Notes.repository.NotesRepository;
import com.example.Notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    NoteService noteService;

    public NotesController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<Note> create(@RequestBody Note note){
        return ResponseEntity.status(201).body(noteService.create(note));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        return ResponseEntity.ok(noteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id ){
        Note lst=noteService.getId(id);
        return ResponseEntity.status(200).body(lst);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id){
        noteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable Long id,@RequestBody Note note){
        noteService.updateById(id,note);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Void> pathupdate(@PathVariable Long id,@RequestBody Note pather){
        noteService.patchupdate(id,pather);
        return ResponseEntity.noContent().build();
    }
}
