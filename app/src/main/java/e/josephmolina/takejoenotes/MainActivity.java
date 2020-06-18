package e.josephmolina.takejoenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import e.josephmolina.takejoenotes.Database.Note;

/**
 *=Live Data is lifecycle aware. It will only update activity is in foreground
 * If activity is destroyed it will also clean up reference to activity - yo avoid Memory leaks
 */
public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        // every recyclerView view needs a layout manager - here we chose a linear layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // if we know the size this improves performance
        recyclerView.setHasFixedSize(true);
        final NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // Triggered each time the LiveData in our LiveData object changes
                noteAdapter.setNotes(notes);
            }
        });
    }
}
