package com.masum.mcc;

/**
 * Created by masum on 1/18/2018.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;




public class CustomizedAdapter  extends RecyclerView.Adapter<CustomizedAdapter.CustomViewHolder> {
    private Context context;
    List<Employee> employeeList;


    public CustomizedAdapter(Context context,List<Employee> employeeList){

        this.context=context;
        this.employeeList=employeeList;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        Context context;
        List<Employee> employeeList;
        TextView txtName;
        TextView txtEmail;
        TextView txtPhone;
        TextView txtAge;
        ImageView imgView;

        public CustomViewHolder(View itemView, Context context, List<Employee> employeeList) {
            super(itemView);
            this.context=context;
            this.employeeList=employeeList;
            txtName=(TextView)itemView.findViewById(R.id.textName);
            txtEmail=(TextView)itemView.findViewById(R.id.textEmail);
            txtPhone=(TextView)itemView.findViewById(R.id.textPhone);
            txtAge=(TextView)itemView.findViewById(R.id.textAge);
            imgView= (ImageView) itemView.findViewById(R.id.imageList);

        }


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.card_layout,null);
        CustomViewHolder viewHolder=new CustomViewHolder(view,context,employeeList);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Employee e=employeeList.get(position);
        holder.txtName.setText(e.getName());
        holder.txtEmail.setText(e.getEmail());
        holder.txtPhone.setText(e.getPhone());
        holder.txtAge.setText(e.getAge());
        byte[] image=e.getImage();
        Bitmap bmp= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imgView.setImageBitmap(bmp);

    }


    @Override
    public int getItemCount() {
        return employeeList.size();
    }



}


