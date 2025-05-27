# MDD

## 🏗️ Architecture

Ce projet est composé de deux applications principales :

- **Frontend** : Application Angular 19 avec Angular Material
- **Backend** : API REST Spring Boot 3.4 avec authentification JWT
- **Base de données** : MySQL
- **Tests API** : Collection Postman

```
📁 Projet MDD
├── 🅰️ front/          # Application Angular (port 4200)
├── ☕ back/           # API Spring Boot (port 8080)  
└── 📮 postman/        # Collections Postman pour tester l'API
```

## 🚀 Démarrage rapide

### Prérequis
- **Node.js** 18+ et npm
- **Java** 21+
- **Maven** 3.6+
- **MySQL** 8.0+

### Installation complète

1. **Cloner le projet**
```bash
git clone https://github.com/Olelouer/mdd.git
cd mdd-project
```

2. **Configuration de la base de données**
```sql
CREATE DATABASE mdd_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. **Variables d'environnement** (créer un fichier `.env`)
```bash
DB_URL=jdbc:mysql://localhost:3306/mdd_db
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
JWT_SECRET=your_super_secret_jwt_key_here
```

4. **Démarrer le backend**
```bash
cd back
mvn clean install
mvn spring-boot:run
```
🌐 API disponible sur `http://localhost:8080`

5. **Démarrer le frontend**
```bash
cd front
npm install
npm start
```
🌐 Application disponible sur `http://localhost:4200`

## 📮 Tests API avec Postman

Une collection Postman complète est disponible dans le dossier `/postman` :

Importer `postman/mdd.postman_collection.json` dans Postman

## 🛠️ Technologies utilisées

### Frontend
- Angular 19.2
- Angular Material

### Backend
- Spring Boot 3.4.1
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Maven pour la gestion des dépendances

## 📱 Fonctionnalités principales

- 🔐 **Authentification** : Inscription/Connexion avec JWT
- 📝 **Articles** : Création, lecture, mise à jour d'articles
- 💬 **Commentaires** : Système de commentaires sur les articles
- 👤 **Profils** : Gestion des profils utilisateurs
- 🏷️ **Sujets** : Organisation par thématiques
- 📱 **Responsive** : Interface adaptée mobile/desktop

## 🚀 Déploiement

### Environnement de développement
- Frontend : `ng serve`
- Backend : `mvn spring-boot:run`

### Environnement de production
- Frontend : `ng build --prod`
- Backend : `mvn clean package` puis `java -jar target/mdd-api.jar`

## 📖 Documentation détaillée

- 📖 [Documentation Frontend](./front/README.md) - Angular, composants, services
- 📖 [Documentation Backend](./back/README.md) - API, sécurité, base de données