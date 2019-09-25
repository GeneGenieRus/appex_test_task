package project.service;

import project.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import project.repository.NoteRepo;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    NoteRepo noteRepo;

    public void save (Note note){
        noteRepo.save(note);
    }

    public void delete(int id){
        noteRepo.deleteById(id);
    }

    public Note get(int id){
        return noteRepo.getOne(id);
    }

    public List<Note> getAll(){
        return noteRepo.findAll();
    }
}
