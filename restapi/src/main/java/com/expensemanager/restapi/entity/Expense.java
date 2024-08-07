package com.expensemanager.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="expense_name")
    @NotBlank(message="Expense Name must not be Empty")
    @Size(min=3, message="Expense Name must be atleast 3 characters")
    private String name;

    @NotBlank(message="Description cannot be empty")
    private String description;

    @Column(name="expense_amount")
    @NotNull(message="Amount cannot be Null")
    private BigDecimal amount;

    @NotNull(message="Category cannot be Null")
    private String category;

    @NotNull(message = "Date cannot be Blank")
    private Date date;

    @Column(name="Created_at", nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name="Updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name="user_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
