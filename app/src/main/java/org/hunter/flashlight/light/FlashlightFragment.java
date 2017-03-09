package org.hunter.flashlight.light;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hunter.flashlight.R;
import org.hunter.flashlight.widget.LightBkView;

public class FlashlightFragment extends Fragment implements FlashlightContract.View {

    private FlashlightContract.Presenter mPresenter;
    private LightBkView lightView;

    public FlashlightFragment() {
        // Requires empty public constructor
    }

    public static FlashlightFragment newInstance() {
        return new FlashlightFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull FlashlightContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flashlight, container, false);
        lightView = (LightBkView) root.findViewById(R.id.light1);
        //定义单击事件
        lightView.setOnClickListener(lightView);
        return root;
    }
}
