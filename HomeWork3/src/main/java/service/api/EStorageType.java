package service.api;

public enum EStorageType{
    COOKIES(CookiesHandler.getInstance()),
    SESSION(SessionHandler.getInstance())
    ;
    private final IHandleRequest handler;
    EStorageType(IHandleRequest handler) {
        this.handler=handler;
    }

    public static EStorageType valueOfIgnoreCase(String name){
        for (EStorageType value : values()) {
            if(value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        throw new IllegalArgumentException("Неизвесный тип хранилища");
    }

    public IHandleRequest getHandler(){
        return handler;
    }
}
