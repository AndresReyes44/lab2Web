package mx.tec.web.lab.manager;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mx.tec.web.lab.entity.Product;
import mx.tec.web.lab.entity.Sku;
import mx.tec.web.lab.repository.ProductRepository;

/**
 * @author Propetario
 *
 *	
 */
@Service
public class ProductManager {
	@Resource
	ProductRepository productRepository;

	/**
	 * Get all the products of the DB
	 * @return a list with object of type product
	 */
	public List<Product> getProducts() {
			return productRepository.findAll();
		}
	
/**
 * Get a product by a determined id
 * @param id of the product to find
 * @return if found the product otherwise a BAD REQUEST
 */
		public Optional<Product> getProduct(final long id) {
			return productRepository.findById(id);
			
		}
		
	/**
	 * Add a new produt to the list based on a given product
	 * @param newProduct Product to add
	 * @return An savedProduct containing the new peoduct saved on the DB
	 */
	public Product addProduct(final Product newProduct) {
		
		for(final Sku newSku: newProduct.getChildSkus()) {
			newSku.setParentProduct(newProduct); 
		}
			return productRepository.save(newProduct);
		}
/**
 * Update a product to the list based on given params
 * 	@param modifiedProduct Product with the json to be updated
 * @param id the id of the product to be modified
 * @return 
 */
	public void updateProduct(final long id, final Product modifiedProduct) {
		if (modifiedProduct.getId() == id) {
			for (final Sku modifiedSku : modifiedProduct.getChildSkus()) {
				modifiedSku.setParentProduct(modifiedProduct);
			}			
			
			productRepository.save(modifiedProduct);
		}
	}	
	
	/**
	 * Delete a product with a given product
	 * @param existingProduct Product to be deleted 
	 * @return
	 */

	public void deleteProduct(final Product existingProduct) {
		productRepository.delete(existingProduct);
	}
	
	/**
	 * Retrieve an specific product based on a given product id
	 * @param pattern Pattern to search
	 * @return a list of products with the same pattern
	*/
	public List<mx.tec.web.lab.entity.Product> getProducts(final String pattern) {
		return productRepository.findByNameLike(pattern);
	}

	
}


