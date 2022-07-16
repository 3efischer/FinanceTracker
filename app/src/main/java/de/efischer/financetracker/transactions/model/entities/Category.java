package de.efischer.financetracker.transactions.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<Category> subCategories;
    private boolean isPositive;

    public Category(String name, boolean isPositive) {
        this.name = name;
        this.isPositive = isPositive;
        this.subCategories = new ArrayList<>();
    }

    public void addSubCategory(Category subCategory) {
        this.subCategories.add(subCategory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }
}
