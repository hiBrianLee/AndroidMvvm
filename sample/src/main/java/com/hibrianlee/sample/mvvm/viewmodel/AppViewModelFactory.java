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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hibrianlee.mvvmapp.inject.ActivityComponent;
import com.hibrianlee.mvvmapp.viewmodel.ViewModel;
import com.hibrianlee.sample.mvvm.adapter.AndroidVersionsAdapter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppViewModelFactory implements ViewModelFactory {

    @Inject
    AppViewModelFactory() {

    }

    @NonNull
    @Override
    public MainViewModel createMainViewModel(@NonNull ActivityComponent activityComponent,
                                             @Nullable ViewModel.State savedViewModelState) {
        return new MainViewModel(activityComponent, savedViewModelState);
    }

    @NonNull
    @Override
    public ClickCountViewModel createClickCountViewModel(
            @NonNull ActivityComponent activityComponent,
            @Nullable ViewModel.State savedViewModelState) {
        return new ClickCountViewModel(activityComponent, savedViewModelState);
    }

    @NonNull
    @Override
    public AndroidVersionsViewModel createAndroidVersionsViewModel(
            @NonNull AndroidVersionsAdapter adapter,
            @NonNull ActivityComponent activityComponent,
            @Nullable ViewModel.State savedViewModelState) {
        return new AndroidVersionsViewModel(adapter, activityComponent, savedViewModelState);
    }

    @NonNull
    @Override
    public AndroidVersionItemViewModel createAndroidVersionItemViewModel(
            @NonNull ActivityComponent activityComponent) {
        return new AndroidVersionItemViewModel(activityComponent);
    }
}
