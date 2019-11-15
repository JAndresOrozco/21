package com.example.design;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultadoUsuario extends RecyclerView.Adapter<ResultadoUsuario.ViewHolderCountries>{

    ArrayList<Usuario> listUsers;

    public ResultadoUsuario(ArrayList<Usuario> listUsers) {
        this.listUsers = listUsers;
    }

    @NonNull
    @Override
    public ViewHolderCountries onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewP = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new ViewHolderCountries(viewP);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCountries holder, int position) {
        holder.nameUser.setText(listUsers.get(position).getNombre());
        holder.numberUser.setText(String.valueOf(listUsers.get(position).getNumero()));
        holder.photoUser.setImageResource(R.drawable.user);
    }


    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class ViewHolderCountries extends RecyclerView.ViewHolder {

        TextView numberUser, nameUser;
        ImageView photoUser;

        public ViewHolderCountries(@NonNull View itemView) {
            super(itemView);
            nameUser = itemView.findViewById(R.id.tv_example);
            numberUser = itemView.findViewById(R.id.number_example);
            photoUser = itemView.findViewById(R.id.iv_example);

        }
    }
}
