package com.example.lollipop.medicaldoctor.data.response;

import java.util.List;

/**
 * Created by R2D2 on 2017/5/12.
 */

public class PatientListResponse {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : tom
         * id : 1
         * username : tom
         */

        private String name;
        private int id;
        private String username;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
