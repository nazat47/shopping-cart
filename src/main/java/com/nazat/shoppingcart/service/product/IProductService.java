package com.nazat.shoppingcart.service.product;

import com.nazat.shoppingcart.dto.ProductDto;
import com.nazat.shoppingcart.entities.Product;
import com.nazat.shoppingcart.request.AddProductRequest;
import com.nazat.shoppingcart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest product, Long productId);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(String category);

    List<Product> getAllProductsByBrand(String brand);

    List<Product> getAllProductsByCategoryAndBrand(String category, String brand);

    List<Product> getAllProductsByName(String name);

    List<Product> getAllProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProductDtos(List<Product> products);

    ProductDto convertToDto(Product product);
}
