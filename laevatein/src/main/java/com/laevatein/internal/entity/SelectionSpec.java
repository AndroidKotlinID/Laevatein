/*
 * Copyright (C) 2014 nohana, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.laevatein.internal.entity;

import com.laevatein.MimeType;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author KeithYokoma
 * @since 2014/03/20
 * @version 1.0.0
 * @hide
 */
public final class SelectionSpec implements Parcelable {
    public static final Creator<SelectionSpec> CREATOR = new Creator<SelectionSpec>() {
        @Override
        public SelectionSpec createFromParcel(Parcel source) {
            return new SelectionSpec(source);
        }

        @Override
        public SelectionSpec[] newArray(int size) {
            return new SelectionSpec[size];
        }
    };
    private int mMaxSelectable;
    private int mMinSelectable;
    private long mMinPixels;
    private long mMaxPixels;
    private int mMinWidthPixels;
    private int mMinHeightPixels;
    private int mMaxWidthPixels;
    private int mMaxHeightPixels;
    private Set<MimeType> mMimeTypeSet;

    public SelectionSpec() {
        mMinSelectable = 0;
        mMaxSelectable = 1;
        mMinPixels = 0L;
        mMaxPixels = Long.MAX_VALUE;
        mMinWidthPixels = 0;
        mMinHeightPixels = 0;
        mMaxWidthPixels = Integer.MAX_VALUE;
        mMaxHeightPixels = Integer.MAX_VALUE;
    }

    /* package */ SelectionSpec(Parcel source) {
        mMinSelectable = source.readInt();
        mMaxSelectable = source.readInt();
        mMinPixels = source.readLong();
        mMaxPixels = source.readLong();
        mMinWidthPixels = source.readInt();
        mMinHeightPixels = source.readInt();
        mMaxWidthPixels = source.readInt();
        mMaxHeightPixels = source.readInt();
        List<MimeType> list = new ArrayList<>();
        source.readList(list, MimeType.class.getClassLoader());
        mMimeTypeSet = EnumSet.copyOf(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mMinSelectable);
        dest.writeInt(mMaxSelectable);
        dest.writeLong(mMinPixels);
        dest.writeLong(mMaxPixels);
        dest.writeInt(mMinWidthPixels);
        dest.writeInt(mMinHeightPixels);
        dest.writeInt(mMaxWidthPixels);
        dest.writeInt(mMaxHeightPixels);
        dest.writeList(new ArrayList<>(mMimeTypeSet));
    }

    public void setMaxSelectable(int maxSelectable) {
        mMaxSelectable = maxSelectable;
    }

    public void setMinSelectable(int minSelectable) {
        mMinSelectable = minSelectable;
    }

    public void setMinPixels(long minPixels) {
        mMinPixels = minPixels;
    }

    public void setMaxPixels(long maxPixels) {
        mMaxPixels = maxPixels;
    }

    public void setMinSize(int minWidthPixels, int minHeightPixels) {
        this.mMinWidthPixels = minWidthPixels;
        this.mMinHeightPixels = minHeightPixels;
    }

    public void setMaxSize(int maxWidthPixels, int maxHeightPixels) {
        this.mMaxWidthPixels = maxWidthPixels;
        this.mMaxHeightPixels = maxHeightPixels;
    }

    public void setMimeTypeSet(Set<MimeType> set) {
        mMimeTypeSet = set;
    }

    public int getMinSelectable() {
        return mMinSelectable;
    }

    public int getMaxSelectable() {
        return mMaxSelectable;
    }

    public long getMinPixels() {
        return mMinPixels;
    }

    public long getMaxPixels() {
        return mMaxPixels;
    }

    public int getMinWidthPixels() {
        return mMinWidthPixels;
    }

    public int getMinHeightPixels() {
        return mMinHeightPixels;
    }

    public int getMaxWidthPixels() {
        return mMaxWidthPixels;
    }

    public int getMaxHeightPixels() {
        return mMaxHeightPixels;
    }

    public Set<MimeType> getMimeTypeSet() {
        return mMimeTypeSet;
    }
}
