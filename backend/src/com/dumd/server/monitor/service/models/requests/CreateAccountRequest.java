package com.dumd.server.monitor.service.models.requests;

import java.util.Objects;

public class CreateAccountRequest {
    private String customerName;
    private String email;
    private String password;

    public CreateAccountRequest(String customerName, String email, String password) {
        this.customerName = customerName;
        this.email = email;
        this.password = password;
    }

    public CreateAccountRequest(){}

    public CreateAccountRequest(Builder builder) {
        this.customerName = builder.name;
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountRequest that = (CreateAccountRequest) o;
        return customerName.equals(that.customerName) && email.equals(that.email) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, email, password);
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "name='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;
        private String email;
        private String password;

        private Builder() {}

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public CreateAccountRequest build() { return new CreateAccountRequest(this); }
    }
}
