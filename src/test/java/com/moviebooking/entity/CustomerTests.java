//package com.moviebooking.entity;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import org.junit.jupiter.api.Test;
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class CustomerTests {
//
//    Customer customer = new Customer();
//    @Test
//    void setCustomerFirstName(){
//        customer.setFirstName("firstName");
//        assertEquals("firstName",customer.getFirstName());
//    }
//    @Test
//    void setCustomerLastName(){
//        customer.setLastName("lastName");
//        assertEquals("lastName",customer.getLastName());
//    }
//    @Test
//    void setCustomerEmailIdTest(){
//        customer.setEmailId("user@email.com");
//        assertEquals("user@email.com",customer.getEmailId());
//    }
//    @Test
//    public void setCustomerLoginId(){
//        customer.setLoginId(1);
//        assertEquals(1,customer.getLoginId());
//    }
//    @Test
//    void setCustomerUserName(){
//        customer.setUserName("userName");
//        assertEquals("userName",customer.getUserName());
//    }
//    @Test
//    void setCustomerPassword(){
//        customer.setPassword("pass");
//        assertEquals("pass",customer.getPassword());
//    }
//    @Test
//    void setCustomerConfirmPassword(){
//        customer.setConfirmPassword("pass");
//        assertEquals("pass",customer.getConfirmPassword());
//    }
//    @Test
//    public void setCustomerContactNo(){
//        customer.setContactNo(1111111111);
//        assertEquals(1111111111,customer.getContactNo());
//    }
//
//    @Test
//    void setCustomerRole(){
//        customer.setRole("user");
//        assertEquals("user",customer.getRole());
//    }
//    @Test
//    void notBlankTest(){
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Customer user1 = new Customer("","","",0,"","","",0,"");
//        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(user1);
//        assertEquals(6,constraintViolations.size());
//    }
//    @Test
//    void notNullTest(){
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Customer user1 = new Customer(null,null,null,0,null,null,null,0,null);
//        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(user1);
//        assertEquals(12,constraintViolations.size());
//    }
//
//}
