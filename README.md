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
* Usage of some important Spring Security Filters while hitting an Authenticated API are:
  - AuthorizationFilter
  - DefaultLoginPageGeneratingFilter
  - UsernamePasswordAuthenticationFilter
- SecurityFilterChain Bean is where we configure the users to allow the secured APIs. In this bean, we are also segregate what are the secured APIs and public APIs by using requestMatchers and the authentication levels.
  - We can customise this bean to have form login(for UI) and basicHttp(if we just need it for backend communication like API)
- Usage of PasswordEncoder helps to not storing the plain-text password which is an industry standard. 
- To maintain industry standard, we always follows hashing technique. We cannot perform Encoding or Encryption on Passwords.
- Encoding can be decoded easily because it is reversible. Ex: Base 64, ASCII encoding. We should use Encoding when data is not in BINARY format.
- Encryption can also be decrypted using a key. We use encryption for when DATA AT REST or DATA IN TRANSIT like files stored in S3. Encryption has two types, Symmetric and Asymmetric. Ex: RSA
- We should use Hashing while storing passwords and it is irreversible. Hashing also has drawbacks 
  1. Every type same hashing value will be generated for the same string.
  2. Generating the hash value is faster. This will be an advantage for hackers to perform hash using multiple values with in small amount of time.
- Considering the above drawbacks, hackers can perform brute-force attacks and dictionary or rainbow attacks.
- To overcome these attacks, we should use salts during password hashing. The advantage of using salt here is it will generate a random value every time. So even for same passwords for different users will be having different passwords. In database, it will store it as hash(salt+password)
- formLogin will have default LoginAuthenticationEntryPoint by Spring Security. But for http Basic, we can create a new custom Authentication Entry Point to show how the response can be.
- We can use Global Exception handle by creating new Custom Access Denied Handler.
- 401 - Unauthorised
- 403 - ForBidden
- To redirect the page after session time out, we can configure that by using session Management in Config file and provide configuration as invalidSession and other configurations like maximum sessions for concurrent users.
- 


## Open Questions:
- Why did we choose UserDetailsService over UserDetailsManager?