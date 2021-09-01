package controllers.web.listeners;

import model.AppParam;
import service.UserService;
import service.api.ETypeStorage;
import storage.FileUserStorage;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class StartListeners implements ServletContextListener {
    private final String TYPE_STORAGE_CONSTANT = "FILE";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String typeStorage = sce.getServletContext().getInitParameter("storage");
        ETypeStorage storageType = ETypeStorage.valueOfIgnoreCase(typeStorage);
        AppParam.getInstance().setStartTime(LocalDateTime.now());
        AppParam.getInstance().setUserStorage(storageType.getUserStorage());
        AppParam.getInstance().setLetterStorage(storageType.getLetterStorage());

        if (typeStorage.equals(TYPE_STORAGE_CONSTANT)) {
            FileUserStorage.getInstance().init();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
