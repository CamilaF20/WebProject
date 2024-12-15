document.addEventListener("DOMContentLoaded", () => {
    const budgetForm = document.getElementById("budget-form");
    const categoryForm = document.getElementById("category-form");
    const transactionForm = document.getElementById("transaction-form");
    const generateSummaryBtn = document.getElementById("generate-summary");
    const categoryList = document.getElementById("category-list");
    const transactionCategory = document.getElementById("transactionCategory");
    const summaryOutput = document.getElementById("summary-output");
    const alertList = document.getElementById("alert-list");

    let categories = [];

    // Guardar presupuesto
    budgetForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const estimateAmount = document.getElementById("estimateAmount").value;
        const rangeTime = document.getElementById("rangeTime").value;

        console.log(`Monto Total: ${estimateAmount}, Rango de Tiempo: ${rangeTime}`); // Verificar los datos

        const budgetData = {
            estimateAmount: estimateAmount,
            rangeTime: rangeTime
        };

        fetch('http://localhost:8080/webProject_war_exploded/Budgets', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams(budgetData)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data); // Verifica la respuesta
                alert(`Presupuesto guardado: $${data.estimateAmount} para ${data.rangeTime}`);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Hubo un problema al guardar el presupuesto.');
            });
    });



    // Agregar rubro
    categoryForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const name = document.getElementById("categoryName").value;
        const value = document.getElementById("categoryValue").value;

        categories.push({ name, value, transactions: [] });

        // Actualizar lista de categorías
        const option = document.createElement("option");
        option.value = name;
        option.textContent = name;
        transactionCategory.appendChild(option);

        const div = document.createElement("div");
        div.textContent = `Rubro: ${name}, Monto: $${value}`;
        categoryList.appendChild(div);
    });

    // Registrar transacción
    transactionForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const categoryName = transactionCategory.value;
        const value = parseFloat(document.getElementById("transactionValue").value);
        const description = document.getElementById("transactionDescription").value;

        const category = categories.find((cat) => cat.name === categoryName);
        if (category) {
            category.transactions.push({ value, description });
            alert(`Transacción registrada: ${description} en ${categoryName} por $${value}`);

            // Verificar alertas
            const totalSpent = category.transactions.reduce((sum, t) => sum + t.value, 0);
            if (totalSpent >= category.value) {
                const alertDiv = document.createElement("div");
                alertDiv.textContent = `ALERTA: Rubro ${categoryName} agotado o excedido.`;
                alertList.appendChild(alertDiv);
            }
        }
    });

    // Generar resumen
    generateSummaryBtn.addEventListener("click", () => {
        summaryOutput.innerHTML = "";
        let totalSpent = 0;

        categories.forEach((category) => {
            const totalCategorySpent = category.transactions.reduce((sum, t) => sum + t.value, 0);
            totalSpent += totalCategorySpent;

            const summaryDiv = document.createElement("div");
            summaryDiv.textContent = `Rubro: ${category.name}, Monto asignado: $${category.value}, Gastado: $${totalCategorySpent}, Saldo disponible: $${category.value - totalCategorySpent}`;
            summaryOutput.appendChild(summaryDiv);
        });

        const totalDiv = document.createElement("div");
        totalDiv.textContent = `Total gastado: $${totalSpent}`;
        summaryOutput.appendChild(totalDiv);
    });
});
