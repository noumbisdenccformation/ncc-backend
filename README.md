# NCC Formation Backend API

## 🚀 Démarrage rapide

### Prérequis
- Java 17+ ✅
- Maven 3.6+ (à installer)

### Installation Maven
```bash
sudo apt install maven
```

### Lancement
```bash
mvn spring-boot:run
```

## 📋 Endpoints disponibles

### 🔐 Authentification
- `POST /api/auth/login` - Connexion
- `POST /api/auth/register` - Inscription
- `GET /api/auth/me` - Profil utilisateur

### 📚 Formations
- `GET /api/formations` - Toutes les formations
- `GET /api/formations/active` - Formations actives
- `GET /api/formations/search?keyword=web` - Recherche
- `POST /api/formations` - Créer (admin/instructor)

### 👥 Utilisateurs (Admin)
- `GET /api/users` - Tous les utilisateurs
- `GET /api/users/stats` - Statistiques

## 🔑 Comptes de test

| Email | Mot de passe | Rôle |
|-------|-------------|------|
| admin@nccformation.com | password | ADMIN |
| jean@example.com | password | USER |
| marie@nccformation.com | password | COACH |

## 🌐 URLs importantes

- **API Base**: http://localhost:8080/api
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **H2 Console**: http://localhost:8080/api/h2-console
  - JDBC URL: `jdbc:h2:mem:nccdb`
  - Username: `sa`
  - Password: `password`

## 🧪 Test rapide

```bash
# 1. Connexion
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@nccformation.com","password":"password"}'

# 2. Formations actives
curl http://localhost:8080/api/formations/active
```