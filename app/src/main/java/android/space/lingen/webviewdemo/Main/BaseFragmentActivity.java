package android.space.lingen.webviewdemo.Main;

import android.content.Intent;
import android.os.Bundle;
import android.space.lingen.webviewdemo.Main.webview.MainActivity;
import android.space.lingen.webviewdemo.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by lingen on 5/15/16.
 */
public abstract class BaseFragmentActivity extends AppCompatActivity{

    private ImageButton homeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_common) != null){
            if (savedInstanceState != null){
                return;
            }

            Fragment fragment = getFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_common, fragment).commit();

            this.homeButton = (ImageButton) findViewById(R.id.webview_home);

            this.homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(), MainActivity.class);

                    startActivity(intent);
                }
            });
        }
    }

    protected Fragment findFragmentById() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_common);
    }

    public abstract Fragment getFragment();
}
