package com.example.demo.service;

import com.example.demo.dao.api.IProductDao;
import com.example.demo.model.Product;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.User;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IUserService;
import com.example.security.UserHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService implements IProductService {

    private final IProductDao productDao;
    private final UserHolder userHolder;
    private final IUserService userService;


    public ProductService(IProductDao productDao, UserHolder userHolder, IUserService userService) {
        this.productDao = productDao;
        this.userHolder = userHolder;
        this.userService = userService;
    }


    public Page<Product> getAll(BaseFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        return productDao.findAll(pageable);
    }

    @Override
    public void save(Product product) {
        LocalDateTime time = LocalDateTime.now();
        product.setDataCreate(time);
        product.setDataUpdate(time);

        String login = userHolder.getAuthentication().getName();
        User user = userService.findByLogin(login);
        product.setUser(user);

        productDao.save(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product по id не найден"));
    }


    @Override
    public void update(Product updateProduct,Long idProduct, long dtUpdate) {
        Product p = get(idProduct);

        updateProduct.setDataCreate(p.getDataCreate());
        updateProduct.setDataUpdate(LocalDateTime.now());
        updateProduct.setId(idProduct);
        updateProduct.setUser(p.getUser());

        productDao.save(updateProduct);
    }


    @Override
    public void delete(Long id, long dtUpdate) {
        productDao.deleteById(id);
    }
}
