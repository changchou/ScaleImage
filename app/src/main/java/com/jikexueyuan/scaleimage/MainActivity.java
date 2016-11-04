package com.jikexueyuan.scaleimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout root;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = (LinearLayout) findViewById(R.id.root);
        imageView = (ImageView) findViewById(R.id.imageView);

        root.setOnTouchListener(new View.OnTouchListener() {

            float currentDistance;
            float lastDistance = -1;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (event.getPointerCount() >= 2) {
                            float offsetX = event.getX(0) - event.getX(1);
                            float offsetY = event.getY(0) - event.getY(1);
                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);
                            if (lastDistance < 0) {
                                lastDistance = currentDistance;
                            } else {
                                if (currentDistance - lastDistance > 5) {
                                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                                    lp.width = (int) (1.1f * imageView.getWidth());
                                    lp.height = (int) (1.1f * imageView.getHeight());
                                    imageView.setLayoutParams(lp);

                                    lastDistance = currentDistance;
                                } else if (lastDistance - currentDistance > 5) {
                                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                                    lp.width = (int) (0.9f * imageView.getWidth());
                                    lp.height = (int) (0.9f * imageView.getHeight());
                                    imageView.setLayoutParams(lp);

                                    lastDistance = currentDistance;
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        lastDistance = -1;
                        break;
                }
                return true;
            }
        });
    }
}
