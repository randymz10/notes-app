package app.notes.backend.controller;

import app.notes.backend.model.Note;
import app.notes.backend.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("")
    public List<Note> getNotes() {
        return noteRepository.findByArchived(false);
    }

    @GetMapping("/archived")
    public List<Note> getNotesArchived() {
        return noteRepository.findByArchived(true);
    }

    @GetMapping("{id}")
    public Optional<Note> getOneNote(@PathVariable int id) {
        return noteRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("{id}")
    public Note updateNote(@PathVariable int id, @RequestBody Note note) {
        Note noteFromDb = noteRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        noteFromDb.setTitle(note.getTitle());
        noteFromDb.setDescription(note.getDescription());
        noteFromDb.setArchived(note.isArchived());

        return noteRepository.save(noteFromDb);
    }

    @PutMapping("/archived/{id}")
    public Note archivedNote(@PathVariable int id) {
        Note noteFromDb = noteRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        if (noteFromDb.isArchived()) {
            noteFromDb.setArchived(false);
        } else {
            noteFromDb.setArchived(true);
        }
        return noteRepository.save(noteFromDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        Note note = noteRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        noteRepository.delete(note);
    }
}
