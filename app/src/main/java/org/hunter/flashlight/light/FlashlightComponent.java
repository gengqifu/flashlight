package org.hunter.flashlight.light;

import org.hunter.flashlight.base.FlashlightRepositoryComponent;
import org.hunter.flashlight.util.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = FlashlightRepositoryComponent.class, modules = {FlashlightPresenterModule.class})
public interface FlashlightComponent {
    void inject(FlashlightActivity activity);
}
