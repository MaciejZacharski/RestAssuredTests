import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AddPostTest {

    @Test
    public void addPost() {
        String newPost = "{\n" +
                "    \"title\": \"a title\",\n" +
                "    \"author\": \"Mark\"\n" +
                "}";


        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts").then().log().all();

    }

    @Test
    public void addPostFromFile() {
        File newPost = new File("src/main/resources/post.json");


        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts").then().log().all();

    }

    @Test
    public void addPostMap() {
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "Very nice post");
        newPost.put("author", "Marry");


        given().log().all().body(newPost)
                .when().post("http://localhost:3000/posts").then().log().all();

    }

    @Test
    public void addPostByObject() {
        Post newPost = new Post();
        newPost.setTitle("Object post");
        newPost.setAuthor("Mario");


        given().log().all().body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();

    }


}
