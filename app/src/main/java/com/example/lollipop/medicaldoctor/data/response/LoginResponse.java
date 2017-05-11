package com.example.lollipop.medicaldoctor.data.response;

/**
 * Created by Lollipop on 2017/5/11.
 */

public class LoginResponse {

    /**
     * status : success
     * message : null
     * user : {"id":1,"username":"zhangsan","password":"1234","name":"zhangsan"}
     */

    private String status;
    private String message;
    private UserBean user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 1
         * username : zhangsan
         * password : 1234
         * name : zhangsan
         */

        private int id;
        private String username;
        private String password;
        private String name;
        private String hospital;
        private String department;

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
