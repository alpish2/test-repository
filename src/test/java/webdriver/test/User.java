package webdriver.test;


public class User {


    User(String firstname, String lastname, String email, String password, String confirmed_password) {
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        this.confirmed_password=confirmed_password;
    }
    User(String email, String password){
        this.email=email;
        this.password=password;
    }

    String firstname;
    String lastname;
    String email;
    String password;
    String confirmed_password;


    }
