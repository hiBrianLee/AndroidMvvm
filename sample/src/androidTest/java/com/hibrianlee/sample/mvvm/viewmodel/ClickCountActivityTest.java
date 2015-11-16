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
import com.hibrianlee.sample.mvvm.activity.ClickCountActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ClickCountActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<ClickCountActivity> activityTestRule = new ActivityTestRule<>(
            ClickCountActivity.class, true, false);

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ClickCountSpyInitializer initializer = new ClickCountSpyInitializer();
        initializer.clickCountText = "5 clicks";
        viewModelFactory.registerSpyInitializer(ClickCountViewModel.class, initializer);
    }

    @Test
    public void testInitialScreen() {
        activityTestRule.launchActivity(null);
        onView(withId(R.id.clickCountText)).check(matches(withText("5 clicks")));
    }

    @Test
    public void testClickButton() {
        activityTestRule.launchActivity(null);
        ClickCountViewModel viewModel = viewModelFactory.getClickCountViewModel();
        onView(withId(R.id.clickButton)).perform(click());

        verify(viewModel).onClickButton();
    }

    @Test
    public void testClickHiBrianLee() {
        activityTestRule.launchActivity(null);
        ClickCountViewModel viewModel = viewModelFactory.getClickCountViewModel();
        onView(withId(R.id.hiBrianLee)).perform(click());

        verify(viewModel).onClickHiBrianLee();
    }

    private static class ClickCountSpyInitializer
            implements MockViewModelFactory.SpyInitializer<ClickCountViewModel> {

        private String clickCountText;

        @Override
        public void setupSpy(ClickCountViewModel viewModel) {
            doReturn(clickCountText).when(viewModel).getClickCountText();
            doNothing().when(viewModel).onClickButton();
            doNothing().when(viewModel).onClickHiBrianLee();
        }
    }
}
