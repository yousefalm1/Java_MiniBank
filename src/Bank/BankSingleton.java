package Bank;

import java.util.ArrayList;
import java.util.List;

public class BankSingleton {

private static BankSingleton instance;

    private List<User> users;
    private List<Employee>  employees;
    private User currentUser;

    private BankSingleton() {
        this.users = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public static BankSingleton getInstance() {
        if (instance == null) {
            instance = new BankSingleton();
        }
        return instance;
    }


    public User createUser(String username, String password) {
        for(User user :users) {
            if(user.getUsername().equals(username)){
                throw new IllegalArgumentException();
            }
        }
        BankAccount newBankAccount = new BankAccount(username);
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("User is created");
        return newUser;
    }

    public Employee createEmployee(String username, String password, String name, String employeeId,
                                   double basicSalary, String position ){



        for (Employee employee : employees) {
            if(employee.getEmployeeId().equals(employeeId)) {
                throw new IllegalArgumentException();
            }
        }
        Employee newEmployee = new Employee(username, password, position, basicSalary);
        employees.add(newEmployee);
        System.out.println("Employee Created");
        return newEmployee;
    }




    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Success");
                currentUser = user;
                return user;
            }
        }
        return null;
    }


    public void displayAccountDetails() {
        if (currentUser != null) {
            System.out.println(currentUser.getUsername());
            currentUser.displayAccountDetails();
        }
    }



}
