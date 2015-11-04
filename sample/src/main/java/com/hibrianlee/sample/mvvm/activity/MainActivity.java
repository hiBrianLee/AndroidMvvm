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

import com.hibrianlee.sample.mvvm.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonClicks)
    void onClickButtonClicks() {
        startActivity(new Intent(this, ClickCountActivity.class));
    }

    @OnClick(R.id.buttonRecyclerView)
    void onClickButtonRecyclerView() {
        startActivity(new Intent(this, AndroidVersionsActivity.class));
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
}
