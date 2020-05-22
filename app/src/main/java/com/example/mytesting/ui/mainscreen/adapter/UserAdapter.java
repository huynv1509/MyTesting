package com.example.mytesting.ui.mainscreen.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytesting.data.model.User;

public class UserAdapter {

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void onBind(@NonNull User user) {}
    }

    public interface UserAdapterListener {
        void onViewUserDetail(int position, String username);
    }
}
