/**
 * 
 */
package mx.tec.web.lab.controller;


import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.lab.manager.ProductManager;
import mx.tec.web.lab.entity.Product;

@RestController
public class ProductController {
	@Resource
	private ProductManager productManager;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productManager.getProducts();
		return  new ResponseEntity<>(products, HttpStatus.OK);
	}

	
	  @GetMapping("/products/{id}") public ResponseEntity<Product>
	  getProduct(@PathVariable(value = "id") long id) { ResponseEntity<Product>
	  responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  
	  Optional<Product> product = productManager.getProduct(id);
	  
	  if (product.isPresent()) { responseEntity = new
	  ResponseEntity<>(product.get(), HttpStatus.OK); }
	  
	  return responseEntity; }
	 
	
	  @PostMapping("/products")
		public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
			Product product = productManager.addProduct(newProduct);			
			return  new ResponseEntity<>(product, HttpStatus.CREATED);
		}


	
	  @PutMapping("/products/{id}")
		public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") long id, @RequestBody Product modifiedProduct) {
			ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			Optional<Product> product = productManager.getProduct(id);
			
			if (product.isPresent()) {
				productManager.updateProduct(id, modifiedProduct);
				responseEntity = new ResponseEntity<>(HttpStatus.OK);
			}
			
			return responseEntity;
		}

	
	  @DeleteMapping("/products/{id}")
		public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") long id) {
			ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			Optional<Product> product = productManager.getProduct(id);
			
			if (product.isPresent()) {
				productManager.deleteProduct(product.get());
				responseEntity = new ResponseEntity<>(HttpStatus.OK);
			}
			
			return responseEntity;
		}
	  
	  
		@GetMapping(value="/products", params="search")
		public ResponseEntity<List<Product>> getProducts(@RequestParam String search) {
			List<Product> products = productManager.getProducts(search);
			return new ResponseEntity<>(products, HttpStatus.OK);
		}


}


