package com.exmaple.android.recyclerviewwithimages;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    String data1[], data2[];
    int img[];
    Context context;

    public MyRecyclerViewAdapter(Context context, String[] data1, String[] data2, int[] img) {
        this.context = context;
        this.data1 = data1;
        this.data2 = data2;
        this.img = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.t1.setText(data1[i]);
        myViewHolder.t2.setText(data2[i]);
        myViewHolder.myImage.setImageResource(img[i]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t1;
        TextView t2;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textView1);
            t2 = itemView.findViewById(R.id.textView2);
            myImage = itemView.findViewById(R.id.imageView);
        }
    }
}
