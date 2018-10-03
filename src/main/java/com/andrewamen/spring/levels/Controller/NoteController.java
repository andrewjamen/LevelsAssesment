package com.andrewamen.spring.levels.Controller;

/**
 *
 * @author andrewamen
 */
import com.andrewamen.spring.levels.Model.Note;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    ArrayList<Note> notes = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    //get single note
    @GetMapping("/api/notes/{id}")
    public Note getNote(@PathVariable String id) {

        Note theNote = findNote(id);
        
        return theNote;
    }

    //get all notes
    @GetMapping("/api/notes")
    public ArrayList<Note> getAll() {
        return notes;
    }

    //post a new note
    @PostMapping("/api/notes")
    public Note postNote(@RequestBody Note newNote) {

        Note aNote = new Note(counter.incrementAndGet(), newNote.getBody());

        notes.add(aNote);

        return aNote;
    }

    //search for notes
    @RequestMapping(method = RequestMethod.GET, value = "/api/notes", params = "query")
    public ArrayList<Note> queryNote(@RequestParam String query) {

        Note aMatch;
        ArrayList<Note> matches = new ArrayList<>();

        for (Note aNote : notes) {

            if (aNote.getBody().contains(query)) {
                aMatch = new Note(aNote.getId(), aNote.getBody());
                matches.add(aMatch);
            }

        }

        return matches;
    }

    
    
    //update note
    @PutMapping("/api/notes")
    public Note replaceEmployee(@RequestBody Note newNote) {
        
        notes.set((int) newNote.getId() -1, newNote);

        return newNote;
    }

    
    //delete note
    @DeleteMapping("/api/notes")
    public String deleteNote(@RequestBody Note theNote) {
        
        
        notes.remove((int) theNote.getId() -1);
        
        return "Note with id=" + theNote.getId() + " has been removed\n";
    }
    

    
    //find a note by id
    public Note findNote(String id){
        
        Note theNote = new Note();
        Long idNum = Long.parseLong(id);
        
        for (Note aNote : notes) {

            if (aNote.getId() == idNum) {
                theNote = new Note(idNum, aNote.getBody());
            }

        }
        
       return theNote; 
    }

}
