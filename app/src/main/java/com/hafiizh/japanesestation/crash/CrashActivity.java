package com.hafiizh.japanesestation.crash;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hafiizh.japanesestation.App;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.databinding.ActivityCrashBinding;
import com.hafiizh.japanesestation.ui.activity.Splash;

public class CrashActivity extends BaseCompatActivity<ActivityCrashBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_crash;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding.btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(CrashActivity.this, Splash.class);
            intent.putExtra("crash", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    App.getInstance(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

            AlarmManager alarmManager = (AlarmManager) App.getInstance().getSystemService(Context.ALARM_SERVICE);

            if (alarmManager != null)
                alarmManager.set(AlarmManager.RTC, 2000, pendingIntent);

            finish();

            System.exit(0);
        });
    }
}