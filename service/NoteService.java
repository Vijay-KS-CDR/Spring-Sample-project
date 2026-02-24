    package com.example.Notes.service;
    import com.example.Notes.models.Note;
    import com.example.Notes.repository.NotesRepository;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class NoteService {

        private final NotesRepository notesrepo;

        public NoteService(NotesRepository notesRepository) {
            this.notesrepo = notesRepository;
        }

        public Note create(Note temp) {
            return notesrepo.save(temp);
        }

        public List<Note> getAll() {
            return notesrepo.findAll();
        }

        public Note getId(Long id) {
            return notesrepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        }

        public void deleteById(Long id) {
            Note deleter = notesrepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
            notesrepo.delete(deleter);
        }

        public void updateById(Long id,Note newer){
            Note note=notesrepo.findById(id).orElseThrow(()->new RuntimeException("Note not found"));
            note.setDescription(newer.getDescription());
            note.setTitle(newer.getTitle());
            notesrepo.save(note);
        }

        public void patchupdate(Long id,Note pather){
            Note exist= notesrepo.findById(id).orElseThrow(()->new RuntimeException("note not found"));
            if(pather.getDescription()!=null){
                exist.setDescription(pather.getDescription());
            }
            if(pather.getTitle()!=null){
                exist.setTitle(pather.getTitle());
            }
            notesrepo.save(exist);
        }


    }