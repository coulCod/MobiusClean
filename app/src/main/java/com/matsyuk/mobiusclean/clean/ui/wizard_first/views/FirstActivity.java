package com.matsyuk.mobiusclean.clean.ui.wizard_first.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.matsyuk.mobiusclean.R;
import com.matsyuk.mobiusclean.clean.dagger.ComponentManager;
import com.matsyuk.mobiusclean.clean.ui.common.BackButtonListener;
import com.matsyuk.mobiusclean.clean.ui.wizard_first.smart_router.FirstWizardSmartRouter;

import javax.inject.Inject;
import javax.inject.Named;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

import static com.matsyuk.mobiusclean.clean.dagger.wizards_common.WizardDaggerConstants.*;
import static com.matsyuk.mobiusclean.clean.ui.wizards_common.WizardConstants.*;

/**
 * @author e.matsyuk
 */
public class FirstActivity extends AppCompatActivity {

    @Inject
    @Named(FIRST_NAMED_ANNOTATION)
    NavigatorHolder navigatorHolder;

    @Inject
    FirstWizardSmartRouter firstWizardSmartRouter;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.start_container) {
        @Override
        public void applyCommand(Command command) {
            if (command instanceof Forward) {
                Forward forward = (Forward) command;
                if (forward.getScreenKey().equals(WIZARD_FIRST_ACCOUNT_SUB_WIZARD)) {
                    Intent loginIntent = new Intent(FirstActivity.this, FirstLoginActivity.class);
                    startActivity(loginIntent);
                    return;
                }
            }
            super.applyCommand(command);
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            if (screenKey.equals(WIZARD_FIRST_INFO_START_SCREEN)) {
                return new FirstInfoStartFragment();
            } else if (screenKey.equals(WIZARD_FIRST_LICENSE_SCREEN)) {
                return new FirstLicenseFragment();
            } else if (screenKey.equals(WIZARD_FIRST_ACTIVATION_SCREEN)) {
                return new FirstActivationFragment();
            } else if (screenKey.equals(WIZARD_FIRST_INFO_FINISH_SCREEN)) {
                return new FirstInfoFinishFragment();
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            // no actions yet
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getFirstComponent().inject(this);
        setContentView(R.layout.ac_start);
        setTitle(getString(R.string.ac_start_wizard_title));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.start_container);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
        firstWizardSmartRouter.startWizard();
    }

    @Override
    public void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (isFinishing()) {
            ComponentManager.getInstance().clearFirstComponent();
        }
        super.onDestroy();
    }

}
