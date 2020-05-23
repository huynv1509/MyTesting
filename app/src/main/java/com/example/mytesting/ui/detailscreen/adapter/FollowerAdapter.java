package com.example.mytesting.ui.detailscreen.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytesting.data.model.User;
import com.example.mytesting.databinding.FollowerListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ViewHolder> {
    private List<User> mFollowerList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FollowerListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void addItems(List<User> followerList) {
        mFollowerList.clear();
        mFollowerList.addAll(followerList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFollowerList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mFollowerList.get(position);
        if (user != null)
            holder.onBind(user);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FollowerListItemBinding mViewBinding;

        ViewHolder(@NonNull FollowerListItemBinding itemView) {
            super(itemView.getRoot());
            mViewBinding = itemView;
        }

        void onBind(@NonNull User user) {
            if (!TextUtils.isEmpty(user.getAvatarUrl())) {
                Picasso.get().load(user.getAvatarUrl()).into(mViewBinding.avatar);
                mViewBinding.avatar.setVisibility(View.VISIBLE);
            } else {
                mViewBinding.avatar.setVisibility(View.GONE);
            }
            mViewBinding.usernameTv.setText(user.getUsername());
        }
    }
}
