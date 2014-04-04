package com.laevatein.internal.ui;

import com.laevatein.R;
import com.laevatein.internal.ui.helper.SelectedCountViewHelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author KeithYokoma
 * @since 2014/04/03
 * @version 1.0.0
 * @hide
 */
public class SelectedCountFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.l_fragment_selected_count, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SelectedCountViewHelper.updateCountView((PhotoSelectionActivity) getActivity(), this);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        SelectedCountViewHelper.updateCountView((PhotoSelectionActivity) getActivity(), this); // bit hacky
    }
}
