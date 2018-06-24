package com.tedxiong.rxjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tedxiong.rxjava2.action.BaseAction;
import com.tedxiong.rxjava2.action.ConcatMapAction;
import com.tedxiong.rxjava2.action.FlatMapAction;
import com.tedxiong.rxjava2.action.IntervalAction;
import com.tedxiong.rxjava2.network.action.NetworkAction1;
import com.tedxiong.rxjava2.network.action.NetworkAction2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BaseAction.ActionUiResult{
    @BindView(R.id.result_txt)
    TextView mRxOperatorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onFlatMap(View view){
        new FlatMapAction(this).run();
    }

    public void onConcatMap(View view){
        new ConcatMapAction(this).run();
    }

    public void onInterval(View view){
        new IntervalAction(this).run();
    }

    public void onTestAction(View view){
        new NetworkAction2(this).run();
    }

    @Override
    public void show(String result) {
        mRxOperatorsText.append(result);
    }
}
