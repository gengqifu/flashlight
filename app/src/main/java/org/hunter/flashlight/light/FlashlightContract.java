package org.hunter.flashlight.light;

import org.hunter.flashlight.base.BasePresenter;
import org.hunter.flashlight.base.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface FlashlightContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
