package com.qa.config;

/**
 * This class contains configuration constants used throughout the test framework.
 */

public class TestConfig {
    // Base URL of the application
    public static final String BASE_URL = "http://localhost:7081";

    // Authentication credentials
    public static final String ADMIN_USERNAME = "admin";
    public static final String USER_USERNAME = "user";
    public static final String PASSWORD = "password";

    // API endpoints
    public static final String BOOKS_ENDPOINT = "/api/books";
}