package org.hunter.flashlight.base;

import android.app.Application;

public class FlashlightApplication extends Application {
    private FlashlightRepositoryComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerFlashlightRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext())))
                .build();
    }

    public FlashlightRepositoryComponent getFlashlightRepositoryComponent() {
        return mComponent;
    }
}
