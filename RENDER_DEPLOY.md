# 🚀 Déploiement NCC Backend sur Render

## 📋 Étapes de déploiement

### 1. Sur Render.com
1. **Dashboard** → **New** → **Web Service**
2. **Connect GitHub** → Sélectionnez `ncc-backend`
3. **Configuration** :
   - **Name** : `ncc-backend`
   - **Environment** : `Java`
   - **Build Command** : `./mvnw clean package -DskipTests`
   - **Start Command** : `java -jar target/ncc-backend-1.0.0.jar`

### 2. Variables d'environnement
Ajoutez ces variables dans Render :

```
DATABASE_URL=postgresql://votre-bd-url-existante
DB_DRIVER=org.postgresql.Driver
DB_USERNAME=votre-username-bd
DB_PASSWORD=votre-password-bd
DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect
DDL_AUTO=update
SHOW_SQL=false
H2_CONSOLE_ENABLED=false
JWT_SECRET=ncc-formation-secret-key-2024-render-production
```

### 3. CORS Configuration
L'URL de votre backend sera : `https://ncc-backend-xxxx.onrender.com`

### 4. Mise à jour Frontend
Après déploiement, mettez à jour `api.service.ts` :
```typescript
private baseUrl = 'https://ncc-backend-xxxx.onrender.com/api';
```

## 🔗 URLs finales
- **Frontend** : https://nccformation.netlify.app
- **Backend** : https://ncc-backend-xxxx.onrender.com/api
- **Swagger** : https://ncc-backend-xxxx.onrender.com/api/swagger-ui.html

## 🗄️ Base de données
Utilise votre PostgreSQL existant sur Render (gratuit).