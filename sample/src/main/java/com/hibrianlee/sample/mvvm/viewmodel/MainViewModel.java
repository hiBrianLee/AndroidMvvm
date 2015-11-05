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

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.hibrianlee.mvvmapp.viewmodel.ViewModel;
import com.hibrianlee.sample.mvvm.R;
import com.hibrianlee.sample.mvvm.activity.AndroidVersionsActivity;
import com.hibrianlee.sample.mvvm.activity.ClickCountActivity;

public class MainViewModel extends ViewModel {

    public MainViewModel(@Nullable State savedInstanceState) {
        super(savedInstanceState);
    }

    public void onClickButtonClicks(Activity activity) {
        activity.startActivity(new Intent(activity, ClickCountActivity.class));
    }

    public void onClickButtonRecyclerView(Activity activity) {
        activity.startActivity(new Intent(activity, AndroidVersionsActivity.class));
    }

    public void onClickHiBrianLee(Activity activity) {
        try {
            Intent intent = Intent.parseUri(activity.getString(R.string.twitter_url), 0);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

