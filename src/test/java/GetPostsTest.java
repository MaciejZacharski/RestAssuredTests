import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetPostsTest {


    @Test
    public void getPosts() {
        when().get("http://localhost:3000/posts")
                .then().log().body();
    }

    @Test
    public void getPost() {
        //   given().pathParam("postId", 1).when().get("http://localhost:3000/posts/{postId}").then().log().body();

        given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().body();
    }
}
