@Id
private Long id;
private String username;
@Coloumn(unique=true)
private String email;
private String password;
private String role;
@OnetoOne
private employeeProfile emp;
