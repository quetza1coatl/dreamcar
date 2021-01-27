# dreamcar
Spring course final project

### pre launch actions  
1. Download code to local computer.
2. If you going to start app at win7, set `nodeVersion` in `pom.xml` to `v11.3.0`.  
3. Use `mvn clean package` to create jars for all services: eureka-server, inventory-system, auction-platform.  
### startup sequence  
1. Go to root directory with docker-compose.yml file.  
2. Run docker-compose up command to start application.  
3. Enjoy.  
eureka-server: http://localhost:8000/  
auction-platform with UI: http://localhost:8082/  
inventory-system: http://localhost:8081/components/  
### credentials  
 - for auction-platform(login/pass): customer/customer or supplier/supplier  
 - for inventory-system: customer/customer  
