package org.fasttrackit.countries.homeworkcurs18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/transactions")
public class BudgetManagerApplication {

    private final Map<Long, Transaction> transactions = new HashMap<>();
    private long transactionIdCounter = 0;

    public static void main(String[] args) {
        SpringApplication.run(BudgetManagerApplication.class, args);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(@RequestParam(required = false) String product,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) Double minAmount,
                                                @RequestParam(required = false) Double maxAmount) {
        return transactions.values().stream()
                .filter(transaction -> product == null || transaction.getProduct().equalsIgnoreCase(product))
                .filter(transaction -> type == null || transaction.getType().equalsIgnoreCase(type))
                .filter(transaction -> minAmount == null || transaction.getAmount() >= minAmount)
                .filter(transaction -> maxAmount == null || transaction.getAmount() <= maxAmount)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactions.get(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        transaction.setId(++transactionIdCounter);
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    @PutMapping("/{id}")
    public Transaction replaceTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        transactions.put(id, transaction);
        return transaction;
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactions.remove(id);
    }

    @GetMapping("/reports/type")
    public Map<String, List<Transaction>> getTypeReports() {
        return transactions.values().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    @GetMapping("/reports/product")
    public Map<String, List<Transaction>> getProductReports() {
        return transactions.values().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}

