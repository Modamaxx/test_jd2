package model;

import service.api.IUserService;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

import java.time.LocalDateTime;

public class AppParam {
    private static AppParam instance=new AppParam();;
    private IUserStorage userStorage;
    private ILetterStorage letterStorage;
    private LocalDateTime startTime;
    private AppParam(){

    }

    public static void setInstance(AppParam instance) {
        AppParam.instance = instance;
    }

    public IUserStorage getUserStorage() {
        return userStorage;
    }

    public void setUserStorage(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public ILetterStorage getLetterStorage() {
        return letterStorage;
    }

    public void setLetterStorage(ILetterStorage letterStorage) {
        this.letterStorage = letterStorage;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public static AppParam getInstance(){
        return instance;
    }

}
