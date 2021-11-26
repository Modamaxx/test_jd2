package com.example.demo.controller.restController;

import com.example.demo.dto.models.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.dto.filter.ESortDirection;
import com.example.demo.dto.filter.ProductFilter;
import com.example.demo.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("api/product")
public class ProductRestController {
    private final IProductService productService;

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "size", required = false) Integer size,
                                                @RequestParam(value = "name", required = false) String name) {

        ProductFilter filter = new ProductFilter();
        filter.setPage(page);
        filter.setSize(size);
        filter.setName(name);
        filter.setSortDirection(ESortDirection.DESC);

        Page<Product> products = productService.getAll(filter);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long id) {
        Product product = productService.get(id);
        long microseconds= product.getDataUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();


        ProductDto productDto= new ProductDto();
        productDto.setProduct(product);
        productDto.setMicroseconds(microseconds);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@RequestBody Product updateProduct, @PathVariable Long id,
                                    @PathVariable("dt_update") long dtUpdate) {

        productService.update(updateProduct, id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable("dt_update") long dtUpdate) {
        productService.delete(id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
