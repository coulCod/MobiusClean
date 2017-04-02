package com.matsyuk.mobiusclean.clean.dagger.main;

import com.matsyuk.mobiusclean.clean.dagger.start.StartComponent;
import com.matsyuk.mobiusclean.clean.ui.main.views.MainActivity;

import dagger.Subcomponent;

/**
 * @author e.matsyuk
 */
@MainScope
@Subcomponent(modules = {MainModule.class, NavigationModule.class})
public interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        MainComponent build();
    }

    StartComponent.Builder startComponentBuilder();

    void inject(MainActivity mainActivity);

}
