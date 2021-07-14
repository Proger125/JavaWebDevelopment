package edu.epam.webproject.entity;

public class SignInData extends Entity{
    private String email;
    private String password;

    public SignInData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SignInData that = (SignInData) o;
        return email.equals(that.email) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return email.hashCode() + password.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SignInData{ ");
        builder.append("Email = ").append(email).append(", ");
        builder.append("Password = ").append(password).append(" }");
        return builder.toString();
    }
}
