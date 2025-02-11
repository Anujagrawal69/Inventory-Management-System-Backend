In this Spring Boot project, I've created some backend components of Inventory Management System that consists of REST APIs with proper security configuration using Role Based Authentication.

If you want to try this then you have to main your database schema as well. Like without adding any ADMIN role in your Role table you can't create another role and unable to call the APIs wherever security is configured.
As I've used Role based authentication then you have to first add a user as ADMIN then you will configure all the stuff.

Below is the APIs information and the format of JSON object that you've to passed from the POSTMAN:

===============================USER SPECIFIC APIs==============================================

1. create user -> http://localhost:8080/api/ims/users/create-user
Authority: 'ADMIN'
JSON: 
{
    "username":"Sumit",
    "password":"Sumit",
    "roles": [
        "ROLE_MANAGER",
        "ROLE_INTERN"
    ]
}


2. get user by id -> http://localhost:8080/api/ims/users/{id}
Authority: 'ADMIN', 'MANAGER'


3. get all users -> http://localhost:8080/api/ims/users
Authority: 'ADMIN', 'MANAGER', 'INTERN'


4. update user by id -> http://localhost:8080/api/ims/users/update-user/{id}
Authority: 'ADMIN'
JSON: 
{
    "username":"Sumit",
    "roles": [
        "ROLE_MANAGER",
        "ROLE_INTERN"
    ]
}



5. delete user by id -> http://localhost:8080/api/ims/users/delete-user/{id}
Authority: 'ADMIN'



===============================ROLE SPECIFIC APIs==============================================

1. create role -> http://localhost:8080/api/ims/roles/create-role
Authority: ADMIN
JSON:
{
    "name": "ROLE_PUBLIC"
}


2. get role id by role name -> http://localhost:8080/api/ims/roles/{role-name}
Authority: ADMIN, MANAGER, INTERN


3. get all roles -> http://localhost:8080/api/ims/roles
Authority: ADMIN, MANAGER, INTERN


4. delete role by id -> http://localhost:8080/api/ims/roles/delete-role/{id}
Authority: ADMIN



===============================PRODUCT SPECIFIC APIs==============================================

1. create product -> http://localhost:8080/api/ims/products/create-product
Authority: 'ADMIN', 'MANAGER'
JSON:
{
    "name":"Samsung Smart TV 42 inches Poled display",
    "price":12000,
    "quantity":50,
    "categoryId":1
}


2. find products by category id -> http://localhost:8080/api/ims/products/{id}
Authority: 'ADMIN', 'MANAGER', 'INTERN'


3. get all products -> http://localhost:8080/api/ims/products
Authority: 'ADMIN', 'MANAGER', 'INTERN'


4. delete product by product id -> http://localhost:8080/api/ims/products/delete-product/{id}
Authority: 'ADMIN', 'MANAGER'


5. update product by product id -> http://localhost:8080/api/ims/products/update-product/{id}
Authority: 'ADMIN', 'MANAGER'
JSON:
{
    "name":"Samsung Smart TV 42 inches Poled display",
    "price":12000,
    "quantity":50,
    "categoryId":1
}



===============================CATEGORY SPECIFIC APIs==============================================

1. create category -> http://localhost:8080/api/ims/categories/create-category
Authority: 'ADMIN', 'MANAGER'
JSON: 
{
    "name":"Footwear"
}


2. get category by id -> http://localhost:8080/api/ims/categories/{id}
Authority: 'ADMIN', 'MANAGER', 'INTERN'


3. get all categories -> http://localhost:8080/api/ims/categories
Authority: 'ADMIN', 'MANAGER', 'INTERN'


4. update category by id -> http://localhost:8080/api/ims/categories/update-category/{id}
Authority: 'ADMIN', 'MANAGER', 'INTERN'
JSON:
{
    "name":"Furniture"
}


5. delete category by id -> http://localhost:8080/api/ims/categories/delete-category/{id}
Authority: 'ADMIN', 'MANAGER'



===============================ORDER SPECIFIC APIs==============================================

1. create order -> http://localhost:8080/api/ims/orders/create-order
Authority: 'ADMIN', 'MANAGER'
JSON: 
{
  "userId": 1,
  "orderItems": [
    {
      "product": {
        "id": 1
      },
      "quantity": 2
    },
    {
      "product": {
        "id": 2
      },
      "quantity": 1
    }
  ]
}


2. get order by id -> http://localhost:8080/api/ims/orders/{id}
Authority: 'ADMIN', 'MANAGER', 'INTERN'


3. get all orders -> http://localhost:8080/api/ims/orders
Authority: 'ADMIN', 'MANAGER', 'INTERN'


4. delete order by id -> http://localhost:8080/api/ims/orders/delete-order/{id}
Authority: 'ADMIN', 'MANAGER'


If anyone want to get more information you please contact: 
Email: rksanuj678@gmail.com
