package org.hunter.flashlight.light;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hunter.flashlight.R;
import org.hunter.flashlight.widget.LightBkView;
import org.hunter.flashlight.widget.LightView2;

public class FlashlightFragment extends Fragment implements FlashlightContract.View {

    private FlashlightContract.Presenter mPresenter;

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
        View root;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            root = inflater.inflate(R.layout.fragment_flashlight2, container, false);
            LightView2 lightView = (LightView2) root.findViewById(R.id.light2);
            //定义单击事件
            lightView.setOnClickListener(lightView);
        } else {
            root = inflater.inflate(R.layout.fragment_flashlight, container, false);
            LightBkView lightView = (LightBkView) root.findViewById(R.id.light1);
            //定义单击事件
            lightView.setOnClickListener(lightView);
        }

        return root;
    }
}
