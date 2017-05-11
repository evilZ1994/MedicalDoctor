package com.example.lollipop.medicaldoctor.data.response;

/**
 * Created by Lollipop on 2017/5/11.
 */

public class RegisterResponse {
    /**
     * status : success
     * message : 用户名已存在
     * user : {"username":"username","password":"password"}
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
         * username : username
         * password : password
         */

        private String username;
        private String password;

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
    }
}
