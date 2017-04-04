package com.matsyuk.mobiusclean.clean.ui.wizard_first.views;

import com.matsyuk.mobiusclean.clean.dagger.ComponentManager;
import com.matsyuk.mobiusclean.clean.ui.wizards_common.info.presenters.IInfoPresenter;
import com.matsyuk.mobiusclean.clean.ui.wizards_common.info.views.InfoFragment;

import javax.inject.Inject;
import javax.inject.Named;

import static com.matsyuk.mobiusclean.clean.dagger.wizards_common.WizardDaggerConstants.*;

/**
 * @author e.matsyuk
 */
public class FirstInfoFinishFragment extends InfoFragment {

    @Inject
    @Named(FIRST_INFO_FINISH_NAMED_ANNOTATION)
    IInfoPresenter infoPresenter;

    @Override
    public void onResume() {
        ComponentManager.getInstance().getFirstComponent().inject(this);
        super.onResume();
    }

    @Override
    protected IInfoPresenter getPresenter() {
        return infoPresenter;
    }

}
