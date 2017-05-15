package com.example.lollipop.medicaldoctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.mvp.presenter.DataPresenter;
import com.example.lollipop.medicaldoctor.mvp.view.DataView;
import com.example.lollipop.medicaldoctor.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class DataActivity extends BaseActivity implements DataView,View.OnClickListener {
    private LineChartView chartView;
    private LineChartData lineData;
    private List<Integer> datas = new ArrayList<>();
    public final static String[] hours = new String[]{"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55",};
    private Timer timer;
    private int patient_id;

    @Inject
    DataPresenter dataPresenter;

    @BindView(R.id.pressure_layout)
    LinearLayout pressureLayout;
    @BindView(R.id.temperature_layout)
    LinearLayout temperatureLayout;
    @BindView(R.id.angle_layout)
    LinearLayout angleLayout;
    @BindView(R.id.pulse_layout)
    LinearLayout pulseLayout;
    @BindView(R.id.data_pressure)
    TextView dataPressure;
    @BindView(R.id.data_angle)
    TextView dataAngle;
    @BindView(R.id.data_temperature)
    TextView dataTemperature;
    @BindView(R.id.data_pulse)
    TextView dataPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        dataPresenter.attachView(this);

        Intent intent = getIntent();
        patient_id = intent.getIntExtra("id", 0);

        chartView = (LineChartView) findViewById(R.id.chart);
        //绑定监听器
        pressureLayout.setOnClickListener(this);
        angleLayout.setOnClickListener(this);
        temperatureLayout.setOnClickListener(this);
        pulseLayout.setOnClickListener(this);

        //初始化图表
        generate(datas);
        //设置定时器，定时刷新
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //刷新图表
                dataPresenter.getDataList(patient_id);
            }
        }, 0, 2000);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void refreshChart(List<Integer> list) {
        //datas保存当前状态
        datas = list;
        generate(datas);
    }

    @Override
    public void refreshData(Map<String, Object> map) {
        dataPressure.setText(map.get("pressure")+"Pa");
        dataAngle.setText(map.get("angle")+"度");
        dataTemperature.setText(map.get("temperature")+"℃");
        dataPulse.setText(map.get("pulse")+"次/分");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = new Intent(this, DataDetailActivity.class);
        switch (id){
            case R.id.pressure_layout:
                intent.putExtra("tag", "pressure");
                intent.putExtra("id", patient_id);
                break;
            case R.id.temperature_layout:
                intent.putExtra("tag", "temperature");
                intent.putExtra("id", patient_id);
                break;
            case R.id.angle_layout:
                intent.putExtra("tag", "angle");
                intent.putExtra("id", patient_id);
                break;
            case R.id.pulse_layout:
                intent.putExtra("tag", "pulse");
                intent.putExtra("id", patient_id);
                break;
        }
        startActivity(intent);
    }

    /**
     * 图表生成
     * @param datas 数据
     */
    private void generate(List<Integer> datas) {
        int numValues = 12;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i=0; i<numValues; i++){
            axisValues.add(new AxisValue(i).setLabel(hours[i]));
        }
        for (int i = 0; i < datas.size(); ++i) {
            values.add(new PointValue(i, datas.get(i)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE).setCubic(true);
        line.setPointRadius(3);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        lineData = new LineChartData(lines);
        Axis axis = new Axis(axisValues);
        axis.setHasLines(true);
        axis.setHasTiltedLabels(true);
        lineData.setAxisXBottom(axis);
        lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));

        chartView.setLineChartData(lineData);

        // For build-up animation you have to disable viewport recalculation.
        chartView.setViewportCalculationEnabled(false);

        // And set initial max viewport and current viewport- remember to set viewports after data.
        Viewport v = new Viewport(0, 1000, 12, 0);
        chartView.setMaximumViewport(v);
        chartView.setCurrentViewport(v);

        chartView.setZoomType(ZoomType.HORIZONTAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消定时器
        timer.cancel();
    }
}
