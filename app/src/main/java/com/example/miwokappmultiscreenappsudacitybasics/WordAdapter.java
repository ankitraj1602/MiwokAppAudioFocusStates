package com.example.miwokappmultiscreenappsudacitybasics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private List<Word> wordList;
    private Context context;
    private int colorResourceId;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects,int colorResourceId) {
        super(context,R.layout.layout_single_item,objects);
        wordList = objects;
        this.context = context;
        this.colorResourceId = colorResourceId;
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public int getPosition(@Nullable Word item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private class Viewholder{
        private TextView defaultTxt;
        private TextView miwokTxt;
        private ImageView image;
        private LinearLayout linearLayout;

        private Viewholder(){
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder viewholder;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_single_item,parent,false);
            viewholder = new Viewholder();
            viewholder.defaultTxt = convertView.findViewById(R.id.txtEnglishNumber);
            viewholder.miwokTxt = convertView.findViewById(R.id.txtMiwokNumber);
            viewholder.image = convertView.findViewById(R.id.image);
            viewholder.linearLayout = convertView.findViewById(R.id.words_items_layout);
            convertView.setTag(viewholder);
        }
        else{
            viewholder = (Viewholder)convertView.getTag();
        }

        Word word = getItem(position);

        viewholder.defaultTxt.setText(word.getDefaultTranslation());
        viewholder.miwokTxt.setText(word.getMiwokTranslation());

        int color = ContextCompat.getColor(context,colorResourceId);
        viewholder.linearLayout.setBackgroundColor(color);

        if(word.hasImage()) {
            viewholder.image.setImageResource(word.getImageResource());
            viewholder.image.setVisibility(View.VISIBLE);
        }
        else{
            viewholder.image.setVisibility(View.GONE);
        }

        return convertView;
    }


}
