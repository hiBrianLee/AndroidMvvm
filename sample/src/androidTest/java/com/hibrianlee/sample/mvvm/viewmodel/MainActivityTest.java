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

import android.support.test.rule.ActivityTestRule;

import com.hibrianlee.sample.mvvm.BaseTest;
import com.hibrianlee.sample.mvvm.R;
import com.hibrianlee.sample.mvvm.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class, true, false);

    @Test
    public void testClickButtonClicks() {
        activityTestRule.launchActivity(null);
        MainViewModel mainViewModel = viewModelFactory.getMainViewModel();
        onView(withId(R.id.buttonClicks)).perform(click());

        verify(mainViewModel).onClickButtonClicks();
    }

    @Test
    public void testClickRecyclerView() {
        activityTestRule.launchActivity(null);
        MainViewModel mainViewModel = viewModelFactory.getMainViewModel();
        onView(withId(R.id.buttonRecyclerView)).perform(click());

        verify(mainViewModel).onClickButtonRecyclerView();
    }

    @Test
    public void testClickHiBrianLee() {
        activityTestRule.launchActivity(null);
        MainViewModel mainViewModel = viewModelFactory.getMainViewModel();
        onView(withId(R.id.hiBrianLee)).perform(click());

        verify(mainViewModel).onClickHiBrianLee();
    }
}
