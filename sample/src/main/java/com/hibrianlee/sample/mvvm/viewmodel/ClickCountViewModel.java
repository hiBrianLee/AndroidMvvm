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
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hibrianlee.mvvmapp.inject.ActivityComponent;
import com.hibrianlee.mvvmapp.viewmodel.ViewModel;
import com.hibrianlee.sample.mvvm.BR;
import com.hibrianlee.sample.mvvm.R;

public class ClickCountViewModel extends ViewModel {

    int clicks;

    public ClickCountViewModel(@NonNull ActivityComponent activityComponent,
                               @Nullable State savedInstanceState) {
        super(activityComponent, savedInstanceState);
        if (savedInstanceState instanceof ClickCountState) {
            clicks = ((ClickCountState) savedInstanceState).clicks;
        }
    }

    @Override
    public State getInstanceState() {
        return new ClickCountState(this);
    }

    @Bindable
    public String getClickCountText() {
        return String.format(appContext.getString(R.string.click_count), clicks);
    }

    public void onClickButton() {
        clicks++;
        notifyPropertyChanged(BR.clickCountText);
    }

    public void onClickHiBrianLee() {
        try {
            attachedActivity.openUrl(appContext.getString(R.string.twitter_url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClickCountState extends State {

        private final int clicks;

        protected ClickCountState(ClickCountViewModel viewModel) {
            super(viewModel);
            clicks = viewModel.clicks;
        }

        public ClickCountState(Parcel in) {
            super(in);
            clicks = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(clicks);
        }

        public static Creator<ClickCountState> CREATOR = new Creator<ClickCountState>() {
            @Override
            public ClickCountState createFromParcel(Parcel source) {
                return new ClickCountState(source);
            }

            @Override
            public ClickCountState[] newArray(int size) {
                return new ClickCountState[size];
            }
        };
    }
}

