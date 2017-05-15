package com.example.lollipop.medicaldoctor.data.response;

import java.util.List;

/**
 * Created by Lollipop on 2017/5/14.
 */

public class DataDownloadResponse {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * create_time : Wed May 10 21:08:41 CST 2017
         * patient_id : 1
         * temperature : 37.65
         * pulse : 106
         * angle : 6.48
         * id : 820
         * pressure : 789
         */

        private String create_time;
        private int patient_id;
        private double temperature;
        private int pulse;
        private double angle;
        private int id;
        private int pressure;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(int patient_id) {
            this.patient_id = patient_id;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getPulse() {
            return pulse;
        }

        public void setPulse(int pulse) {
            this.pulse = pulse;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "create_time='" + create_time + '\'' +
                    ", patient_id=" + patient_id +
                    ", temperature=" + temperature +
                    ", pulse=" + pulse +
                    ", angle=" + angle +
                    ", id=" + id +
                    ", pressure=" + pressure +
                    '}';
        }
    }
}
