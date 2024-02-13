import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class VerifyResponse {


    @Test
    public void getPostCheckBody() {
        String expected = "{\n" +
                "  \"author\": \"Jane\",\n" +
                "  \"title\": \"Updated post\",\n" +
                "  \"id\": \"1\"\n" +
                "}";


                when()
                    .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                    .log().all().body(Matchers.equalTo(expected));
    }

    @Test
    public void getPostBodyContains() {



                when()
                        .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                        .log().all().body(Matchers.containsString("Jane"));
    }

    @Test
    public void checkSpecificField() {


                when()
                        .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                        .log().all().body("author", Matchers.equalTo("Jane"))
                .and()
                        .body("title", Matchers.equalToIgnoringCase("Updated Post"));
    }

    @Test
    public void getPostCheckByObject() {
        Integer id = 1;

        Post post = given()
                    .log().all()
                .when()
                    .get("http://localhost:3000/posts/{postId}", id)
                .then()
                    .log().all().body("author", Matchers.equalTo("Jane"))
                .and()
                    .body("title", Matchers.equalToIgnoringCase("Updated Post")).extract().as(Post.class);

        Assert.assertEquals(post.getAuthor(), "Jane");
        Assert.assertEquals(post.getTitle(), "Updated post");
        Assert.assertEquals(post.getId(), id);
    }

    @Test
    public void addPostByObject() {
        Post newPost = new Post();
        newPost.setTitle("verification post again");
        newPost.setAuthor("John");
        newPost.setId(9);


        Post createdPost =
                given().log().all().body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all().extract().as(Post.class);

        Assert.assertEquals(newPost, createdPost);

    }
}
