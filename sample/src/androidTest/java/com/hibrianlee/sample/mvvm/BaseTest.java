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

package com.hibrianlee.sample.mvvm;

import android.app.Instrumentation;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hibrianlee.mvvmapp.adapter.RecyclerViewAdapter;
import com.hibrianlee.mvvmapp.inject.AppContextModule;
import com.hibrianlee.sample.mvvm.viewmodel.MockViewModelFactory;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;

import javax.inject.Inject;

public class BaseTest {

    @Inject
    protected MockViewModelFactory viewModelFactory;

    @CallSuper
    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MvvmSampleApplication application = (MvvmSampleApplication) instrumentation
                .getTargetContext().getApplicationContext();
        TestComponent testComponent = DaggerTestComponent.builder()
                .appContextModule(new AppContextModule(application))
                .build();
        application.setAppComponent(testComponent);
        testComponent.inject(this);
        viewModelFactory.clear();
    }

    public static Matcher<View> withRecyclerViewPosition(final int position) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with RecyclerView position: " + position);
            }

            @Override
            protected boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof RecyclerView)) {
                    return false;
                }

                RecyclerView recyclerView = (RecyclerView) view.getParent();
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (!(adapter instanceof RecyclerViewAdapter)) {
                    return false;
                }
                return position == recyclerView.getChildAdapterPosition(view);
            }
        };
    }
}
