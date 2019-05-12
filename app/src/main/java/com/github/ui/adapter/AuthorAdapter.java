package com.github.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.R;
import com.github.databinding.AdapterAuthorBinding;
import com.github.model.AuthorModel;
import com.github.utils.WebConstants;

import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {

    private List<AuthorModel> authors;
    private Context context;

    public AuthorAdapter(List<AuthorModel> authors) {
        this.authors = authors;
    }

    @NonNull
    @Override
    public AuthorAdapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new AuthorViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_author, viewGroup, false));
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder viewHolder, int position) {
        viewHolder.bind(authors.get(position));
        Glide.with(context).load(WebConstants.URL_PHOTO + authors.get(position).getAvatar())
                .into(viewHolder.binding.authorImage);
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        private AdapterAuthorBinding binding;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(AuthorModel author) {
            binding = DataBindingUtil.bind(itemView);
            binding.setAuthor(author);
        }
    }
}
