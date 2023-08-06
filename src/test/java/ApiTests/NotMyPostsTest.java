package ApiTests;

import ApiTests.service.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NotMyPostsTest extends AbstractTest {

    @Test
    @Tag("Positive")
    void getNotMyPostsAsc() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(order, ASC)
                .queryParam(page, 0)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    void getNotMyPostsDesc() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(order, DESC)
                .queryParam(page, 10)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    void getNotMyPostsALL() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(order, ALL)
                .queryParam(page, 20)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Negative")
    void getNotMyPostsPageIsNegative() {
        JsonPath response = given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(order, ASC)
                .queryParam(page, -20)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad request"));
    }

    @Test
    @Tag("Negative")
    void getNotMyPostsInvalidToken() {
        JsonPath response = given()
                .header("X-Auth-Token", 123)
                .queryParam(owner, notMe)
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
    void getNotMyPostsNotToken() {
        JsonPath response = given()
                .queryParam(owner, notMe)
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
