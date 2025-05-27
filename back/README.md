# MDD API - Backend Spring Boot

## 🚀 Technologies utilisées

- **Spring Boot** 3.4.1 - Framework Java
- **Spring Security** - Authentification et autorisation
- **Spring Data JPA** - Couche d'accès aux données
- **MySQL** - Base de données
- **JWT** (JSON Web Tokens) - Authentification stateless
- **Swagger/OpenAPI** 3 - Documentation API
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestionnaire de dépendances

## 📋 Prérequis

- **Java 21** ou supérieur
- **Maven** 3.6 ou supérieur
- **MySQL** 8.0 ou supérieur
- Variable d'environnement configurées (voir section Configuration)

## 🛠️ Installation

1. Cloner le projet :
```bash
git clone https://github.com/Olelouer/mdd.git
cd back
```

2. Installer les dépendances :
```bash
mvn clean install
```

3. Configurer les variables d'environnement (voir section Configuration)

4. Démarrer l'application :
```bash
mvn spring-boot:run
```

L'API sera accessible à l'adresse `http://localhost:8080`

## ⚙️ Configuration

### Variables d'environnement requises

Créez un fichier `.env` ou configurez les variables suivantes dans votre système :

```bash
# Base de données MySQL
DB_URL=jdbc:mysql://localhost:3306/mdd_db
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password

# JWT Secret (doit être une chaîne sécurisée)
JWT_SECRET=your_super_secret_jwt_key_here
```

### Configuration de la base de données

1. Créer la base de données MySQL :
```sql
CREATE DATABASE mdd_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. L'application créera automatiquement les tables au démarrage grâce à `spring.jpa.hibernate.ddl-auto=update`

## 📮 Collection Postman

Une collection Postman est disponible dans le dossier `/postman` pour tester l'API :

Importer `postman/mdd.postman_collection.json` dans Postman

## 🧪 Tests

Lancer les tests unitaires :
```bash
mvn test
```

Lancer les tests avec couverture :
```bash
mvn clean test jacoco:report
```

## 🚀 Déploiement

### Build de production

```bash
mvn clean package -DskipTests
```