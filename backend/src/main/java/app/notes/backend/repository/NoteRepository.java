package app.notes.backend.repository;

import app.notes.backend.model.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
    public List<Note> findByArchived(boolean archived);
}
