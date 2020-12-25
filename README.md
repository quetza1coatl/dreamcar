# dreamcar
Spring course final project

### pre launch actions  
1. If you going to start app at win7, set `nodeVersion` in `pom.xml` to `v11.3.0`.  
2. Use `mvn clean package` to package auction-platform application.  
### startup sequence  
1. Start eureka-server service  
2. Start auction-platform and inventory-system services  
3. Enjoy  
eureka-server: http://localhost:8000/  
auction-platform with UI: http://localhost:8082/  
inventory-system: http://localhost:8081/components/  
### credentials  
 - for auction-platform(login/pass): customer/customer or supplier/supplier  
 - for inventory-system: customer/customer  
