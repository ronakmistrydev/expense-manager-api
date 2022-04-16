package com.main.expensemanagerapi.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@ChangeUnit(id = "CategoryMigration", order = "1")
public class CategoryMigration {
    class SubCategory {
        @Id
        private final String id;
        private final String parentId;
        private final String createdBy;
        private String name;

        public SubCategory(String id, String parentId, String createdBy, String name) {
            this.id = id;
            this.parentId = parentId;
            this.createdBy = createdBy;
            this.name = name;
        }
    }

    class Category {
        private final String id;
        private final String name;
        private final String createdBy;

        public Category(String id, String name, String createdBy) {
            this.id = id;
            this.name = name;
            this.createdBy = createdBy;
        }
    }

    @Execution
    public void seedCategories(MongoTemplate mongoTemplate) {
        HashMap<String, String[]> categories = this.generateCategories();
        categories.keySet().forEach((parentCategoryName) -> {
            String categoryId = UUID.randomUUID().toString();
            Category category = new Category(UUID.randomUUID().toString(), parentCategoryName, "SYSTEM");
            mongoTemplate.save(category, "category");
            Arrays.stream(categories.get(parentCategoryName)).forEach((subCategoryName) -> {
                String subCategoryId = UUID.randomUUID().toString();
                SubCategory subCategory = new SubCategory(subCategoryId, categoryId, "SYSTEM", subCategoryName);
                mongoTemplate.save(subCategory, "subCategory");
            });
        });
    }

    @RollbackExecution
    public void rollback() {
    }

    private HashMap<String, String[]> generateCategories() {
        HashMap<String, String[]> categories = new HashMap<>();
        categories.put("Food & Drinks", new String[]{
            "Bar, Cafe",
            "Groceries",
            "Restaurant, Fast-food"
        });
        categories.put("Shopping", new String[]{
            "Clothes & Shoes",
            "Drug-store, Chemist",
            "Electronics, Accessories",
            "Free Time",
            "Gift, Joy",
            "Health & Beauty",
            "Home, Garden",
            "Jewels, Accessories",
            "Kids",
            "Pet, Animals",
            "Stationery, Tools"
        });
        categories.put("Housing", new String[]{
            "Energy, Utilities",
            "Maintenance, Repairs",
            "Mortgage",
            "Property Insurance",
            "Rent",
            "Service"
        });
        categories.put("Transportation", new String[]{
            "Business Trips",
            "Long Distance",
            "Public Transport",
            "Taxi"
        });
        categories.put("Vehicle", new String[]{
            "Fuel",
            "Leasing",
            "Parking",
            "Rentals",
            "Vehicle Insurance",
            "Vehicle Maintenance"
        });
        categories.put("Life & Entertainment", new String[]{
            "Active Sport, Fitness",
            "Alcohol, Tobacco",
            "Books, Audio, Subscriptions",
            "Charity, Gifts",
            "Culture, Sport events",
            "Education, Development",
            "Health Care, Doctor",
            "Hobbies",
            "Holiday, Trips, Hotels",
            "Life Events",
            "Lottery, Gambling",
            "TV, Streaming",
            "Wellness, Beauty"
        });
        categories.put("Communication, PC", new String[]{
            "Internet",
            "Phone, Mobile Phone",
            "Postal Service",
            "Software, Apps, Games"
        });
        categories.put("Financial expenses", new String[]{
            "Advisory",
            "Charges",
            "Child Support",
            "Fines",
            "Insurance",
            "Loan, Interests",
            "Taxes"
        });
        categories.put("Investments", new String[]{
            "Collections",
            "Financial Investments",
            "Realty",
            "Savings",
            "Vehicles, Chattels"
        });
        categories.put("Income", new String[]{
            "Checks, Coupons",
            "Child Support",
            "Dues & Grants",
            "Gifts",
            "Interest, Dividends",
            "Lending, Renting",
            "Lottery, Gambling",
            "Refund, (Tax, Purchase)",
            "Rental Income",
            "Sale",
            "Wage, Invoices"
        });
        return categories;
    }
}
