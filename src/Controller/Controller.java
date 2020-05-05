package Controller;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

import InventoryCategory.CategoryData;
import InventoryCategory.CategoryModel;
import InventoryCategory.CategoryServices;
import InventoryProduct.ProductModel;
import InventoryProduct.ProductServices;
import InventoryProduct.ProductsData;
import inventoryCustomer.CustomerData;
import inventoryCustomer.CustomerModel;
import inventoryCustomer.CustomersServices;
import inventoryPurchase.PurchaseData;
import inventoryPurchase.PurchaseModel;
import inventoryPurchase.PurchaseServices;
import inventorySales.SalesData;
import inventorySales.SalesModel;
import inventorySales.SalesServices;
import inventoryUsers.UsersData;
import inventoryUsers.UsersModel;
import inventoryUsers.UsersServices;

@Path("/inventory")
public class Controller {
	@GET
	@Path("/category")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCategory()
	{
		CategoryServices service = new CategoryServices();
		Gson gson = new Gson();
		CategoryData details = service.getCategory();
		return gson.toJson(details);
	}
	
	@GET
	@Path("/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCategoryData(@PathParam("id") int id)
	{
		CategoryServices service = new CategoryServices();
		Gson gson = new Gson();
		CategoryModel details = service.getCategoryData(id);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/category")
	@Produces(MediaType.APPLICATION_JSON)
	public String addCategoryData(@QueryParam("id")int ids,@QueryParam("cat_name") String cat_name, @QueryParam("description") String description, @QueryParam("user_id") int user_id) {
		CategoryServices service = new CategoryServices();
		Gson gson = new Gson();
		CategoryModel details = service.addCategoryData(ids, cat_name, description,user_id);
		return gson.toJson(details);
	}
	
	@PUT
	@Path("/category")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCategoryData(@QueryParam("id") int id,@QueryParam("cat_name") String cat_name, @QueryParam("description") String description,@QueryParam("user_id") int user_id) {
		CategoryServices service = new CategoryServices();
		Gson gson = new Gson();
		CategoryModel details = service.updateCategoryData(id, cat_name, description, user_id);
		return gson.toJson(details);
	}
	
	@DELETE
	@Path("/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCategoryData(@PathParam("id") int id) {
		CategoryServices service = new CategoryServices();
		Gson gson = new Gson();
		CategoryModel details = service.deleteCategoryData(id);
		return gson.toJson(details);
	}
	
	
	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProduct() {
		ProductServices service = new ProductServices();
		Gson gson = new Gson();
		ProductModel details = service.getProduct();
		return gson.toJson(details);
	}
	
	@GET
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductData(@PathParam("id") int id) {
		ProductServices service = new ProductServices();
		Gson gson = new Gson();
		ProductsData details = service.getProductdata(id);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/product")
	@Produces(MediaType.APPLICATION_JSON)
	public String addProduct(@QueryParam("name") String name, @QueryParam("price") Double price, @QueryParam("category") int category,
			@QueryParam("cost") Double cost, @QueryParam("user_id") int user_id) {
		ProductServices service = new ProductServices();
		Gson gson = new Gson();
		ProductsData details= service.addProduct(name, price, category, cost, user_id);
		return gson.toJson(details);
	}
	
	@PUT
	@Path("/product")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProduct(@QueryParam("name") String name, @QueryParam("price") Double price, @QueryParam("category") int category, @QueryParam("cost") Double cost,@QueryParam("user_id") int user_id, @QueryParam("id") int id) {
		ProductServices service = new ProductServices();
		Gson gson = new Gson();
		ProductsData details = service.updateProduct( name, price, category, cost, user_id, id);
		return gson.toJson(details);
	}
	
	@DELETE
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(@PathParam("id") int id) {
		ProductServices service = new ProductServices();
		Gson gson = new Gson();
		ProductsData details = service.deleteProduct(id);
		return gson.toJson(details);
		}
	
	
	@GET
	@Path("/purchase")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPurchase() {
		PurchaseServices service = new PurchaseServices();
		Gson gson = new Gson();
		PurchaseModel details = service.getPurchase();
		return gson.toJson(details);
	}
	
	@GET
	@Path("/purchase/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPurchaseData(@PathParam("id") int id) {
		PurchaseServices service = new PurchaseServices();
		Gson gson = new Gson();
		PurchaseData details = service.getPurchaseData(id);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/purchase")
	@Produces(MediaType.APPLICATION_JSON)
	public String addPurchase(@QueryParam("product")int product, @QueryParam("quantity") int quantity, @QueryParam("user_id") int user_id) {
		PurchaseServices service = new PurchaseServices();
		Gson gson = new Gson();
		PurchaseData details = service.addPurchase(product, quantity, user_id);
		return gson.toJson(details);	
	}
	
	@PUT
	@Path("/purchase")
	@Produces(MediaType.APPLICATION_JSON)
	public String updatePurchase(@QueryParam("product")int product, @QueryParam("quantity") int quantity, @QueryParam("user_id") int user_id,@QueryParam("id") int id) {
		PurchaseServices service = new PurchaseServices();
		Gson gson = new Gson();
		PurchaseData details = service.updatePurchase(product, quantity, user_id, id);
		return gson.toJson(details);	
	}
	
	@DELETE
	@Path("/purchase/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePurchaseData(@PathParam("id") int id) {
		PurchaseServices service = new PurchaseServices();
		Gson gson = new Gson();
		PurchaseData details = service.deletePurchase(id);
		return gson.toJson(details);	
	}
	
	
	@GET
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomers() {
		CustomersServices service = new CustomersServices();
		Gson gson = new Gson();
		CustomerModel details = service.getCustomers();
		return gson.toJson(details);
	}
	
	@GET
	@Path("/customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomersData(@PathParam("id") int id) {
		CustomersServices service = new CustomersServices();
		Gson gson = new Gson();
		CustomerData details = service.getCustomerData(id);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	public String addCustomer(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, @QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("address") String address, @QueryParam("user_id") int user_id) {
		CustomersServices service = new CustomersServices();
		Gson gson = new Gson();
		CustomerData details = service.addCustomer(firstname, lastname, email, phone, address, user_id);
		return gson.toJson(details);
	}
	
	@PUT
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCustomer(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, @QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("address") String address,@QueryParam("user_id") int user_id,@QueryParam("id") int id) {
		CustomersServices service = new CustomersServices();
		Gson gson = new Gson();
		CustomerData details = service.updateCustomer(firstname, lastname, email, phone, address,user_id, id);
		return gson.toJson(details);
	}
	
	@DELETE
	@Path("/customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCustomer(@PathParam("id") int id) {
		CustomersServices service = new CustomersServices();
		Gson gson = new Gson();
		CustomerData details = service.deleteCustomer(id);
		return gson.toJson(details);
	}
	
	@GET
	@Path("/sales")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSales() {
		SalesServices service = new SalesServices();
		Gson gson = new Gson();
		SalesModel details = service.getSales();
		return gson.toJson(details);
	}
	
	@GET
	@Path("/sale/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSalesData(@PathParam("id") int id) {
		SalesServices service = new SalesServices();
		Gson gson = new Gson();
		SalesData details = service.getSaleData(id);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/sale")
	@Produces(MediaType.APPLICATION_JSON)
	public String addSale(@QueryParam("product_id") int product_id, @QueryParam("customer_id") int customer_id, @QueryParam("quantity") int quantity, @QueryParam("user_id") int user_id) {
		SalesServices service = new SalesServices();
		Gson gson = new Gson();
		SalesData details  = service.addSale(product_id, customer_id, quantity, user_id);
		return gson.toJson(details);
	}
	
	@PUT
	@Path("/sale")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateSale(@QueryParam("id") int id, @QueryParam("product_id") int product_id, @QueryParam("quantity") int quantity, @QueryParam("customer_id") int customer_id, @QueryParam("user_id") int user_id ) {
		SalesServices service = new SalesServices();
		Gson gson = new Gson();
		SalesData details = service.updateSale(product_id, quantity,user_id, id);
		return gson.toJson(details);
	}
	
	@DELETE
	@Path("/sale/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteSale(@PathParam("id") int id) {
		SalesServices service = new SalesServices();
		Gson gson = new Gson();
		SalesData details = service.deleteSale(id);
		return gson.toJson(details);
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() {
		UsersServices service = new UsersServices();
		Gson gson = new Gson();
		UsersModel details = service.getUsers();
		return gson.toJson(details);
	}
	
	@GET
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsersData(@PathParam("id") int id) {
		UsersServices service = new UsersServices();
		Gson gson = new Gson();
		UsersData details = service.getUserData(id);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public String processUserLogin(@QueryParam("username") String username,@QueryParam("password") String password) {
		UsersServices service = new UsersServices();
		Gson gson = new Gson();
		UsersData details = service.processUserLogin(username, password);
		return gson.toJson(details);
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String addUser(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, @QueryParam("email") String email,@QueryParam("phone") String phone ,@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("role") String role, @QueryParam("company") String company) {
		UsersServices service = new UsersServices();
		Gson gson = new Gson();
		UsersData details = service.addUser(firstname, lastname, email, phone, username, password, role, company);
		return gson.toJson(details);
	}
	
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, @QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("role") String role, @QueryParam("company") String company, @QueryParam("id") int id) {
		UsersServices service = new UsersServices();
		Gson gson = new Gson();
		UsersData details = service.updateUsers(firstname, lastname, email, phone, username, password, role, company, id);
		return gson.toJson(details);
	}
	
	@DELETE
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("id") int id) {
		UsersServices service = new UsersServices();
		Gson gson = new Gson();
		UsersData details = service.deleteUser(id);
		return gson.toJson(details);
	}
	
	
	
}
