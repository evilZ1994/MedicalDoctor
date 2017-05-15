package com.example.lollipop.medicaldoctor.data.response;

import java.util.List;

/**
 * Created by Lollipop on 2017/5/15.
 */

public class DataDetailResponse {
    private String tag;
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static class ResultBean {
        /**
         * create_time : 2017-05-14 22:59:15
         * value : 611
         */

        private String create_time;
        private int pressure;
        private double temperature;
        private double angle;
        private int pulse;

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public int getPulse() {
            return pulse;
        }

        public void setPulse(int pulse) {
            this.pulse = pulse;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "create_time='" + create_time + '\'' +
                    ", pressure=" + pressure +
                    ", temperature=" + temperature +
                    ", angle=" + angle +
                    ", pulse=" + pulse +
                    '}';
        }
    }
}
