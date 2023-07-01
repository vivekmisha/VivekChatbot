package com.example.vivekchatbot.recyclerChatAdapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vivekchatbot.FullImageActivity;
import com.example.vivekchatbot.R;
import com.example.vivekchatbot.ui.gallery.SnapFragment;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private List<File> imagePaths;
    private Context context;
    public GalleryAdapter(List<File> imagePaths, Context context) {
        this.imagePaths = imagePaths;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.snapimage, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        File imageFile = imagePaths.get(position);
        Log.d("TAG", "onBindViewHolder: " +imageFile);
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        holder.imageView.setImageBitmap(bitmap);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.putExtra("imagePath", imageFile.getAbsolutePath());
                context.startActivity(intent);

            }
        });

//  ........................................set dialog.............................................................

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openDialog();
                return false;
            }
            private void openDialog() {
               Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.custom_dialog);
                dialog.show();
                dialog.findViewById(R.id.btndelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imagePaths.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();;
                        boolean isDeleted = imageFile.delete();
                        if (isDeleted){
                            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.findViewById(R.id.btncancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {

        return imagePaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
