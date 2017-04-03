package com.matsyuk.mobiusclean.clean.ui.first_wizard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matsyuk.mobiusclean.clean.dagger.ComponentManager;
import com.matsyuk.mobiusclean.clean.ui.wizards_common.info.presenters.IInfoPresenter;
import com.matsyuk.mobiusclean.clean.ui.wizards_common.info.views.InfoFragment;

import javax.inject.Inject;
import javax.inject.Named;

import static com.matsyuk.mobiusclean.clean.ui.first_wizard.FirstWizardConstants.FIRST_INFO_START_NAMED_ANNOTATION;

/**
 * @author e.matsyuk
 */
public class FirstInfoStartFragment extends InfoFragment {

    @Inject
    @Named(FIRST_INFO_START_NAMED_ANNOTATION)
    IInfoPresenter infoPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ComponentManager.getInstance().getFirstComponent().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected IInfoPresenter getPresenter() {
        return infoPresenter;
    }

}
