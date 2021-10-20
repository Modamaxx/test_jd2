package config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import service.CalculationsService;
import service.DepartmentService;
import service.EmployerService;
import service.PositionService;
import service.api.ICalculationService;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

@Configuration
public class ServiceConfig {

    @Bean
    public IEmployerService employerService(IEmployerStorage employerStorage, IDepartmentStorage departmentStorage,
                                            IPositionStorage positionStorage, ICalculationService calculationsService){
        return new EmployerService(employerStorage,departmentStorage,positionStorage,calculationsService);
    }

    @Bean
    public ICalculationService calculationService(IDepartmentStorage iDepartmentStorage,
                                                  IPositionStorage iPositionStorage,
                                                  IEmployerStorage iEmployerStorage){
        return new CalculationsService(iDepartmentStorage,iPositionStorage,iEmployerStorage);
    }

    @Bean
    public IDepartmentService departmentService(IDepartmentStorage departmentStorage){
        return new DepartmentService(departmentStorage);
    }

    @Bean
    public IPositionService positionService(IPositionStorage positionStorage){
        return new PositionService(positionStorage);
    }
}
