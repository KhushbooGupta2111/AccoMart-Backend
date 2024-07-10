package com.accomart.backend.controllers.addresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.accomart.backend.entities.Category;
import com.accomart.backend.entities.Product;
import com.accomart.backend.entities.dto.UpdateProduct;
import com.accomart.backend.entities.dto.ViewProduct;
import com.accomart.backend.services.ProductService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/AdminDashboard")
public class ProductController {

	@Autowired
	private ProductService productService;


	@GetMapping("/Products/CategoryId={id}")
	public CompletableFuture<List<Product>> getAllProducts(@PathVariable int id, @RequestParam String orderBy) {
		return productService.getAllProductsByCategoryAsync(id, orderBy);
	}

	@GetMapping("/ProductsByPageNo")
	public CompletableFuture<List<Product>> getProductsByPageNo(@RequestParam int id, @RequestParam int pageNo) {
		final int pageSize = 10;
		return productService.getProductsByPageNoAsync(id, pageNo, pageSize);
	}

	@GetMapping("/Products")
	public CompletableFuture<List<Product>> getProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/GetAllProductsPagewise")
	public CompletableFuture<List<Product>> getAllProductsPagewise(@RequestParam int pageNo) {
		int pageSize = 20;
		return productService.getAllProductsPagewiseAsync(pageNo, pageSize);
	}

	@GetMapping("/Products/SearchBy={prefix}")
	public CompletableFuture<List<Product>> getProductBySearchName(@PathVariable String prefix) {
		return productService.getProductBySearchNameAsync(prefix);
	}

	@GetMapping("/Products/CategoryName={name}")
	public CompletableFuture<List<Product>> getProductsByCategoryName(@PathVariable String name) {
		return productService.getProductsByCategoryNameAsync(name);
	}

	@GetMapping("/Product/{id}")
	public CompletableFuture<Product> getProductById(@PathVariable int id) {
		return productService.getProductByIdAsync(id);
	}

	@GetMapping("/GetAllCategories")
	public CompletableFuture<List<Category>> getAllCategories() {
		return productService.getAllCategoriesAsync();
	}

	@GetMapping("/Category/{id}")
	public CompletableFuture<Category> getCategoryById(@PathVariable int id) {
		return productService.getCategoryByIdAsync(id);
	}

	@GetMapping("/Category/name/{name}")
	public CompletableFuture<Category> getCategoryByName(@PathVariable String name) {
		return productService.getCategoryByNameAsync(name);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/Category/Create")
	public CompletableFuture<Category> createCategory(@RequestBody String categoryName) {
		return productService.createCategoryAsync(categoryName);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/Product/Create")
	public CompletableFuture<Product> createProduct(@RequestBody ViewProduct productDto) {
		return productService.createProductAsync(productDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/Update/Category")
	public CompletableFuture<Category> updateCategory(@RequestParam int id,
			@RequestParam String newCategoryName) {
		return productService.updateCategoryAsync(id, newCategoryName);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/Update/Product/{productId}")
	public CompletableFuture<Product> updateProduct(@PathVariable int productId,
			@RequestBody UpdateProduct productDto) {
		return productService.updateProductAsync(productId, productDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Delete/Category/{categoryId}")
    public CompletableFuture<Void> deleteCategory(@PathVariable int categoryId) {
        return productService.deleteCategoryAsync(categoryId);
    }

	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Delete/Product/{productId}")
    public CompletableFuture<Void> deleteProduct(@PathVariable int productId) {
        return productService.deleteProductAsync(productId);
    }
}
