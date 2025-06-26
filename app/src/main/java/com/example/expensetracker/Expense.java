package com.example.expensetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expense {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String category;
    public double amount;

    public Expense(String title, String category, double amount) {
        this.title = title;
        this.category = category;
        this.amount = amount;
    }
}
