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
import com.hibrianlee.sample.mvvm.activity.AndroidVersionsActivity;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class AndroidVersionsActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<AndroidVersionsActivity> activityTestRule = new ActivityTestRule<>(
            AndroidVersionsActivity.class, true, false);

    @Override
    public void setUp() throws Exception {
        super.setUp();
        AndroidVersionItemInitializer initializer = new AndroidVersionItemInitializer();
        initializer.version = "version";
        initializer.codeName = "codeName";
        initializer.selected = true;
        viewModelFactory.registerSpyInitializer(AndroidVersionItemViewModel.class, initializer);
        viewModelFactory.registerSpyInitializer(AndroidVersionsViewModel.class,
                new AndroidVersionsInitializer());
    }

    @Test
    public void testInitialScreen() {
        activityTestRule.launchActivity(null);

        AndroidVersionItemViewModel viewModel = viewModelFactory.getAndroidVersionItemViewModel();
        verify(viewModel, atLeastOnce()).setItem(any(AndroidVersion.class));
        onView(withRecyclerViewPosition(0))
                .check(matches(allOf(
                        hasDescendant(withText("version")),
                        hasDescendant(withText("codeName")),
                        isSelected()
                )));
    }

    @Test
    public void testItemClick() {
        activityTestRule.launchActivity(null);

        AndroidVersionItemViewModel viewModel = viewModelFactory.getAndroidVersionItemViewModel();
        onView(withRecyclerViewPosition(0)).perform(click());
        verify(viewModel).onClick();
    }

    @Test
    public void testClickHiBrianLee() {
        activityTestRule.launchActivity(null);
        AndroidVersionsViewModel viewModel = viewModelFactory.getAndroidVersionsViewModel();
        onView(withId(R.id.hiBrianLee)).perform(click());

        verify(viewModel).onClickHiBrianLee();
    }

    private static class AndroidVersionItemInitializer
            implements MockViewModelFactory.SpyInitializer<AndroidVersionItemViewModel> {

        private String version;
        private String codeName;
        private boolean selected;

        @Override
        public void setupSpy(AndroidVersionItemViewModel viewModel) {
            doReturn(version).when(viewModel).getVersion();
            doReturn(codeName).when(viewModel).getCodeName();
            doReturn(selected).when(viewModel).getSelected();
            doNothing().when(viewModel).onClick();
            doNothing().when(viewModel).setItem(any(AndroidVersion.class));
        }
    }

    private static class AndroidVersionsInitializer
            implements MockViewModelFactory.SpyInitializer<AndroidVersionsViewModel> {

        @Override
        public void setupSpy(AndroidVersionsViewModel viewModel) {
            doNothing().when(viewModel).onClickHiBrianLee();
        }
    }
}
