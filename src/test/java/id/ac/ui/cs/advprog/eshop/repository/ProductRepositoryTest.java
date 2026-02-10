package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindProductByIdNotFound() {
        Product foundProduct = productRepository.findById("gaada hihihihi");
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProductSuccessfully() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(50);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
        assertEquals("Sampo Cap Usep", result.getProductName());
        assertEquals(50, result.getProductQuantity());

        // cek beneran diupdate di repo apa kgk
        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("Sampo Cap Usep", foundProduct.getProductName());
        assertEquals(50, foundProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("gaada dawg");
        updatedProduct.setProductQuantity(50);

        Product result = productRepository.update("id random hihi", updatedProduct);

        assertNull(result);
    }

    @Test
    void testUpdateProductName() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Namabaruhihi");
        updatedProduct.setProductQuantity(100);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertEquals("Namabaruhihi", result.getProductName());
        assertEquals(100, result.getProductQuantity());
    }

    @Test
    void testUpdateProductQuantity() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testUpdateProductWithMultipleProducts() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang Updated");
        updatedProduct.setProductQuantity(150);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertEquals("Sampo Cap Bambang Updated", result.getProductName());
        assertEquals(150, result.getProductQuantity());

        // cek product 2 tetap ga berubah
        Product product2Check = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals("Sampo Cap Usep", product2Check.getProductName());
        assertEquals(50, product2Check.getProductQuantity());
    }

    @Test
    void testDeleteProductSuccessfully() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // delete produk yang ga ada harusnya ga throw exception
        productRepository.delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        // sampo cap bambang harusnya masih ada
        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(foundProduct);
    }

    @Test
    void testDeleteProductFromMultiple() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("lalala");
        product3.setProductName("Produk 3");
        product3.setProductQuantity(67);
        productRepository.create(product3);

        productRepository.delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        Product deletedProduct = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertNull(deletedProduct);

        Product product1Check = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(product1Check);

        Product product3Check = productRepository.findById("lalala");
        assertNotNull(product3Check);

        Iterator<Product> productIterator = productRepository.findAll();
        int count = 0;
        while (productIterator.hasNext()) {
            productIterator.next();
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void testDeleteAllProducts() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepository.delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductThenAddNewProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Product newProduct = new Product();
        newProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        newProduct.setProductName("Sampo Cap Usep");
        newProduct.setProductQuantity(50);
        productRepository.create(newProduct);

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundProduct);

        Product foundNewProduct = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals("Sampo Cap Usep", foundNewProduct.getProductName());
    }

    @Test
    void testUpdateThenDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang Updated");
        updatedProduct.setProductQuantity(150);
        productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundProduct);
    }

    @Test
    void testDeleteWithEmptyRepository() {
        // harusnya ga throw exception
        productRepository.delete("hihihi");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}
