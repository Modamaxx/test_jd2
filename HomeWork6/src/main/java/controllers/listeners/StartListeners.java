//package controllers.listeners;
//
//import model.AppParam;
//import service.api.ETypeStorage;
//import storage.Hibernate.HibernateUtil;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.time.LocalDateTime;
//
//@WebListener
//public class StartListeners implements ServletContextListener {
//    private final String TYPE_STORAGE_CONSTANT = "FILE";
//
//    public StartListeners() {
//    }
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        String typeStorage = sce.getServletContext().getInitParameter("storage");
//        ETypeStorage storageType = ETypeStorage.valueOfIgnoreCase(typeStorage);
//        AppParam.getInstance().setDepartmentStorage(storageType.getDepartmentStorage());
//        AppParam.getInstance().setEmployerStorage(storageType.getEmployerStorage());
//        AppParam.getInstance().setPositionStorage(storageType.getPositionStorage());
//
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        HibernateUtil.shutdown();
//    }
//
//
//}
