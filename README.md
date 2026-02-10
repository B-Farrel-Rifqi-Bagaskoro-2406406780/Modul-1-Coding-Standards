# Module 1
### Reflection 1
Adding the new edit and delete product feature is quite interesting. I have never worked with using Java to do MVC work. Nonetheless, there were quite a few errors originally, such as forgetting to include ProductId to edit a product. Everything else went fine, I copy-pasted the original CreateProduct.html and slightly edited it for EditProduct.html.

### Reflection 2
1. To put it bluntly, unit tests are tedious, but is a must-have habit for a developer. The amount of unit tests depends on how large a class is. It is also important that every variable is covered by the tests. Also, 100% code coverage does NOT mean that the code has no bugs or errors. For example, quantity field has no "integer" restriction (yet).
2. The new code WILL reduce the code quality. By continuing with this trend of creating new class for such features, there will be TONS of classes that needs the developers' attention. It is time consuming and VERY HARD when a new developer joins the project. <br>
t.l.d.r. <i>verifying quantity in the product list</i> is a very niche test, just verify it by adding more tests into existing classes such as ProductRepositoryTest.java.