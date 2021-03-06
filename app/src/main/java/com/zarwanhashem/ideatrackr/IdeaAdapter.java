package com.zarwanhashem.ideatrackr;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

import static com.zarwanhashem.ideatrackr.MainActivity.CURR_IDEA_KEY;
import static com.zarwanhashem.ideatrackr.MainActivity.IDEA_ID_KEY;

/**
 * Used to provide the ListView of ideas in MainActivity
 */
public class IdeaAdapter extends ArrayAdapter<Button> {
    private static List<Idea> ideas;

    public IdeaAdapter(Context context, int resource, List<Button> ideaButtons, List<Idea> ideas) {
        super(context, resource, ideaButtons);
        IdeaAdapter.ideas = ideas;
    }

    @Override
    public View getView(final int POSITION, View convertView, final ViewGroup PARENT) {
        View view = super.getView(POSITION, convertView, PARENT);
        Button idea = (Button) view.findViewById(R.id.idea_button);
        idea.setText(ideas.get(POSITION).getTitle());

        idea.setOnClickListener(new View.OnClickListener() {

            //Clicking on ideas takes you to the edit idea page
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditIdeaPageActivity.class);
                String jsonIdea = new Gson().toJson(ideas.get(POSITION));
                intent.putExtra(CURR_IDEA_KEY, jsonIdea);
                intent.putExtra(IDEA_ID_KEY, POSITION);
                PARENT.getContext().startActivity(intent);
            }
        });
        return view;
    }
}
