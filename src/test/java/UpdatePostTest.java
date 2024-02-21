import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdatePostTest {


    @Test
    public void replacePostMap() {

        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "Updated post");
        newPost.put("author", "Jane");

                given()
                    .log().all().body(newPost)
                .when()
                    .put("http://localhost:3000/posts/3")
                .then()
                    .log().all();
    }

    @Test
    public void replacePostObject() {

        Post newPost = new Post();
        newPost.setTitle("post patched again");
        newPost.setAuthor("Mario");

                given()
                    .log().all().body(newPost)
                .when()
                        .put("http://localhost:3000/posts/2")
                        .then()
                        .log().all();
    }

    @Test
    public void updatePostObject() {

        Post newPost = new Post();
        newPost.setTitle("post patched again");

                given()
                        .log().all().body(newPost)
                .when()
                        .patch("http://localhost:3000/posts/2")
                .then()
                        .log().all();
    }
}
