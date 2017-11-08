package com.hafiizh.japanesestation.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.facebook.login.LoginManager;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.databinding.HeaderNavBinding;
import com.hafiizh.japanesestation.ui.activity.About;
import com.hafiizh.japanesestation.ui.activity.bookmark.Bookmark;
import com.hafiizh.japanesestation.ui.activity.kategori.Kategori;
import com.hafiizh.japanesestation.ui.activity.login.Login;
import com.hafiizh.japanesestation.ui.activity.settings.SettingsActivity;
import com.hafiizh.japanesestation.utilities.Constants;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.TwitterCore;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class DrawerConfig {
    private Activity activity;
    private Toolbar toolbar;

    private AccountHeader header;
    private Drawer drawer;

    private HeaderNavBinding binding;

    public DrawerConfig(Activity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
    }

    public void addDrawer() {
        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(activity).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Picasso.with(activity).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {

            }

            @Override
            public Drawable placeholder(Context ctx) {
                return null;
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    return activity.getDrawable(R.mipmap.ic_launcher_round);
                return null;
            }
        });

        binding = HeaderNavBinding.inflate(LayoutInflater.from(activity));

        if (SharedPref.getInt(Constants.AUTH.LOGIN) == Constants.AUTH.FB_ACCOUNT) {
            binding.setImage(SharedPref.getString(Constants.AUTH.FB_GRAPH.IMAGE));
            binding.setName(SharedPref.getString(Constants.AUTH.FB_GRAPH.NAME));
            binding.setEmail(SharedPref.getString(Constants.AUTH.FB_GRAPH.EMAIL));
            header = new AccountHeaderBuilder()
                    .withActivity(activity)
                    .withHeaderBackground(android.R.color.holo_red_light)
                    .addProfiles(
                            new ProfileDrawerItem()
                                    .withName(SharedPref.getString(Constants.AUTH.FB_GRAPH.NAME))
                                    .withEmail(SharedPref.getString(Constants.AUTH.FB_GRAPH.EMAIL))
                                    .withIcon(SharedPref.getString(Constants.AUTH.FB_GRAPH.IMAGE))
                    )
                    .withOnAccountHeaderListener((view, profile, current) -> false).build();
        } else if (SharedPref.getInt(Constants.AUTH.LOGIN) == Constants.AUTH.TW_ACCOUNT) {
            binding.setImage(SharedPref.getString(Constants.AUTH.TW_GRAPH.profileImageUrlHttps));
            binding.setName(SharedPref.getString(Constants.AUTH.TW_USER_NAME));
            binding.setEmail(SharedPref.getString(Constants.AUTH.TW_GRAPH.emailAddress));
            header = new AccountHeaderBuilder()
                    .withActivity(activity)
                    .withHeaderBackground(android.R.color.holo_red_light)
                    .addProfiles(
                            new ProfileDrawerItem()
                                    .withName(SharedPref.getString(Constants.AUTH.TW_USER_NAME))
                                    .withEmail(SharedPref.getString(Constants.AUTH.TW_GRAPH.emailAddress))
                                    .withIcon(SharedPref.getString(Constants.AUTH.TW_GRAPH.profileImageUrlHttps))
                    )
                    .withOnAccountHeaderListener((view, profile, current) -> false).build();
        } else {
            binding.setImage("");
            binding.setName("No Display Name");
            binding.setEmail("Email unknown");
            header = new AccountHeaderBuilder()
                    .withActivity(activity)
                    .withHeaderBackground(android.R.color.holo_red_light)
                    .addProfiles(
                            new ProfileDrawerItem()
                                    .withName("No Display Name")
                                    .withEmail("Email unknown")
                                    .withIcon(R.mipmap.ic_launcher_round)
                    )
                    .withOnAccountHeaderListener((view, profile, current) -> false).build();
        }

        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
//                .withAccountHeader(header)
                .withHeader(binding.getRoot())
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withIdentifier(1)
                                .withName("Home")
                                .withIcon(R.mipmap.ic_launcher_round),
                        new ExpandableDrawerItem()
                                .withName("Categories")
                                .withIcon(R.mipmap.ic_launcher_round)
                                .withIdentifier(2)
                                .withSubItems(
                                        new SecondaryDrawerItem().withIdentifier(21).withName(Common.sub("Entertainment")),
                                        new SecondaryDrawerItem().withIdentifier(22).withName(Common.sub("Otakuarena")),
                                        new SecondaryDrawerItem().withIdentifier(23).withName(Common.sub("Lifestyle")),
                                        new SecondaryDrawerItem().withIdentifier(24).withName(Common.sub("Culture")),
                                        new SecondaryDrawerItem().withIdentifier(25).withName(Common.sub("Events")),
                                        new SecondaryDrawerItem().withIdentifier(26).withName(Common.sub("WakarimasenLOL")),
                                        new SecondaryDrawerItem().withIdentifier(27).withName(Common.sub("Learn Japanese")),
                                        new SecondaryDrawerItem().withIdentifier(28).withName(Common.sub("Review"))
                                ),
                        new PrimaryDrawerItem()
                                .withIdentifier(3)
                                .withName("Read Later")
                                .withIcon(R.mipmap.ic_launcher_round),
                        new PrimaryDrawerItem()
                                .withIdentifier(4)
                                .withName("Settings")
                                .withIcon(R.mipmap.ic_launcher_round),
                        new PrimaryDrawerItem()
                                .withIdentifier(5)
                                .withName("About")
                                .withIcon(R.mipmap.ic_launcher_round),
                        new PrimaryDrawerItem()
                                .withIdentifier(6)
                                .withName("Login or Register")
                                .withIcon(R.mipmap.ic_launcher_round),
                        new PrimaryDrawerItem()
                                .withIdentifier(7)
                                .withName("Logout")
                                .withIcon(R.mipmap.ic_launcher_round)
                )
                .withDrawerGravity(GravityCompat.START)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    if (drawerItem != null) {
                        if (drawerItem.getIdentifier() == 3) {
                            Common.newActivity(Bookmark.class);
                        } else if (drawerItem.getIdentifier() == 4) {
                            Common.newActivity(SettingsActivity.class);
                        } else if (drawerItem.getIdentifier() == 5) {
                            Common.newActivity(About.class);
                        } else if (drawerItem.getIdentifier() == 6) {
                            Common.newActivity(Login.class);
                            activity.finish();
                        } else if (drawerItem.getIdentifier() == 7) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setIcon(R.mipmap.ic_launcher).setTitle("Logout")
                                    .setMessage("Are you sure you want to logout?")
                                    .setCancelable(true).setNeutralButton("CANCEL", (dialog, which) -> {
                                dialog.dismiss();
                            }).setNegativeButton("NO", (dialog, which) -> {
                                dialog.dismiss();
                            });
                            if (SharedPref.getInt(Constants.AUTH.LOGIN) == Constants.AUTH.FB_ACCOUNT) {
                                builder.setPositiveButton("YES", (dialog, which) -> {
                                    LoginManager.getInstance().logOut();
                                    SharedPref.delete(Constants.AUTH.FB_APP_ID);
                                    SharedPref.delete(Constants.AUTH.FB_TOKEN);
                                    SharedPref.delete(Constants.AUTH.FB_USER_ID);

                                    SharedPref.delete(Constants.AUTH.LOGIN);

                                    Common.newActivity(Login.class);
                                    activity.finish();
                                }).create().show();
                            } else if (SharedPref.getInt(Constants.AUTH.LOGIN) == Constants.AUTH.TW_ACCOUNT) {
                                builder.setPositiveButton("YES", (dialog, which) -> {
                                    TwitterCore.getInstance().getSessionManager().clearActiveSession();
                                    SharedPref.delete(Constants.AUTH.TW_AUTH_TOKEN);
                                    SharedPref.delete(Constants.AUTH.TW_AUTH_TOKEN_SECRET);
                                    SharedPref.delete(Constants.AUTH.TW_USER_ID);
                                    SharedPref.delete(Constants.AUTH.TW_USER_NAME);

                                    SharedPref.delete(Constants.AUTH.LOGIN);

                                    Common.newActivity(Login.class);
                                    activity.finish();
                                }).create().show();
                            } else {
                                builder.setPositiveButton("YES", (dialog, which) -> {
                                    LoginManager.getInstance().logOut();
                                    SharedPref.delete(Constants.AUTH.FB_APP_ID);
                                    SharedPref.delete(Constants.AUTH.FB_TOKEN);
                                    SharedPref.delete(Constants.AUTH.FB_USER_ID);

                                    TwitterCore.getInstance().getSessionManager().clearActiveSession();
                                    SharedPref.delete(Constants.AUTH.TW_AUTH_TOKEN);
                                    SharedPref.delete(Constants.AUTH.TW_AUTH_TOKEN_SECRET);
                                    SharedPref.delete(Constants.AUTH.TW_USER_ID);
                                    SharedPref.delete(Constants.AUTH.TW_USER_NAME);

                                    SharedPref.delete(Constants.AUTH.LOGIN);

                                    Common.newActivity(Login.class);
                                    activity.finish();
                                }).create().show();
                            }
                        } else if (drawerItem.getIdentifier() == 21) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Entertainment", "entertainment"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 22) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Otakuarena", "otaku_arena"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 23) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Lifestyle", "lifestyle"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 24) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Culture", "culture"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 25) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Events", "events"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 26) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"WakarimasenLOL", "wakarimasen"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 27) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Learn Japanese", "learn_js"};
                            Common.newActivity(Kategori.class, keys, value);
                        } else if (drawerItem.getIdentifier() == 28) {
                            String[] keys = {"title", "kategori"};
                            String[] value = {"Review", "review"};
                            Common.newActivity(Kategori.class, keys, value);
                        }
                    }
                    return false;
                })
                .build();
    }

    public void closeDrawer() {
        drawer.closeDrawer();
    }

    public void openDrawer() {
        drawer.openDrawer();
    }

    public boolean isOpenDrawer() {
        return drawer.isDrawerOpen();
    }
}