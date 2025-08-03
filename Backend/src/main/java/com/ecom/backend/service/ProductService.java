package com.ecom.backend.service;

import com.ecom.backend.model.Product;
import com.ecom.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product save(Product product, MultipartFile file) throws IOException {
        product.setImageName(file.getOriginalFilename());
        product.setImageType(file.getContentType());
        product.setImageData(file.getBytes());
        return productRepo.save(product);
    }

    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }


    public Product update(int id, Product product, MultipartFile image) throws IOException {
        product.setImageData(image.getBytes());
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        return productRepo.save(product);
    }

    public boolean deleteById(int id) {
        Product existingProduct = productRepo.findById(id).get();
        if (existingProduct != null) {
            productRepo.delete(existingProduct);
            return true;
        }
        return false;
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
