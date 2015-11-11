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

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hibrianlee.mvvmapp.adapter.RecyclerViewAdapter;
import com.hibrianlee.mvvmapp.inject.ActivityComponent;
import com.hibrianlee.sample.mvvm.R;
import com.hibrianlee.sample.mvvm.databinding.ItemAndroidVersionBinding;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;
import com.hibrianlee.sample.mvvm.viewmodel.AndroidVersionItemViewModel;
import com.hibrianlee.sample.mvvm.viewmodel.ViewModelFactory;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidVersionsAdapter
        extends RecyclerViewAdapter<AndroidVersion, AndroidVersionItemViewModel> {

    private final ViewModelFactory viewModelFactory;

    public AndroidVersionsAdapter(ViewModelFactory viewModelFactory,
                                  @NonNull ActivityComponent activityComponent) {
        super(activityComponent);
        this.viewModelFactory = viewModelFactory;
    }

    @Override
    public AndroidVersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_android_version, parent, false);

        AndroidVersionItemViewModel viewModel =
                viewModelFactory.createAndroidVersionItemViewModel(getActivityComponent());

        ItemAndroidVersionBinding binding = ItemAndroidVersionBinding.bind(itemView);
        binding.setViewModel(viewModel);

        return new AndroidVersionViewHolder(itemView, binding, viewModel);
    }

    public void setItems(ArrayList<AndroidVersion> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public ArrayList<AndroidVersion> getItems() {
        return items;
    }

    static class AndroidVersionViewHolder
            extends ItemViewHolder<AndroidVersion, AndroidVersionItemViewModel> {

        public AndroidVersionViewHolder(View itemView, ViewDataBinding binding,
                                        AndroidVersionItemViewModel viewModel) {
            super(itemView, binding, viewModel);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.versionItem)
        void onClickVersionItem() {
            viewModel.onClick();
        }
    }
}
