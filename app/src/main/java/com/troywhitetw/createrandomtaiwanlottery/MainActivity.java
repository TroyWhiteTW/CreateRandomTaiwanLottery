package com.troywhitetw.createrandomtaiwanlottery;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll1;
    private int group = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ll1 = findViewById(R.id.ll1);
    }

    public void createLotteryNum(View view) {
        TreeSet<Integer> set = new TreeSet<>();
        while (set.size() < 6) {
            set.add((int) (Math.random() * 49 + 1));
        }

        addNewLotteryGroup(set);
    }

    private void addNewLotteryGroup(Set<Integer> set) {
        group++;

        LinearLayout.LayoutParams layoutParams1 =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams2 =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams3 =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(1));
        layoutParams3.setMargins(0, dpToPx(10), 0, dpToPx(10));

        TextView tvGroup = new TextView(this);
        tvGroup.setLayoutParams(layoutParams1);
        tvGroup.setText(MessageFormat.format("第 {0} 組", group));

        ll1.addView(tvGroup);

        LinearLayout insideLayout = new LinearLayout(this);
        insideLayout.setLayoutParams(layoutParams2);
        insideLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (Integer num : set) {
            TextView tvBallNum = new TextView(this);
            tvBallNum.setLayoutParams(layoutParams1);
            tvBallNum.setBackground(getDrawable(R.drawable.number_bg));
            tvBallNum.setGravity(Gravity.CENTER);
            tvBallNum.setText(MessageFormat.format("{0}", num));
            tvBallNum.setTextColor(Color.parseColor("#ffffff"));
            tvBallNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvBallNum.setTypeface(null, Typeface.BOLD);

            insideLayout.addView(tvBallNum);
        }

        ll1.addView(insideLayout);

        View line = new View(this);
        line.setLayoutParams(layoutParams3);
        line.setBackgroundColor(Color.parseColor("#777777"));

        ll1.addView(line);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;

        return Math.round((float) dp * density);
    }
}
