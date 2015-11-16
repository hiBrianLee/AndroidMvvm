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

import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.hibrianlee.mvvmapp.inject.ActivityComponent;
import com.hibrianlee.mvvmapp.viewmodel.ItemViewModel;
import com.hibrianlee.sample.mvvm.BR;
import com.hibrianlee.sample.mvvm.model.AndroidVersion;

public class AndroidVersionItemViewModel extends ItemViewModel<AndroidVersion> {

    private AndroidVersion androidVersion;

    AndroidVersionItemViewModel(@NonNull ActivityComponent activityComponent) {
        super(activityComponent);
    }

    @Override
    public void setItem(AndroidVersion item) {
        androidVersion = item;
        notifyChange();
    }

    public void onClick() {
        androidVersion.toggleSelected();
        notifyPropertyChanged(BR.selected);
    }

    @Bindable
    public String getVersion() {
        return androidVersion.getVersion();
    }

    @Bindable
    public String getCodeName() {
        return androidVersion.getCodeName();
    }

    @Bindable
    public boolean getSelected() {
        return androidVersion.isSelected();
    }
}
