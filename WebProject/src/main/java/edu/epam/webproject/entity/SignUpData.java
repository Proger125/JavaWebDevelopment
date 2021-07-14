package edu.epam.webproject.entity;


public class SignUpData extends SignInData{
    private String login;

    public SignUpData(String email, String password, String login) {
        super(email, password);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SignUpData that = (SignUpData) o;
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + login.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SignUpData{ ");
        builder.append("Login = ").append(login).append(", ");
        builder.append("Email = ").append(getEmail()).append(", ");
        builder.append("Password = ").append(getPassword()).append(" }");
        return builder.toString();
    }
}
