#!/bin/bash

echo "🚀 Démarrage du backend NCC Formation..."

# Vérifier Java
if ! command -v java &> /dev/null; then
    echo "❌ Java n'est pas installé. Installez avec:"
    echo "sudo apt install openjdk-17-jdk"
    exit 1
fi

# Vérifier Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven n'est pas installé. Installez avec:"
    echo "sudo apt install maven"
    exit 1
fi

echo "✅ Java version:"
java -version

echo "✅ Maven version:"
mvn -version

echo "🔧 Compilation et démarrage..."
mvn clean spring-boot:run

echo "🌐 API disponible sur: http://localhost:8080/api"
echo "📚 Documentation Swagger: http://localhost:8080/api/swagger-ui.html"
echo "🗄️ Console H2: http://localhost:8080/api/h2-console"