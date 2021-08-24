package service.api;

import storage.FileLetterStorage;
import storage.FileUserStorage;
import storage.MemoryLetterStorage;
import storage.MemoryUserStorage;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

public enum ETypeStorage {
    MEMORY(MemoryUserStorage.getInstance(), MemoryLetterStorage.getInstance()),
    FILE(FileUserStorage.getInstance(), FileLetterStorage.getInstance()),
    ;
    private final IUserStorage userStorage;
    private final ILetterStorage letterStorage;

    ETypeStorage(IUserStorage userStorage, ILetterStorage letterStorage) {
        this.userStorage=userStorage;
        this.letterStorage=letterStorage;
    }

    public static ETypeStorage valueOfIgnoreCase(String name){
        for (ETypeStorage value : values()) {
            if(value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        throw new IllegalArgumentException("Неизвесный тип хранилища");
    }



    public  IUserStorage getUserStorage(){
        return userStorage;
    }
    public  ILetterStorage getLetterStorage(){
        return this.letterStorage;
    }
}
