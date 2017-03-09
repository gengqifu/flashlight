package org.hunter.flashlight.light;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link FlashlightPresenter}.
 */
@Module
public class FlashlightPresenterModule {

    private final FlashlightContract.View mView;

    public FlashlightPresenterModule(FlashlightContract.View view) {
        mView = view;
    }

    @Provides
    FlashlightContract.View provideTasksContractView() {
        return mView;
    }

}
