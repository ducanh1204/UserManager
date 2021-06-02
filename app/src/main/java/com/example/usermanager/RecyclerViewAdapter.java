package com.example.usermanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<User> userList;
    private Context context;

    public RecyclerViewAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        if(userList.size()>0) {
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(userList.get(position).getPhoto(), 0, userList.get(position).getPhoto().length);
                if(bitmap==null){
                    Toast.makeText(context, "Không thể tải hình ảnh", Toast.LENGTH_SHORT).show();
                }
                holder.img.setImageBitmap(bitmap);
            }catch (Exception e){
                Toast.makeText(context, "Không thể tải hình ảnh", Toast.LENGTH_SHORT).show();
            }
            holder.tvUserName.setText("Username: "+userList.get(position).getUserName());
            holder.tvPassword.setText("Password: "+userList.get(position).getPassword());
            holder.tvFullName.setText("FullName: "+userList.get(position).getFullName());
            holder.tvGender.setText("Gender: "+userList.get(position).getGender());
            holder.tvBirthday.setText("Birthday: "+userList.get(position).getBirthday());
            holder.tvAddress.setText("Address: "+userList.get(position).getAddress());
            holder.tvCCCD.setText("CCCD: "+userList.get(position).getCccd());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvUserName,tvPassword,tvFullName,tvGender,tvBirthday,tvAddress,tvCCCD;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            img  = itemView.findViewById(R.id.img);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvPassword = itemView.findViewById(R.id.tvPassword);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvBirthday = itemView.findViewById(R.id.tvBirthday);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvCCCD = itemView.findViewById(R.id.tvCCCD);
        }
    }
}
