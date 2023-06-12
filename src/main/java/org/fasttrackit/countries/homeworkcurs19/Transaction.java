package org.fasttrackit.countries.homeworkcurs19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum TransactionType {
    SELL,
    BUY
}

class Transaction {
    private String id;
    private String product;
    private TransactionType type;
    private double amount;

    public Object getId() {
        return null;
    }

    public void setProduct(String product) {
    }

    public void setAmount(double amount) {
    }

    public TransactionType getType() {
        return null;
    }

    public double getAmount() {
        return 0;
    }

    public String getProduct() {
        return null;
    }

    // Constructor, getters, and setters

    // ...
}

class TransactionRepository {
    private List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction getTransactionById(String id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void replaceTransaction(String id, Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getId().equals(id)) {
                transactions.set(i, updatedTransaction);
                break;
            }
        }
    }

    public void updateTransaction(String id, String product, double amount) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                transaction.setProduct(product);
                transaction.setAmount(amount);
                break;
            }
        }
    }

    public void deleteTransaction(String id) {
        transactions.removeIf(transaction -> transaction.getId().equals(id));
    }

    public Map<TransactionType, Double> getTypeReports() {
        Map<TransactionType, Double> typeReports = new HashMap<>();
        for (Transaction transaction : transactions) {
            double totalAmount = typeReports.getOrDefault(transaction.getType(), 0.0);
            totalAmount += transaction.getAmount();
            typeReports.put(transaction.getType(), totalAmount);
        }
        return typeReports;
    }

    public Map<String, Double> getProductReports() {
        Map<String, Double> productReports = new HashMap<>();
        for (Transaction transaction : transactions) {
            double totalAmount = productReports.getOrDefault(transaction.getProduct(), 0.0);
            totalAmount += transaction.getAmount();
            productReports.put(transaction.getProduct(), totalAmount);
        }
        return productReports;
    }

    // Filtering methods

    public List<Transaction> getByType(TransactionType type) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == type) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public List<Transaction> getByMinAmount(double minAmount) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() >= minAmount) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public List<Transaction> getByMaxAmount(double maxAmount) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() <= maxAmount) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public List<Transaction> getByTypeAndMin(TransactionType type, double minAmount) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == type && transaction.getAmount() >= minAmount) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public List<Transaction> getByTypeAndMax(TransactionType type, double maxAmount) {
        List<Transaction> filteredTransactions;
        filteredTransactions = new;
    }
}

