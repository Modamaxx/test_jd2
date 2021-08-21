package Task3.model;

public class Letter {
 private    String data;
 private    String user;
 private    String message;

    public Letter(String data, String user, String message) {
        this.data = data;
        this.user = user;
        this.message = message;
    }
    public Letter(){}


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
