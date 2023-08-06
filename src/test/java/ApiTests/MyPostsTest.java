package ApiTests;

import ApiTests.service.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MyPostsTest extends AbstractTest {

    @Test
    @Tag("Positive")
    void getMyPostsAsc() {
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(order, ASC)
                .queryParam(page, 0)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    void getMyPostsDesc() {
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(order, DESC)
                .queryParam(page, 1)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Negative")
    void getMyPostsPageIsNegative() {
        JsonPath response = given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(order, ASC)
                .queryParam(page, -2)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad request"));
    }

    @Test
    @Tag("Negative")
    void getMyPostsInvalidQuery() {
        JsonPath response = given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(order, order)
                .queryParam(page, 2)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat(response.getString("message"), equalTo("Bad request"));
    }

    @Test
    @Tag("Negative")
    void getMyPostsInvalidToken() {
        JsonPath response = given()
                .header("X-Auth-Token", 123)
                .queryParam(sort, createdAt)
                .queryParam(order, DESC)
                .queryParam(page, 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("No API token provided or is not valid"));
    }

    @Test
    @Tag("Negative")
    void getMyPostsNotToken() {
        JsonPath response = given()
                .queryParam(sort, createdAt)
                .queryParam(order, DESC)
                .queryParam(page, 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("Auth header required X-Auth-Token"));
    }
}


