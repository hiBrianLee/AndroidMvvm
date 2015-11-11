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

import com.hibrianlee.mvvmapp.inject.AppContextModule;
import com.hibrianlee.sample.mvvm.viewmodel.MockViewModelFactory;

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
    }
}
