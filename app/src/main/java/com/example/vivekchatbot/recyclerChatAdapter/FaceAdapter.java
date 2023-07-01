package com.example.vivekchatbot.recyclerChatAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.models.Face;

import java.util.ArrayList;

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceViewHoder> {

    ArrayList<Face> arrayList;
    private Context context;
    public FaceAdapter(ArrayList<Face> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public FaceAdapter.FaceViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.snapimage, parent, false);
       return new FaceViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FaceViewHoder holder, int position) {
           holder.imageView.setImageResource(arrayList.get(position).imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class FaceViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public FaceViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }


        }


