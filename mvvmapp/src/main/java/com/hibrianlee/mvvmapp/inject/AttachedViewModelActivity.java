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

package com.hibrianlee.mvvmapp.inject;

import android.app.Activity;
import android.content.Intent;

import com.hibrianlee.mvvmapp.activity.ViewModelActivity;

import java.lang.ref.WeakReference;
import java.net.URISyntaxException;

public final class AttachedViewModelActivity implements AttachedActivity {

    private final WeakReference<ViewModelActivity> weakActivity;

    AttachedViewModelActivity(ViewModelActivity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    public void startActivity(Class<? extends Activity> activityClass) {
        ViewModelActivity activity = weakActivity.get();
        if (activity != null && !activity.isFinishing()) {
            activity.startActivity(new Intent(activity, activityClass));
        }
    }

    @Override
    public void openUrl(String url) throws URISyntaxException {
        ViewModelActivity activity = weakActivity.get();
        if (activity != null && !activity.isFinishing()) {
            activity.startActivity(Intent.parseUri(url, 0));
        }
    }
}
