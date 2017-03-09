package org.hunter.flashlight.light;

import javax.inject.Inject;

final class FlashlightPresenter implements FlashlightContract.Presenter {

    private final FlashlightContract.View mView;

    @Inject
    FlashlightPresenter(FlashlightContract.View view) {
        mView = view;
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     * For more information, see Java Concurrency in Practice.
     */
    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
