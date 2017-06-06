package www.nupter.org.nupter.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.update.UmengUpdateAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import www.nupter.org.nupter.R;
import www.nupter.org.nupter.event.PushScreenMessageEvent;
import www.nupter.org.nupter.fragment.EyeFragment;
import www.nupter.org.nupter.fragment.LibraryFragment;
import www.nupter.org.nupter.fragment.LoginZhenFangFragment;
import www.nupter.org.nupter.fragment.LostFragment;
import www.nupter.org.nupter.fragment.MainPageFragment;
import www.nupter.org.nupter.fragment.ReadingFragment;
import www.nupter.org.nupter.fragment.SecondHandFragment;
import www.nupter.org.nupter.http.HttpResquestUtil;
import www.nupter.org.nupter.utils.Common;
import www.nupter.org.nupter.utils.DataUtils;
import www.nupter.org.nupter.utils.PreferenceKey;
import www.nupter.org.nupter.view.MoreWindow;
import www.nupter.org.nupter.view.ReactImageView;
import www.nupter.org.nupter.view.RoundImageView;


public class MainActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //didi
    MoreWindow mMoreWindow;

    private ImageView mainPageImage;
    private ImageView readingImage;
    private ImageView eyeImage;
    private ImageView lostImage;
    private ImageView secondImage;


    private ImageView image_left;

    private RelativeLayout mainPageRela;
    private RelativeLayout readingRela;
    private RelativeLayout eyeRela;
    private RelativeLayout lostRela;
    private RelativeLayout secRela;

    private MainPageFragment mainPageFragment;
    private ReadingFragment readingFragment;
    private LostFragment lostFragment;
    private SecondHandFragment secondHandFragment;
    private Fragment currentFragment;
    private LibraryFragment libraryFragment;
    private LoginZhenFangFragment loginZhenFangFragment;


    private ReactImageView avatarImage;
    private TextView nickNameText;
    private TextView title_text;

    private TextView quit_text;
    private TextView setting_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("掌上南邮");


        //友盟自动更新
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);

        EventBus.getDefault().register(MainActivity.this);

        mainPageImage = (ImageView) findViewById(R.id.main_menu);
        readingImage = (ImageView) findViewById(R.id.main_reading);
        eyeImage = (ImageView) findViewById(R.id.main_eye);
        lostImage = (ImageView) findViewById(R.id.main_lost);
        secondImage = (ImageView) findViewById(R.id.main_second);
        image_left = (ImageView) findViewById(R.id.image_left);

        mainPageRela = (RelativeLayout) findViewById(R.id.main_rela);
        readingRela = (RelativeLayout) findViewById(R.id.read_rela);
        lostRela = (RelativeLayout) findViewById(R.id.lost_rela);
        eyeRela = (RelativeLayout) findViewById(R.id.eye_rela);
        secRela = (RelativeLayout) findViewById(R.id.sec_rela);

        title_text = (TextView) findViewById(R.id.header_title);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        image_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });


       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        View headerView = getLayoutInflater().inflate(R.layout.nav_header_main, navigationView, false);
        navigationView.addView(headerView);


        quit_text = (TextView) headerView.findViewById(R.id.quite);
        setting_text = (TextView) headerView.findViewById(R.id.setting);


        avatarImage = (ReactImageView) headerView.findViewById(R.id.imageView);
        nickNameText = (TextView) headerView.findViewById(R.id.nickName);
        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String avartarurl = DataUtils.getPreferences("avatar_url", "");
                if (avartarurl.equals("")) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


        quit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setting_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                drawer.closeDrawer(Gravity.LEFT);
            }
        });

        if (mainPageFragment == null) {
            mainPageFragment = new MainPageFragment();
        }

        if (!mainPageFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, mainPageFragment).commit();
            currentFragment = mainPageFragment;
        }

        mainPageRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPageImage.setImageResource(R.drawable.main2);
                readingImage.setImageResource(R.drawable.read1);
                lostImage.setImageResource(R.drawable.lost1);
                secondImage.setImageResource(R.drawable.second1);
                eyeImage.setImageResource(R.drawable.eyes);

                if (mainPageFragment == null) {
                    mainPageFragment = new MainPageFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), mainPageFragment);
                setTitleText("掌上南邮");
            }
        });

        readingRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPageImage.setImageResource(R.drawable.main1);
                readingImage.setImageResource(R.drawable.read2);
                lostImage.setImageResource(R.drawable.lost1);
                secondImage.setImageResource(R.drawable.second1);
                eyeImage.setImageResource(R.drawable.eyes);
                if (readingFragment == null) {
                    readingFragment = new ReadingFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), readingFragment);
                setTitleText("柚子轻博客");

            }
        });

        lostRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPageImage.setImageResource(R.drawable.main1);
                readingImage.setImageResource(R.drawable.read1);
                lostImage.setImageResource(R.drawable.lost2);
                secondImage.setImageResource(R.drawable.second1);
                eyeImage.setImageResource(R.drawable.eyes);

                if (lostFragment == null) {
                    lostFragment = new LostFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), lostFragment);
                setTitleText("失物招领所");

            }
        });

        secRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPageImage.setImageResource(R.drawable.main1);
                readingImage.setImageResource(R.drawable.read1);
                lostImage.setImageResource(R.drawable.lost1);
                secondImage.setImageResource(R.drawable.second2);
                eyeImage.setImageResource(R.drawable.eyes);

                if (secondHandFragment == null) {
                    secondHandFragment = new SecondHandFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), secondHandFragment);
                setTitleText("柚子二手铺");

            }
        });

        eyeRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPageImage.setImageResource(R.drawable.main1);
                readingImage.setImageResource(R.drawable.read1);
                lostImage.setImageResource(R.drawable.lost1);
                secondImage.setImageResource(R.drawable.second1);
                eyeImage.setImageResource(R.drawable.eyes);
                /*if (eyeFragment == null) {
                    eyeFragment = new EyeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), eyeFragment);*/
                showMoreWindow(v);
            }
        });


    }

    public void setTitleText(String title) {
        title_text.setText(title);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PushScreenMessageEvent pushScreenMessageEvent) {
        String msg = "onEventMainThread收到了消息：" + pushScreenMessageEvent.getContent();
        Common.Toast(MainActivity.this, msg);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            // transaction.setCustomAnimations(R.anim.fragment_slide_in_from_left, R.anim.fragment_slide_out_to_right);
            transaction.hide(currentFragment).add(R.id.container, fragment).commit();
        } else {
            //  transaction.setCustomAnimations(R.anim.fragment_slide_in_from_left, R.anim.fragment_slide_out_to_right);
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }


    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String nickName = DataUtils.getPreferences("userName", "");
        if (!nickName.equals("")) {
            nickNameText.setText(nickName);
        }else{
            nickNameText.setText("未登录");
        }
        String avartarurl = DataUtils.getPreferences("avatar_url", "");
        if (!avartarurl.equals("")) {
            HttpResquestUtil.initImage(avatarImage, avartarurl);
        }else{
            avatarImage.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        }
        JPushInterface.onResume(this);

    }


    private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
            mMoreWindow.init();
        }

        mMoreWindow.showMoreWindow(view, 100);
        mMoreWindow.setListenner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPageImage.setImageResource(R.drawable.main1);
                readingImage.setImageResource(R.drawable.read1);
                lostImage.setImageResource(R.drawable.lost1);
                secondImage.setImageResource(R.drawable.second1);
                eyeImage.setImageResource(R.drawable.eyelight);
                mMoreWindow.dismiss();
                if (v.getId() == R.id.library) {
                    if (libraryFragment == null) {
                        libraryFragment = new LibraryFragment();
                    }
                    addOrShowFragment(getSupportFragmentManager().beginTransaction(), libraryFragment);
                    setTitleText("图书管理");
                }
                if (v.getId() == R.id.class_table) {
                    if (!DataUtils.getPreferences(PreferenceKey.KEBIAO, "").equals("")) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ClassTableActivity.class);
                        startActivity(intent);
                    } else {
                        if (loginZhenFangFragment == null) {
                            loginZhenFangFragment = new LoginZhenFangFragment();
                        }
                        addOrShowFragment(getSupportFragmentManager().beginTransaction(), loginZhenFangFragment);
                        setTitleText("登陆正方");
                    }
                }
                if (v.getId() == R.id.running) {

                }
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
