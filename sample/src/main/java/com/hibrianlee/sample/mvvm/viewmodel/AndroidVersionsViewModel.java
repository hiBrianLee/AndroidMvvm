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

package com.hibrianlee.sample.mvvm.viewmodel;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hibrianlee.mvvmapp.adapter.RecyclerViewAdapter;
import com.hibrianlee.mvvmapp.inject.ActivityComponent;
import com.hibrianlee.mvvmapp.viewmodel.RecyclerViewViewModel;
import com.hibrianlee.sample.mvvm.R;
import com.hibrianlee.sample.mvvm.adapter.AndroidVersionsAdapter;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;

import java.util.ArrayList;

public class AndroidVersionsViewModel extends RecyclerViewViewModel {

    AndroidVersionsAdapter adapter;

    AndroidVersionsViewModel(@NonNull AndroidVersionsAdapter adapter,
                             @NonNull ActivityComponent activityComponent,
                             @Nullable State savedInstanceState) {
        super(activityComponent, savedInstanceState);

        ArrayList<AndroidVersion> versions;
        if (savedInstanceState instanceof AndroidVersionsState) {
            versions = ((AndroidVersionsState) savedInstanceState).versions;
        } else {
            versions = getVersions();
        }
        this.adapter = adapter;
        adapter.setItems(versions);
    }

    @Override
    protected RecyclerViewAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(appContext);
    }

    @Override
    public RecyclerViewViewModelState getInstanceState() {
        return new AndroidVersionsState(this);
    }

    public void onClickHiBrianLee() {
        try {
            attachedActivity.openUrl(appContext.getString(R.string.twitter_url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<AndroidVersion> getVersions() {
        ArrayList<AndroidVersion> versions = new ArrayList<>();
        versions.add(new AndroidVersion("Cupcake", "Android 1.5"));
        versions.add(new AndroidVersion("Donut", "Android 1.6"));
        versions.add(new AndroidVersion("Eclair", "Android 2.0-2.1"));
        versions.add(new AndroidVersion("Froyo", "Android 2.2"));
        versions.add(new AndroidVersion("Gingerbread", "Android 2.3"));
        versions.add(new AndroidVersion("Honeycomb", "Android 3.0-3.2"));
        versions.add(new AndroidVersion("Ice Cream Sandwich", "Android 4.0"));
        versions.add(new AndroidVersion("Jelly Bean", "Android 4.1-4.3"));
        versions.add(new AndroidVersion("KitKat", "Android 4.4"));
        versions.add(new AndroidVersion("Lollipop", "Android 5.0-5.1"));
        versions.add(new AndroidVersion("Marshmallow", "Android 6.0"));
        return versions;
    }

    private static class AndroidVersionsState extends RecyclerViewViewModelState {

        private final ArrayList<AndroidVersion> versions;

        public AndroidVersionsState(AndroidVersionsViewModel viewModel) {
            super(viewModel);
            versions = viewModel.adapter.getItems();
        }

        public AndroidVersionsState(Parcel in) {
            super(in);
            versions = in.createTypedArrayList(AndroidVersion.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeTypedList(versions);
        }

        public static Creator<AndroidVersionsState> CREATOR = new Creator<AndroidVersionsState>() {
            @Override
            public AndroidVersionsState createFromParcel(Parcel source) {
                return new AndroidVersionsState(source);
            }

            @Override
            public AndroidVersionsState[] newArray(int size) {
                return new AndroidVersionsState[size];
            }
        };
    }
}
