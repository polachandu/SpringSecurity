 - Security is for protecting your data and business logic inside the web applications. Most people think security is all about data, but what if someone knows the business logic to generate the OTPs, so security also applies for business logic.
 - Security handled at different layers:
   - At Cloud Environment Level - Handle DDoS Attacks, Firewalls
   - At Virtual Machines Level - Secure Access, Network Security
   - At OS Level - Regular updates and Patching, Antivirus & Malware protection
   - If the application is deployed on 
     - Containers Level - Trusted Images, Minimise Attack Surface, Implement Container Isolation
     - Servers Level - Access Control, Web Application Firewall, Regular Security Updates
   - At Application/Service Level - Authentication, Authorisation, Protection from CORS, CSRF. This is the place where Spring Security comes into the picture.
 * Servlet Containers : In Java Web Apps, Servlet Container(Web Server) takes care of translating the HTTP messages for Java code to understand. Most popular is Tomcat. Servlet container converts the HTTP messages into ServletRequest and hand over to Servlet method as a parameter. Similarly, for ServletResponse.
 * Role of Filters: Filters inside the Java Web Application can be used to intercept each req/resp and do some pre-work before our business logic.
- Whenever an HTTP requests hit the Application Server, first it should hit the Filters then the Servlets or Application Business logic.
* 