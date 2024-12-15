package org.example.servlets;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@WebServlet("/Budgets")
public class Budgets extends HttpServlet {

    // Configuración de MongoDB
    private static final String MONGO_URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "GestorPersonal";
    private static final String BUDGETS_COLLECTION = "budget";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        StringBuilder requestBody = new StringBuilder();
        String line;

        while ((line = request.getReader().readLine()) != null) {
            requestBody.append(line);
        }

        try {
            // Parsear la solicitud JSON
            JSONObject jsonRequest = new JSONObject(requestBody.toString());
            String timePeriod = jsonRequest.optString("timePeriod", null);
            double totalAmount = jsonRequest.optDouble("totalAmount", 0);

            if (timePeriod == null || totalAmount <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Faltan parámetros obligatorios o son inválidos.\"}");
                return;
            }

            // Conectar a MongoDB y guardar el presupuesto
            try (MongoClient mongoClient = MongoClients.create(new ConnectionString(MONGO_URI))) {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> budgetsCollection = database.getCollection(BUDGETS_COLLECTION);

                // Buscar si ya existe un presupuesto para ese periodo de tiempo
                Document existingBudget = budgetsCollection.find(new Document("timePeriod", timePeriod)).first();

                if (existingBudget != null) {
                    // Si existe, actualizar el monto total
                    double currentTotalAmount = existingBudget.getDouble("totalAmount");
                    double updatedTotalAmount = currentTotalAmount + totalAmount;

                    budgetsCollection.updateOne(
                            new Document("timePeriod", timePeriod),
                            new Document("$set", new Document("totalAmount", updatedTotalAmount))
                    );
                    response.getWriter().write("{\"message\": \"Presupuesto actualizado con éxito.\"}");
                } else {
                    // Si no existe, insertar un nuevo documento
                    Document newBudget = new Document()
                            .append("timePeriod", timePeriod)
                            .append("totalAmount", totalAmount);

                    budgetsCollection.insertOne(newBudget);
                    response.getWriter().write("{\"message\": \"Presupuesto agregado con éxito.\"}");
                }
            }
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Formato JSON inválido: " + e.getMessage() + "\"}");
        }
    }
}
