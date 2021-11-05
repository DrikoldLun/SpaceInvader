package edu.uchicago.gerber._06design.R12_15;

public class Invoice {
    Customer customer;
    Product[] products;
}

class Address {}

class Customer {
    Address billingaddress;
    Address shippingaddress;
}

class Product {
    Customer customer;
}
