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

import com.hibrianlee.sample.mvvm.activity.AndroidVersionsActivity;
import com.hibrianlee.sample.mvvm.activity.ClickCountActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainViewModelTest extends Assert {

    private MainViewModel viewModel;
    private Activity activity;

    @Before
    public void setup() {
        activity = mock(Activity.class);
        viewModel = new MainViewModel(null);
    }

    @Test
    public void testOnClickButtonClicks() {
        viewModel.onClickButtonClicks(activity);
        verify(activity).startActivity(new Intent(activity, ClickCountActivity.class));
    }

    @Test
    public void testOnClickButtonRecyclerView() {
        viewModel.onClickButtonRecyclerView(activity);
        verify(activity).startActivity(new Intent(activity, AndroidVersionsActivity.class));
    }

    @Test
    public void testOnClickHiBrianLee() throws Exception {
        viewModel.onClickHiBrianLee(activity);
        verify(activity).startActivity(Intent.parseUri("https://www.twitter.com/hiBrianLee", 0));
    }
}
