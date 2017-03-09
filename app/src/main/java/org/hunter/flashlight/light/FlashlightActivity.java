package org.hunter.flashlight.light;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.hunter.flashlight.R;
import org.hunter.flashlight.base.FlashlightApplication;
import org.hunter.flashlight.util.ActivityUtils;

import javax.inject.Inject;

public class FlashlightActivity extends AppCompatActivity {

    @Inject
    FlashlightPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlashlightFragment fragment =
                (FlashlightFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment == null) {
            // Create the fragment
            fragment = FlashlightFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.content_frame);
        }

        // Create the presenter
        DaggerFlashlightComponent.builder()
                .flashlightRepositoryComponent(((FlashlightApplication) getApplication()).getFlashlightRepositoryComponent())
                .flashlightPresenterModule(new FlashlightPresenterModule(fragment)).build()
                .inject(this);
    }
}
