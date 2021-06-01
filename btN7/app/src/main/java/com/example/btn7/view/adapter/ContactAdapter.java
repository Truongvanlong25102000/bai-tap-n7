package com.example.btn7.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btn7.R;
import com.example.btn7.model.Contact;
import com.example.btn7.view.ItemSelected;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Contact> contacts=new ArrayList<>();
    private ItemSelected itemSelected;

    public ContactAdapter(Context context, ArrayList<Contact> contacts,ItemSelected itemSelected) {
        this.contacts=contacts;
        this.context = context;
        this.itemSelected=itemSelected;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_contact,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(contacts.get(position).getName());
        if(position%2==0){
            holder.imgContact.setImageResource(R.drawable.ic_launcher2);
        }

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelected.itemSelectedInRecyclerView(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (contacts!=null)?contacts.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView imgContact;
        private LinearLayout rootLayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            imgContact=itemView.findViewById(R.id.imgContact);
            rootLayout=itemView.findViewById(R.id.rootLayout);
        }
    }
}
