package com.qa.testdata;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.qa.models.Book;
import static com.qa.config.TestConfig.*;
import java.util.ArrayList;
import java.util.List;

public class TestDataSetup {
    private static List<Integer> createdBookIds = new ArrayList<>();

    public static void setupTestData() {
        Response getResponse = given()
                .auth()
                .basic(ADMIN_USERNAME, PASSWORD)
                .when()
                .get(BOOKS_ENDPOINT);

        if (getResponse.getStatusCode() == 200) {
            List<Integer> ids = getResponse.jsonPath().getList("id");
            if (ids != null && !ids.isEmpty()) {
                createdBookIds.addAll(ids);
                return;
            }
        }

        createTestBooks();
    }

    private static void createTestBooks() {
        Book book1 = new Book();
        book1.setTitle("Test Book 1");
        book1.setAuthor("Test Author 1");

        Book book2 = new Book();
        book2.setTitle("Test Book 2");
        book2.setAuthor("Test Author 2");

        Integer id1 = postBook(book1);
        if (id1 != null) createdBookIds.add(id1);

        Integer id2 = postBook(book2);
        if (id2 != null) createdBookIds.add(id2);
    }

    private static Integer postBook(Book book) {
        Response response = given()
                .auth()
                .basic(ADMIN_USERNAME, PASSWORD)
                .contentType("application/json")
                .body(book)
                .when()
                .post(BOOKS_ENDPOINT);

        if (response.getStatusCode() == 201 || response.getStatusCode() == 208) {
            Response getResponse = given()
                    .auth()
                    .basic(ADMIN_USERNAME, PASSWORD)
                    .when()
                    .get(BOOKS_ENDPOINT);

            if (getResponse.getStatusCode() == 200) {
                List<Integer> ids = getResponse.jsonPath().getList("id");
                if (!ids.isEmpty()) {
                    return ids.get(0);
                }
            }
        }
        return null;
    }

    public static Integer getFirstBookId() {
        return createdBookIds.isEmpty() ? null : createdBookIds.get(0);
    }
}