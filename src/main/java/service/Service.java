package service;

import model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import repository.NoteRepo;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    NoteRepo noteRepo;

    public void create (Note note){
        noteRepo.save(note);
    }

    public void delete(int id){
        noteRepo.deleteById(id);
    }

    public void update(Note note){
        noteRepo.save(note);
    }

    public Note get(int id){
        return noteRepo.getOne(id);
    }

    public List<Note> getAll(){
        return noteRepo.findAll();
    }
}
