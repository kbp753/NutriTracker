package com.bhanu.nutritracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "log_entry") // Define the name of your table
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "nutrient_id", nullable = false)
    private Nutrient nutrient;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "percent_daily_value")
    private Double percentDailyValue;

    @JoinColumn(name = "intake_id")
    @ManyToOne
    private DailyIntake dailyIntake;

    @Column(name = "log_date")
    private LocalDate logDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
// Constructors, getters, and setters

    public LogEntry() {
        // Default constructor
    }

    // Getters and setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPercentDailyValue() {
        return percentDailyValue;
    }

    public void setPercentDailyValue(Double percentDailyValue) {
        this.percentDailyValue = percentDailyValue;
    }

    public DailyIntake getDailyIntake() {
        return dailyIntake;
    }

    public void setDailyIntake(DailyIntake dailyIntake) {
        this.dailyIntake = dailyIntake;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }
}
