import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPostsTest {


    @Test
    public void filterPostsById() {

        given().log().all().queryParam("id", "1")
                .when().get("http://localhost:3000/posts")
                .then().log().all()
                .statusCode(Matchers.lessThanOrEqualTo(200))
                .statusLine(Matchers.containsString("HTTP/1.1 200 OK"));
    }

    @Test
    public void filterPosts() {

        given().queryParam("author", "Mark")
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void filterPostsByMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("author", "Mark");


        given().queryParams(params)
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }
}
