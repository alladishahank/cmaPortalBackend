package com.accelq.csma_portal.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JoinColumn(name = "point_of_contact_email", nullable = false)
    @JsonProperty("point_of_contact_email")
    private String point_of_contact_email;

    private String tenant_id;
    private Date subscriptionStartDate;

    public Customer(Integer id, String name, String point_of_contact_email, String tenant_id, Date subscriptionStartDate) {
        this.id = id;
        this.name = name;
        this.point_of_contact_email = point_of_contact_email;
        this.tenant_id = tenant_id;
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Customer() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return point_of_contact_email;
    }

    public void setEmail(String email) {
        this.point_of_contact_email = email;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }
}
