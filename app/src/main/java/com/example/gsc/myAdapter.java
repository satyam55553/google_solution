package com.example.gsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    private RecyclerViewClickListener listener;

    private String[] data;

    public myAdapter(String[] data, RecyclerViewClickListener listener) {
        this.data = data;
        this.listener = listener;
    }
    public myAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String title = data[position];
        holder.txtTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    //ViewHolder Class
    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgIcon;
        TextView txtTitle;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.list_item_img);
            txtTitle = (TextView) itemView.findViewById(R.id.list_item_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

}
