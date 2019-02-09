package com.example.user.vkmsg.mvp.contracts;

import com.example.user.vkmsg.interfaces.RecyclerItemClickListener;
import com.example.user.vkmsg.interfaces.IPresenter;
import com.example.user.vkmsg.base.BaseNavigationFragment;
import com.example.user.vkmsg.interfaces.IView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKError;

public interface MainActivityContract {
    interface View extends IView {
        void VkSdkLogin ();
        void setFragment(BaseNavigationFragment fragment);
        Boolean hasFragment();
    }

    interface Presenter extends IPresenter<MainActivityContract.View>, FragmentNavigationContract.Presenter {
        void login ();
        void onLoginResult (VKAccessToken res);
        void onLoginError (VKError error);
    }

    interface Model{
        String LOGIN_SHARED_PREFERENCES = "login_data";
        void savePreferences (String value, String key);
        String loadPreferences (String key);
    }
}
// TODO: ЗАПИЛИТЬ АВТОУДАЛЯТОР СООБЩЕНИЙ
// TODO: DRAG&DROP FEATURE https://developer.android.com/guide/topics/ui/drag-drop