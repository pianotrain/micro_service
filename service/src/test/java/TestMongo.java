import com.rensm.audit.service.Application;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

/**
 * Created by DELL on 2018/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestMongo {

    @Autowired
    private MongoClient mongo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        MongoDatabase test =
                mongo.getDatabase("test");
//        test.createCollection("users");users
        MongoCollection<Document> users = test.getCollection("users");
        long count = users.count();
        System.out.println(count);
    }

    @Test
    public void testTemplate(){
        User user = new User();
        user.setId(21L);
        user.setUsername("user");
        user.setPassword("admin");
        mongoTemplate.save(user);
    }

    @Test
    public void testFind(){
        Query query = new Query(Criteria.where("username").is("user"));
        User one = mongoTemplate.findOne(query, User.class);
        String password = one.getPassword();
        System.out.println(password);
    }

    class User implements Serializable {
        private static final long serialVersionUID = -7711735494827731641L;
        private Long id;
        private String username;
        private String password;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
