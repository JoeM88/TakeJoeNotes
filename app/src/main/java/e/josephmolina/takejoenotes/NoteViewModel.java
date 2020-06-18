package e.josephmolina.takejoenotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import e.josephmolina.takejoenotes.Database.Note;
import e.josephmolina.takejoenotes.Database.NoteRepository;

/**
 * Store and process data for UI and communicate with the model
 * Requests data from repository so activity/fragment can present it to screen
 * Forwards user interactions  from UI to repository
 * UI data in ViewModel is useful because they survive configuration changes
 *
 * You should never store context of an Activity or a view that references an activity
 * in the ViewModel because ViewModel is supposed to out-live an Activity after it is destroyed. But if hold
 * a ref to an already destroyed Activity we have a memory leak
 */
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
