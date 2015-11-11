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

import com.hibrianlee.mvvmapp.fragment.ViewModelFragment;
import com.hibrianlee.mvvmapp.inject.AppComponent;
import com.hibrianlee.sample.mvvm.SampleAppComponent;
import com.hibrianlee.sample.mvvm.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public abstract class BaseFragment extends ViewModelFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;

    @Override
    protected void inject(AppComponent appComponent) {
        ((SampleAppComponent) appComponent).inject(this);
    }
}
