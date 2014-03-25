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
package com.laevatein.internal.ui.helper;

import com.laevatein.R;
import com.laevatein.internal.entity.Album;
import com.laevatein.internal.entity.AlbumViewResources;
import com.laevatein.internal.ui.AlbumListFragment;
import com.laevatein.internal.ui.adapter.DevicePhotoAlbumAdapter;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * @author KeithYokoma
 * @since 2014/03/20
 */
public final class AlbumListViewHelper {
    private AlbumListViewHelper() {
        throw new AssertionError("oops! the utility class is about to be instantiated...");
    }

    public static void setUpListView(Fragment fragment, AdapterView.OnItemClickListener listener, AlbumViewResources resources) {
        ListView listView = (ListView) fragment.getView().findViewById(R.id.l_list_directory);
        listView.setOnItemClickListener(listener);
        listView.setAdapter(new DevicePhotoAlbumAdapter(fragment.getActivity(), null, resources));
    }

    public static void setCursor(Fragment fragment, Cursor cursor) {
        ListView listView = (ListView) fragment.getView().findViewById(R.id.l_list_directory);
        CursorAdapter adapter = (CursorAdapter) listView.getAdapter();
        adapter.swapCursor(cursor);
    }

    public static void callOnSelect(AlbumListFragment.OnDirectorySelectListener listener, Cursor cursor) {
        Album album = Album.valueOf(cursor);
        listener.onSelect(album);
    }
}