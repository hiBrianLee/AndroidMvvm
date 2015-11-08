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

import android.databinding.Observable;

import com.hibrianlee.mvvmapp.viewmodel.ViewModel;
import com.hibrianlee.sample.mvvm.BaseTest;

import org.mockito.verification.VerificationMode;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class ViewModelTest<ViewModelT extends ViewModel>
        extends BaseTest {

    protected ViewModelT viewModel;
    protected Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    private ViewModel.State savedInstanceState;

    protected abstract ViewModelT createViewModel(ViewModel.State savedInstanceState);

    @Override
    public void setup() {
        super.setup();
        savedInstanceState = null;
        onPropertyChangedCallback = mock(Observable.OnPropertyChangedCallback.class);
        viewModel = createViewModel(null);
        viewModel.addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    protected final void verifyChanged() {
        verify(onPropertyChangedCallback)
                .onPropertyChanged(any(Observable.class), eq(0));
    }

    protected final void verifyPropertyChanged(int propertyId) {
        verify(onPropertyChangedCallback)
                .onPropertyChanged(any(Observable.class), eq(propertyId));
    }

    protected final void verifyPropertyChanged(int propertyId, VerificationMode verificationMode) {
        verify(onPropertyChangedCallback, verificationMode)
                .onPropertyChanged(any(Observable.class), eq(propertyId));
    }

    protected final void rotateDestroy() {
        savedInstanceState = viewModel.getInstanceState();
        viewModel.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        viewModel.onStop();
    }

    protected final void rotateCreate() {
        onPropertyChangedCallback = mock(Observable.OnPropertyChangedCallback.class);
        viewModel = createViewModel(savedInstanceState);
        viewModel.addOnPropertyChangedCallback(onPropertyChangedCallback);
        viewModel.onStart();
        savedInstanceState = null;
    }
}
