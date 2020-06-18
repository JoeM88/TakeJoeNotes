package e.josephmolina.takejoenotes.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * This interface contains all of our database operations
 * Room takes care of the implementation in the backend for the operations
 */
@Dao
interface NoteDao {

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);

    // For custom operations we use @Query operation
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    /**
     * Using LiveData allows this to be observed and updated immediately.
     * It will also be persisted throughout lifecycle changes
     * Room takes care of updated value
     */
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
