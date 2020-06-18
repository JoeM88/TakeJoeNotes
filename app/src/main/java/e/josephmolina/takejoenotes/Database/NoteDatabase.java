package e.josephmolina.takejoenotes.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Database annotation takes two parameters.
 * 1. A list of entities, in this case we only have one
 * 2. The version # of the DB ( You increment it every single time you update the DB)
 */
@Database(entities = {Note.class}, version = 1)
public  abstract class NoteDatabase extends androidx.room.RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();

    /**
     *  To prevent two threads from accidentally creating the instance
     *  FallBackToDestructiveMigration destroys the DB each time we increment version so we can
     *  start with a new one. Otherwise we would see an IllegalState Exception.
     * @param context
     * @return NoteDatabase
     */
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>  {
        private NoteDao noteDao;
        public PopulateDbAsyncTask(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title1", "Description1", 1));
            noteDao.insert(new Note("Title2", "Description2", 2));
            noteDao.insert(new Note("Title3", "Description3", 3));
            return null;
        }
    }
}
