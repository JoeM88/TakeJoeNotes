package e.josephmolina.takejoenotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import e.josephmolina.takejoenotes.Database.Note;

// YOu need to specify the type of data that the adapter will hold
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    List<Note> notes = new ArrayList<>();

    /**
     * This is where we create the ViewHolder object
     * The parent is the RecyclerView itself and since it is from out Activity
     * we can get access to the context
     *
     * From here this is how we are able to find the views from our layout since here we inflate it
     * And then this gets passed to our viewholder class
     */
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(layoutView);
    }

    // This is where we set the values of the view
    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNode = notes.get(position);

        holder.title.setText(currentNode.getTitle());
        holder.description.setText(currentNode.getDescription());
        holder.priority.setText(String.valueOf(currentNode.getPriority()));
    }

    // This determines how many items we want to display in our recyclerview
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    // This holds the views of each list item.
    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView priority;
        private TextView description;

        // We are going to be using this in onBindViewHolder
        public NoteHolder(@NonNull View layoutView) {
            super(layoutView);
            title = layoutView.findViewById(R.id.title);
            priority = layoutView.findViewById(R.id.priority);
            description = layoutView.findViewById(R.id.description);
        }
    }
}
