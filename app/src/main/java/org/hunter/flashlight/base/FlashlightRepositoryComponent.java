package org.hunter.flashlight.base;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is a Dagger component. Refer to {@link FlashlightApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component @Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * FlashlightApplication}.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface FlashlightRepositoryComponent {

    FlashlightRepository getFlashlightRepository();
}
