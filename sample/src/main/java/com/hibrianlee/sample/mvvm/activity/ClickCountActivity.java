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

package com.hibrianlee.sample.mvvm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hibrianlee.sample.mvvm.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClickCountActivity extends AppCompatActivity {

    private static final String EXTRA_CLICKS = "clicks";

    @Bind(R.id.clickCountText)
    TextView clickCountText;

    private int clicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_count);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            clicks = savedInstanceState.getInt(EXTRA_CLICKS);
        }
        updateClicks();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_CLICKS, clicks);
    }

    @OnClick(R.id.clickButton)
    void onClickButton() {
        clicks++;
        updateClicks();
    }

    @OnClick(R.id.hiBrianLee)
    void onClickHiBrianLee() {
        try {
            Intent intent = Intent.parseUri(getString(R.string.twitter_url), 0);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateClicks() {
        clickCountText.setText(String.format(getString(R.string.click_count), clicks));
    }
}
