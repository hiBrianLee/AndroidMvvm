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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

@Singleton
public class MockViewModelFactory implements ViewModelFactory {

    private final Map<Class<? extends ViewModel>, SpyInitializer<? extends ViewModel>>
            spyInitializerMap;

    private MainViewModel mainViewModel;
    private ClickCountViewModel clickCountViewModel;
    private AndroidVersionsViewModel androidVersionsViewModel;
    private AndroidVersionItemViewModel androidVersionItemViewModel;

    public interface SpyInitializer<ViewModelT extends ViewModel> {
        void setupSpy(ViewModelT viewModel);
    }

    @Inject
    MockViewModelFactory() {
        spyInitializerMap = new HashMap<>();
    }

    public void clear() {
        spyInitializerMap.clear();
        mainViewModel = null;
        clickCountViewModel = null;
        androidVersionsViewModel = null;
        androidVersionItemViewModel = null;
    }

    public <ViewModelT extends ViewModel> void registerSpyInitializer(
            Class<ViewModelT> viewModelClass, SpyInitializer<ViewModelT> spyInitializer) {
        spyInitializerMap.put(viewModelClass, spyInitializer);
    }

    @SuppressWarnings("unchecked")
    private <ViewModelT extends ViewModel> void setupSpy(Class<ViewModelT> viewModelClass,
                                                         ViewModelT viewModel) {
        SpyInitializer spyInitializer = spyInitializerMap.get(viewModelClass);
        if (spyInitializer != null) {
            spyInitializer.setupSpy(viewModel);
        }
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public ClickCountViewModel getClickCountViewModel() {
        return clickCountViewModel;
    }

    public AndroidVersionsViewModel getAndroidVersionsViewModel() {
        return androidVersionsViewModel;
    }

    public AndroidVersionItemViewModel getAndroidVersionItemViewModel() {
        return androidVersionItemViewModel;
    }

    @NonNull
    @Override
    public MainViewModel createMainViewModel(@NonNull ActivityComponent activityComponent,
                                             @Nullable ViewModel.State savedViewModelState) {
        mainViewModel = spy(new MainViewModel(activityComponent, savedViewModelState));
        doNothingOnLifeCycle(mainViewModel);
        setupSpy(MainViewModel.class, mainViewModel);
        return mainViewModel;
    }

    @NonNull
    @Override
    public ClickCountViewModel createClickCountViewModel(
            @NonNull ActivityComponent activityComponent,
            @Nullable ViewModel.State savedViewModelState) {
        clickCountViewModel = spy(new ClickCountViewModel(activityComponent, savedViewModelState));
        doNothingOnLifeCycle(clickCountViewModel);
        setupSpy(ClickCountViewModel.class, clickCountViewModel);
        return clickCountViewModel;
    }

    @NonNull
    @Override
    public AndroidVersionsViewModel createAndroidVersionsViewModel(
            @NonNull AndroidVersionsAdapter adapter,
            @NonNull ActivityComponent activityComponent,
            @Nullable ViewModel.State savedViewModelState) {
        androidVersionsViewModel = spy(new AndroidVersionsViewModel(adapter, activityComponent,
                savedViewModelState));
        doNothingOnLifeCycle(androidVersionsViewModel);
        setupSpy(AndroidVersionsViewModel.class, androidVersionsViewModel);
        return androidVersionsViewModel;
    }

    @NonNull
    @Override
    public AndroidVersionItemViewModel createAndroidVersionItemViewModel(
            @NonNull ActivityComponent activityComponent) {
        if (androidVersionItemViewModel == null) {
            androidVersionItemViewModel = spy(new AndroidVersionItemViewModel(activityComponent));
            doNothingOnLifeCycle(androidVersionItemViewModel);
            setupSpy(AndroidVersionItemViewModel.class, androidVersionItemViewModel);
        }
        return androidVersionItemViewModel;
    }

    private void doNothingOnLifeCycle(ViewModel viewModel) {
        doNothing().when(viewModel).onStart();
        doNothing().when(viewModel).onStop();
    }
}
