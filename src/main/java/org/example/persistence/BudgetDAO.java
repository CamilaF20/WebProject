package org.example.persistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.logic.Budget;

public class BudgetDAO {

    private final MongoCollection<Document> budgetCollection;

    public BudgetDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.budgetCollection = database.getCollection("budget");
    }

    public ObjectId addBudget(Budget budget) {
        // Convertir el objeto Budget a un documento BSON
        Document document = new Document()
                .append("estimateAmount", budget.getEstimateAmount())
                .append("rangeTime", budget.getRangeTime().toString());

        // Insertar el documento en la colección y retornar el ID generado
        budgetCollection.insertOne(document);
        return document.getObjectId("_id");
    }
}

