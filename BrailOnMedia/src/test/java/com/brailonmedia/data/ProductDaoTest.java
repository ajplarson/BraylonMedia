package com.brailonmedia.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jackelder
 */

public class ProductDaoTest {
    
    @Autowired
    ProductDao productDao;
    
    public ProductDaoTest() {
    }
    
    @BeforeEach
    public void setUp() {
        productDao.deleteAll();
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
