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
package com.laevatein.internal.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amalgam.os.BundleUtils;
import com.laevatein.R;
import com.laevatein.internal.entity.Album;
import com.laevatein.internal.misc.ui.FragmentUtils;
import com.laevatein.internal.model.DevicePhotoAlbumCollection;
import com.laevatein.internal.ui.helper.AlbumListViewHelper;

/**
 * @author KeithYokoma
 * @version 1.0.0
 * @hide
 * @since 2014/03/20
 */
public class AlbumListFragment extends Fragment implements
        AdapterView.OnItemClickListener,
        DevicePhotoAlbumCollection.DevicePhotoAlbumCallbacks {
    private static final String ARGS_ALBUM_ID = BundleUtils.buildKey(AlbumListFragment.class, "ARGS_ALBUM_ID");

    private final DevicePhotoAlbumCollection mCollection = new DevicePhotoAlbumCollection();
    private OnDirectorySelectListener mListener;
    private String defaultAlbum;

    public static AlbumListFragment newInstance(String defaultAlbumId) {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_ALBUM_ID, defaultAlbumId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnDirectorySelectListener) context;
        } catch (ClassCastException e) {
            throw new IllegalStateException("the host activity should implement OnDirectorySelectListener.", e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.l_fragment_list_album, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AlbumListViewHelper.setUpHeader(this);
        AlbumListViewHelper.setUpListView(this, this);
        defaultAlbum = getArguments().getString(ARGS_ALBUM_ID);
        mCollection.onCreate(getActivity(), this);
        mCollection.onRestoreInstanceState(savedInstanceState);
        ListView list = (ListView) FragmentUtils.findViewById(this, R.id.l_list_album);
        if (list.getHeaderViewsCount() > 0 && mCollection.getCurrentSelection() == 0) {
            mCollection.setStateCurrentSelection(1);
        }
        AlbumListViewHelper.setCheckedState(this, mCollection.getCurrentSelection());
        mCollection.loadAlbums();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mCollection.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        mCollection.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        if (cursor == null) {
            //avoid moving selection to header
            AlbumListViewHelper.setCheckedState(this, mCollection.getCurrentSelection());
            return;
        }
        AlbumListViewHelper.callOnSelect(mListener, cursor);
        AlbumListViewHelper.setCheckedState(this, position);
        mCollection.setStateCurrentSelection(position);
    }

    @Override
    public void onLoad(final Cursor cursor) {
        AlbumListViewHelper.setCursor(this, cursor);
        AlbumListViewHelper.callOnDefaultSelect(this, mListener, cursor, defaultAlbum, mCollection);
    }

    @Override
    public void onReset() {
        AlbumListViewHelper.setCursor(this, null);
    }

    public interface OnDirectorySelectListener {
        void onSelect(Album album);
    }
}