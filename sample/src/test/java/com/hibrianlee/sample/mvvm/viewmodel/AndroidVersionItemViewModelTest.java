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

import com.hibrianlee.mvvmapp.viewmodel.ViewModel;
import com.hibrianlee.sample.mvvm.BR;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AndroidVersionItemViewModelTest extends ViewModelTest<AndroidVersionItemViewModel> {

    private AndroidVersion androidVersion;

    @Override
    protected AndroidVersionItemViewModel createViewModel(ViewModel.State savedInstanceState) {
        return new AndroidVersionItemViewModel(testComponent);
    }

    @Test
    public void testViewModel() {
        androidVersion = mock(AndroidVersion.class);
        when(androidVersion.getVersion()).thenReturn("Version");
        when(androidVersion.getCodeName()).thenReturn("Code Name");
        when(androidVersion.isSelected()).thenReturn(true);
        viewModel.setItem(androidVersion);

        verifyChanged();
        assertEquals("Version", viewModel.getVersion());
        assertEquals("Code Name", viewModel.getCodeName());
        assertTrue(viewModel.getSelected());
    }

    @Test
    public void testVersion() {
        androidVersion = mock(AndroidVersion.class);
        when(androidVersion.getVersion()).thenReturn("Version");
        viewModel.setItem(androidVersion);

        assertEquals("Version", viewModel.getVersion());

        when(androidVersion.getVersion()).thenReturn("New Version");
        assertEquals("New Version", viewModel.getVersion());
    }

    @Test
    public void testCodeName() {
        androidVersion = mock(AndroidVersion.class);
        when(androidVersion.getCodeName()).thenReturn("Code Name");
        viewModel.setItem(androidVersion);

        assertEquals("Code Name", viewModel.getCodeName());

        when(androidVersion.getCodeName()).thenReturn("New Code Name");
        assertEquals("New Code Name", viewModel.getCodeName());
    }

    @Test
    public void testSelected() {
        androidVersion = mock(AndroidVersion.class);
        when(androidVersion.isSelected()).thenReturn(false);
        viewModel.setItem(androidVersion);

        assertFalse(viewModel.getSelected());

        when(androidVersion.isSelected()).thenReturn(true);
        assertTrue(viewModel.getSelected());
    }

    @Test
    public void testOnClick() {
        androidVersion = mock(AndroidVersion.class);
        viewModel.setItem(androidVersion);
        viewModel.onClick();

        verify(androidVersion).toggleSelected();
        verifyPropertyChanged(BR.selected);
    }
}
