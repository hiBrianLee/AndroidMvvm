/*
 * Copyright (C) 2015 Brian Lee (@hiBrianLee)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hibrianlee.sample.mvvm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hibrianlee.sample.mvvm.R;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AndroidVersionsAdapter
        extends RecyclerView.Adapter<AndroidVersionsAdapter.AndroidVersionViewHolder>
        implements View.OnClickListener {

    private final ArrayList<AndroidVersion> items = new ArrayList<>();

    @Override
    public AndroidVersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_android_version, parent, false);
        itemView.setOnClickListener(this);
        return new AndroidVersionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AndroidVersionViewHolder holder, int position) {
        AndroidVersion item = items.get(position);
        holder.itemView.setTag(position);
        holder.itemView.setSelected(item.isSelected());
        holder.version.setText(item.getVersion());
        holder.codeName.setText(item.getCodeName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<AndroidVersion> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public ArrayList<AndroidVersion> getItems() {
        return items;
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        AndroidVersion item = items.get(position);
        item.toggleSelected();
        notifyItemChanged(position);
    }

    static class AndroidVersionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.version)
        TextView version;

        @Bind(R.id.codeName)
        TextView codeName;

        public AndroidVersionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
