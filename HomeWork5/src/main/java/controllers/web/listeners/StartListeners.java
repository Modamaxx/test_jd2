package controllers.web.listeners;

import model.AppParam;
import service.UserService;
import service.api.ETypeStorage;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class StartListeners implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String typeStorage = sce.getServletContext().getInitParameter("storage");
        ETypeStorage storageType = ETypeStorage.valueOfIgnoreCase(typeStorage);
        AppParam.getInstance().setStartTime(LocalDateTime.now());
        AppParam.getInstance().setUserStorage(storageType.getUserStorage());
        AppParam.getInstance().setLetterStorage(storageType.getLetterStorage());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
