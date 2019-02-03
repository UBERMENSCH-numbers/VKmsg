package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.RecyclerItemClickListener;
import com.example.user.vkmsg.mvp.interfaces.IPresenter;
import com.example.user.vkmsg.mvp.view.BaseFragment;
import com.example.user.vkmsg.mvp.interfaces.IView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKError;

public interface MainActivityContract {
    interface View extends IView, RecyclerItemClickListener {
        void VkSdkLogin ();
        void setFragment(BaseFragment fragment);
    }

    interface Presenter extends IPresenter, FragmentNavigationContract.Presenter {
        void login ();
        void onLoginResult (VKAccessToken res);
        void onLoginError (VKError error);
    }

    interface Model{
        String LOGIN_SHARED_PREFERECES = "login_data";
        void savePreferences (String value, String key);
        String loadPreferences (String key);
    }
}
