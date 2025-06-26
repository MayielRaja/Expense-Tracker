package com.example.expensetracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpenseDatabase db;
    private List<Expense> expenseList;
    private ExpenseAdapter adapter;

    private RecyclerView recyclerView;
    private Button btnAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Room DB
        db = ExpenseDatabase.getInstance(this);

        // Get data
        expenseList = db.expenseDao().getAllExpenses();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Button click to show dialog
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddExpenseDialog();
            }
        });
    }

    private void showAddExpenseDialog() {
        // Inflate the custom dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_input, null);

        // Get references to EditText fields
        EditText inputTitle = dialogView.findViewById(R.id.inputTitle);
        EditText inputCategory = dialogView.findViewById(R.id.inputCategory);
        EditText inputAmount = dialogView.findViewById(R.id.inputAmount);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Expense");
        builder.setView(dialogView);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String title = inputTitle.getText().toString().trim();
            String category = inputCategory.getText().toString().trim();
            String amountStr = inputAmount.getText().toString().trim();

            if (!title.isEmpty() && !category.isEmpty() && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);

                Expense expense = new Expense(title, category, amount);
                db.expenseDao().insert(expense);

                // Refresh list
                expenseList.clear();
                expenseList.addAll(db.expenseDao().getAllExpenses());
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
