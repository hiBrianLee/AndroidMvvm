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

package com.hibrianlee.sample.mvvm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hibrianlee.sample.mvvm.R;
import com.hibrianlee.sample.mvvm.adapter.AndroidVersionsAdapter;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidVersionsFragment extends Fragment {

    private static final String EXTRA_VERSIONS = "versions";

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private AndroidVersionsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_android_versions, container, false);
        ButterKnife.bind(this, root);

        adapter = new AndroidVersionsAdapter();
        if (savedInstanceState != null) {
            ArrayList<AndroidVersion> versions =
                    savedInstanceState.getParcelableArrayList(EXTRA_VERSIONS);
            adapter.setItems(versions);
        } else {
            adapter.setItems(getVersions());
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_VERSIONS, adapter.getItems());
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

    @OnClick(R.id.hiBrianLee)
    void onClickHiBrianLee() {
        try {
            Intent intent = Intent.parseUri(getString(R.string.twitter_url), 0);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
