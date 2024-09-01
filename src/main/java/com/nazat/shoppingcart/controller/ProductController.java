package com.nazat.shoppingcart.controller;

import com.nazat.shoppingcart.dto.ProductDto;
import com.nazat.shoppingcart.entities.Product;
import com.nazat.shoppingcart.exceptions.AlreadyExistsException;
import com.nazat.shoppingcart.exceptions.ResourceNotFoundException;
import com.nazat.shoppingcart.request.AddProductRequest;
import com.nazat.shoppingcart.request.ProductUpdateRequest;
import com.nazat.shoppingcart.response.ApiResponse;
import com.nazat.shoppingcart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllProduct() {
        List<Product> product = iProductService.getAllProducts();
        List<ProductDto> convertedProduct = iProductService.getConvertedProductDtos(product);
        return ResponseEntity.ok(new ApiResponse("Found all", convertedProduct));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = iProductService.getProductById(id);
            ProductDto productDto = iProductService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Found", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product addedProduct = iProductService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Successfully added", addedProduct));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest product, @PathVariable Long id) {
        try {
            Product updatedProduct = iProductService.updateProduct(product, id);
            ProductDto productDto = iProductService.convertToDto(updatedProduct);
            return ResponseEntity.ok(new ApiResponse("Updated success", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            iProductService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Deleted success", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/getByBrandAndName")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = iProductService.getAllProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProduct = iProductService.getConvertedProductDtos(products);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping("/getByCategoryAndBrand")
    public ResponseEntity<ApiResponse> getProductByBrandAndCategory(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> products = iProductService.getAllProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProduct = iProductService.getConvertedProductDtos(products);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        try {
            List<Product> products = iProductService.getAllProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProduct = iProductService.getConvertedProductDtos(products);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/getByBrand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        try {
            List<Product> products = iProductService.getAllProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProduct = iProductService.getConvertedProductDtos(products);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String category) {
        try {
            List<Product> products = iProductService.getAllProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProduct = iProductService.getConvertedProductDtos(products);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/countByBrandAndName")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = iProductService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Found products count", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
