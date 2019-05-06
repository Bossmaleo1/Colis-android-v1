package com.colis.android.colis.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colis.android.colis.R;

public class Notification extends Fragment {
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
							 Bundle savedInstanceState) {
		final View inflatedView = inflater.inflate(R.layout.notification, container, false);

		return  inflatedView;
	}
}