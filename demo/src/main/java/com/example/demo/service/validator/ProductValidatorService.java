package com.example.demo.service.validator;

import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.dto.filter.ProductFilter;
import com.example.demo.model.Product;
import com.example.demo.service.UserService;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IProfileService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Aspect
@Component
public class ProductValidatorService {

    private final IProductService productService;

    public ProductValidatorService(IProductService productService) {
        this.productService = productService;
    }

    @Before("execution(* com.example.demo.service.ProductService.getAll(..))")
    public void getAll(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ProductFilter productFilter= (ProductFilter) args[0];
        if(productFilter.getPage()==null||productFilter.getSize()==null){
            throw new IllegalArgumentException("Не указаны обязательные параметры для пагинации");
        }

    }


    @Before("execution(* com.example.demo.service.ProductService.save(..))")
    public void save(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Product product = (Product) args[0];

        if (product.getCalories() <= 0) {
            throw new IllegalArgumentException("Данные калории введены неверено");
        }
        if (product.getCarbohydrates() <= 0) {
            throw new IllegalArgumentException("Данные углеводов введены неверено");
        }
        if (product.getFats() <= 0) {
            throw new IllegalArgumentException("Данные жиров введены неверено");
        }
        if (product.getProteins() <= 0) {
            throw new IllegalArgumentException("Данные белков введены неверено");
        }
        if (product.getMass() <= 0) {
            throw new IllegalArgumentException("Данные массы продукта введены неверено");
        }

        if (product.getName() == null || product.getName().equals("")) {
            throw new IllegalArgumentException("Имя продукта введено неверено");
        }
        if (product.getCompany() == null || product.getCompany().equals("")) {
            throw new IllegalArgumentException("Название компании введено неверено");
        }

    }

    @Before("execution(* com.example.demo.service.ProductService.update(..))")
    public void update(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Product updateProduct = (Product) args[0];
        Long idProduct = (Long) args[1];
        long dtUpdate = (long) args[2];

        Product product = productService.get(idProduct);
        long dtBd = product.getDataUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

        if (updateProduct.getCalories() <= 0) {
            throw new IllegalArgumentException("Данные калории введены неверено");
        }
        if (updateProduct.getCarbohydrates() <= 0) {
            throw new IllegalArgumentException("Данные углеводов введены неверено");
        }
        if (updateProduct.getFats() <= 0) {
            throw new IllegalArgumentException("Данные жиров введены неверено");
        }
        if (updateProduct.getProteins() <= 0) {
            throw new IllegalArgumentException("Данные белков введены неверено");
        }
        if (updateProduct.getMass() <= 0) {
            throw new IllegalArgumentException("Данные массы продукта введены неверено");
        }

        if (updateProduct.getName() == null || updateProduct.getName().equals("")) {
            throw new IllegalArgumentException("Имя продукта введено неверено");
        }
        if (updateProduct.getCompany() == null || updateProduct.getCompany().equals("")) {
            throw new IllegalArgumentException("Название компании введено неверено");
        }

    }

    @Before("execution(* com.example.demo.service.ProductService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long idProduct = (Long) args[0];
        long dtUpdate = (long) args[1];

        if (dtUpdate <= 0) {
            throw new IllegalArgumentException("время изменения неверного формата");
        }

        Product product = productService.get(idProduct);
        long dtBd = product.getDataUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (dtBd != dtUpdate) {
            throw new IllegalArgumentException("Данные в базе изменились обновите страницу");
        }

    }
}
