package com.hafiizh.japanesestation.ui.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.databinding.ActivityLoginBinding;
import com.hafiizh.japanesestation.ui.activity.MainActivity;
import com.hafiizh.japanesestation.utilities.Constants;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

public class Login extends BaseCompatActivity<ActivityLoginBinding> {
    public static final String TAG = Login.class.getSimpleName();
    CallbackManager callbackManager;
    TwitterSession twitterSession;

    ProgressDialog dialog;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
        Common.trackScreenView(TAG);

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait...");

        callbackManager = CallbackManager.Factory.create();
        twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        dataBinding.btnFbLogin.setReadPermissions("public_profile", "email");

        loginWithFB();
        loginWithTW();

        if (SharedPref.getInt(Constants.AUTH.LOGIN) == Constants.AUTH.FB_ACCOUNT) {
            if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null) {
                Common.newActivity(MainActivity.class);
                finish();
            }
        } else if (SharedPref.getInt(Constants.AUTH.LOGIN) == Constants.AUTH.TW_ACCOUNT) {
            if (twitterSession != null) {
                Common.newActivity(MainActivity.class);
                finish();
            }
        }
    }

    private void loginWithTW() {
        dataBinding.btnTwLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                dialog.show();
                twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterApiClient apiClient = new TwitterApiClient(twitterSession);
                apiClient.getAccountService().verifyCredentials(true, true, true).enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        if (result.response.isSuccessful()) {
                            twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
                            Common.setUserTwitterInfo(result.data, twitterSession);
                            dialog.dismiss();
                            Common.newActivity(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Common.trackException(exception);
                        Common.showToast("" + exception.getMessage());
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Common.trackException(exception);
                dialog.dismiss();
            }
        });
    }

    private void loginWithFB() {
        dataBinding.btnFbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                dialog.show();
                Bundle bundle = new Bundle();
                bundle.putString("fields", "id,email,gender,cover,picture.width(200).height(200)");
                new GraphRequest(loginResult.getAccessToken(), "me", bundle, HttpMethod.GET, response -> {
                    if (response != null) {
                        Common.setUserFacebookInfo(response.getJSONObject(), Profile.getCurrentProfile(), loginResult);
                        dialog.dismiss();
                        Common.newActivity(MainActivity.class);
                        finish();
                    }
                }).executeAsync();
            }

            @Override
            public void onCancel() {
                dialog.dismiss();
            }

            @Override
            public void onError(FacebookException error) {
                dialog.dismiss();
                Common.trackException(error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        dataBinding.btnTwLogin.onActivityResult(requestCode, resultCode, data);
    }
}