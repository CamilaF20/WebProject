package org.example.persistence;

import org.example.logic.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UserDAO {
    private final MongoCollection<Document> usersCollection;

    public UserDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.usersCollection = database.getCollection("users");
    }

    public void registerUser(User user) {
        // Crear el documento con los atributos de User
        Document doc = new Document("name", user.getName())
                .append("email", user.getEmail())
                .append("password", user.getPassword())
                .append("estimateAmount", user.getEstimateAmount())
                .append("rangeTime", user.getRangeTime())
                .append("categories", user.getCategories());
        usersCollection.insertOne(doc);
    }

    public User findUserByEmail(String email) {
        Document query = new Document("email", email);
        Document result = usersCollection.find(query).first();
        if (result != null) {
            User user = new User(
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password")
            );
            user.setId(result.getObjectId("_id"));  // El _id se asigna automáticamente
            user.setEstimateAmount(result.getDouble("estimateAmount"));
            user.setRangeTime(result.get("rangeTime", RangeTime.class)); // Asegúrate que RangeTime esté bien definido
            user.setCategories(result.getList("categories", Category.class)); // Asegúrate que Category esté bien definido
            return user;
        }
        return null;
    }
}
