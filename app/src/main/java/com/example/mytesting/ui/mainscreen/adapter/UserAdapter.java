package com.example.mytesting.ui.mainscreen.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytesting.data.model.User;
import com.example.mytesting.databinding.UserListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private UserAdapterListener mListener;
    private List<User> mUserList = new ArrayList<>();

    public UserAdapter(UserAdapterListener listener) {
        this.mListener = listener;
    }

    public void addItems(List<User> userList) {
        mUserList.clear();
        mUserList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUserList.get(position);
        if (user != null)
            holder.onBind(user);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private UserListItemBinding mViewBinding;

        ViewHolder(@NonNull UserListItemBinding itemView) {
            super(itemView.getRoot());
            mViewBinding = itemView;
            mViewBinding.getRoot().setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onViewUserDetail(getAdapterPosition(), mUserList.get(getAdapterPosition()).getUsername());
                }
            });
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

    public interface UserAdapterListener {
        void onViewUserDetail(int position, String username);
    }
}
