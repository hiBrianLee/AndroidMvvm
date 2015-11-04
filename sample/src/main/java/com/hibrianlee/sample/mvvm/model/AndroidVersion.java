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

package com.hibrianlee.sample.mvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AndroidVersion implements Parcelable {

    private final String codeName;
    private final String version;
    private boolean selected;

    public AndroidVersion(String codeName, String version) {
        this.codeName = codeName;
        this.version = version;
    }

    public AndroidVersion(Parcel in) {
        codeName = in.readString();
        version = in.readString();
        selected = in.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codeName);
        dest.writeString(version);
        dest.writeInt(selected ? 1 : 0);
    }

    public String getCodeName() {
        return codeName;
    }

    public String getVersion() {
        return version;
    }

    public boolean isSelected() {
        return selected;
    }

    public void toggleSelected() {
        selected = !selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AndroidVersion that = (AndroidVersion) o;

        if (selected != that.selected) return false;
        if (codeName != null ? !codeName.equals(that.codeName) : that.codeName != null)
            return false;
        return !(version != null ? !version.equals(that.version) : that.version != null);

    }

    @Override
    public int hashCode() {
        int result = codeName != null ? codeName.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (selected ? 1 : 0);
        return result;
    }

    public static Creator<AndroidVersion> CREATOR = new Creator<AndroidVersion>() {
        @Override
        public AndroidVersion createFromParcel(Parcel source) {
            return new AndroidVersion(source);
        }

        @Override
        public AndroidVersion[] newArray(int size) {
            return new AndroidVersion[size];
        }
    };
}
