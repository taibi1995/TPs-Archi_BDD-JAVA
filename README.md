# 5️⃣ PROJET: TPs-Archi_BDD-JAVA

## 📋 Informations Actuelles
- **Lien**: https://github.com/taibi1995/TPs-Archi_BDD-JAVA
- **Type**: Travaux Pratiques / Education
- **Langage**: Java (100%)
- **Fichiers**: 10 fichiers (classes Java et module-info)

## ⚠️ Problèmes Identifiés
1. **README vide ou minimal**
2. **Pas de .gitignore**
3. **Pas de guide de compilation**
4. **Pas de description**
5. **Pas de topics**
6. **Pas de documentation sur les TPs**

---

## 📝 Fichiers à Créer/Modifier

### 1️⃣ README.md (À créer/remplacer)

```markdown
# Architecture des Bases de Données - Java 🗄️

## 📚 Description
Travaux pratiques sur l'architecture des bases de données implémentés en Java, couvrant les concepts fondamentaux de gestion et optimisation des données au niveau système.

## 🎯 Objectifs
- Comprendre l'architecture interne des bases de données
- Implémenter des structures de données efficaces
- Maîtriser les algorithmes de jointure
- Optimiser la gestion de la mémoire
- Implémenter des systèmes de cache et de gestion des blocs

## 📋 Liste des Travaux Pratiques

| TP | Sujet | Description | Fichiers |
|----|-------|-------------|----------|
| **TP1** | Gestion des Blocs | Dump et gestion des blocs disque | `DiskBlocksDump.java`, `TaibiYounesDiskBlocksDump.java` |
| **TP2** | Free List | Structure de liste libre pour blocs libres | `FreeList.java`, `FreeListBM.java` |
| **TP3** | Jointures | Algorithmes de jointure (Nested Loop, Merge) | `Jointure.java`, `TaibiYounesTP1Jointure.java` |
| **TP4** | Nested Loop Join | Implémentation détaillée du Nested Loop Join | `TaibiYounesTP1NestedLoop.java` |
| **TP5** | Tests et Validation | Suite de tests | `TaibiYounesDumpTests.java`, `TaibiYounesTP1NestedLoopTest.java` |

## 🛠️ Technologies

- **Java 11+**
- **JUnit 5** - Framework de test
- **Maven** (optionnel) - Build automation
- **Gradle** (optionnel) - Alternatif à Maven

## 📥 Installation

### Prérequis
```bash
# Vérifier que Java est installé
java -version
# Résultat attendu: Java version 11 ou supérieur

# Vérifier javac
javac -version
```

### Cloner le projet
```bash
git clone https://github.com/taibi1995/TPs-Archi_BDD-JAVA.git
cd TPs-Archi_BDD-JAVA
```

## 🔨 Compilation

### Compilation Simple
```bash
# Compiler tous les fichiers Java
javac *.java

# Compiler un fichier spécifique
javac Main.java

# Compiler avec les tests
javac -cp junit-4.13.2.jar:hamcrest-core-1.3.jar *.java
```

### Avec Maven (optionnel)
```bash
# Compiler
mvn clean compile

# Tester
mvn test

# Construire le JAR
mvn package
```

### Avec Gradle (optionnel)
```bash
# Compiler
gradle build

# Tester
gradle test
```

## ▶️ Exécution

### Exécution Simple
```bash
# Exécuter le programme principal
java Main

# Exécuter une classe spécifique
java TaibiYounesDiskBlocksDump

# Avec arguments
java Main arg1 arg2
```

### Exécution des Tests
```bash
# Avec JUnit 4
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore TaibiYounesDumpTests

# Avec Maven
mvn test

# Avec Gradle
gradle test
```

## 📂 Structure du Projet

```
.
├── Main.java                                    # Point d'entrée
├── TaibiYounesMain.java                        # Implémentation principale
├── DiskBlocksDump.java                         # Gestion des blocs disque
├── TaibiYounesDiskBlocksDump.java             # Implémentation personnalisée
├── TaibiYounesDumpTests.java                   # Tests pour DiskBlocksDump
├── FreeList.java                               # Structure Free List
├── FreeListBM.java                             # Free List Bitmap
├── Jointure.java                               # Algorithms de jointure
├── TaibiYounesTP1Jointure.java                # Implémentation jointure
├── TaibiYounesTP1NestedLoop.java              # Nested Loop Join
├── TaibiYounesTP1NestedLoopTest.java          # Tests Nested Loop
├── module-info.java                            # Module Java 9+
├── TaibiYounesDiskBlocksDump.java             # [À documenter]
├── .gitignore                                  # Fichiers à ignorer
├── pom.xml                                     # Configuration Maven (optionnel)
├── build.gradle                                # Configuration Gradle (optionnel)
└── README.md                                   # Ce fichier
```

## 📖 Concepts Importants

### 1️⃣ Gestion des Blocs Disque
```java
// Représentation d'un bloc disque
public class DiskBlock {
    private int blockId;
    private byte[] data;
    private int size;
    
    public void read() { /* ... */ }
    public void write() { /* ... */ }
    public void dump() { /* ... */ }
}
```

### 2️⃣ Free List et Gestion Mémoire
```java
// Tracker les blocs libres
public class FreeList {
    private List<Integer> freeBlocks;
    
    public int allocateBlock() { /* ... */ }
    public void freeBlock(int blockId) { /* ... */ }
}
```

### 3️⃣ Algorithmes de Jointure

#### Nested Loop Join
```java
// Jointure naïve: O(n*m)
for (Record r1 : table1) {
    for (Record r2 : table2) {
        if (r1.id == r2.id) {
            result.add(join(r1, r2));
        }
    }
}
```

#### Sort-Merge Join
```java
// Plus efficace: O(n log n + m log m + n + m)
sort(table1);
sort(table2);
// Parcours linéaire et fusion
```

### 4️⃣ Index et Optimisation
```java
// Utilisation d'index pour accélération
public class Index {
    private Map<Integer, List<Record>> index;
    
    public List<Record> lookup(int key) { 
        return index.get(key); 
    }
}
```

## 🚀 Exemple Complet

```java
public class Example {
    public static void main(String[] args) {
        // 1. Créer les tables
        Table table1 = new Table("Employees");
        Table table2 = new Table("Departments");
        
        // 2. Charger les données
        table1.loadData("employees.csv");
        table2.loadData("departments.csv");
        
        // 3. Effectuer la jointure
        NestedLoopJoin joiner = new NestedLoopJoin();
        Table result = joiner.join(table1, table2, "dept_id");
        
        // 4. Afficher les résultats
        result.display();
        
        // 5. Mesurer la performance
        System.out.println("Temps: " + joiner.getExecutionTime() + "ms");
    }
}
```

## 🧪 Tests

### Exécuter les Tests
```bash
# Tous les tests
./gradlew test

# Test spécifique
./gradlew test --tests TaibiYounesDumpTests

# Avec rapport
./gradlew test --info
```

### Exemple de Test
```java
@Test
public void testNestedLoopJoin() {
    Table t1 = createSampleTable1();
    Table t2 = createSampleTable2();
    
    NestedLoopJoin joiner = new NestedLoopJoin();
    Table result = joiner.join(t1, t2);
    
    assertEquals(expectedSize, result.size());
    assertTrue(result.isValid());
}
```

## 📊 Performance et Optimisation

### Mesurer la Performance
```java
long startTime = System.currentTimeMillis();
// Code à mesurer
long endTime = System.currentTimeMillis();
System.out.println("Temps: " + (endTime - startTime) + "ms");

// Avec System.nanoTime() pour plus de précision
long nanoStart = System.nanoTime();
// Code à mesurer
long nanoEnd = System.nanoTime();
long durationMs = (nanoEnd - nanoStart) / 1_000_000;
```

### Techniques d'Optimisation
- **Indexing**: Créer des index sur les colonnes de jointure
- **Caching**: Garder les données fréquemment accédées en cache
- **Buffering**: Utiliser des buffers pour réduire les I/O disque
- **Parallel Processing**: Utiliser des threads pour paralléliser

## 📚 Ressources

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Database Systems by Garcia-Molina](https://www.elsevier.com/books/database-systems/garcia-molina/978-0-13-840319-5)
- [SQL Performance Explained](https://sql-performance-explained.com/)
- [Algorithms for Relational Operations](https://www.postgresql.org/docs/current/indexes.html)

## ❓ FAQ

**Q: Comment compiler avec des dépendances externes?**
```bash
javac -cp lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar *.java
```

**Q: Comment créer un JAR exécutable?**
```bash
# Créer manifest
echo "Main-Class: Main" > manifest.txt

# Créer JAR
jar cfm app.jar manifest.txt *.class

# Exécuter
java -jar app.jar
```

**Q: Comment déboguer?**
```bash
# Compiler avec symboles de debug
javac -g *.java

# Utiliser jdb (Java Debugger)
jdb Main
```

## 🐛 Dépannage Courant

| Erreur | Solution |
|--------|----------|
| `javac: command not found` | Installer JDK ou ajouter à PATH |
| `ClassNotFoundException` | Vérifier le classpath (-cp) |
| `OutOfMemoryError` | Augmenter heap: `java -Xmx2g Main` |
| `NoSuchMethodError` | Vérifier la compatibilité Java/JAR |

## 📝 Licence

MIT License

## 👨‍💻 Auteur

**Younes Taibi**
- ID: [À remplir si applicable]
- GitHub: [@taibi1995](https://github.com/taibi1995)

---

**Dernière mise à jour**: Février 2026
```

### 2️⃣ .gitignore (À créer)

```
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files
*.jar
*.war
*.ear
*.zip
*.tar.gz
*.rar

# IDEs
.idea/
*.iml
*.iws
*.ipr
*.swp
*.swo
*~

# Eclipse
.classpath
.project
.settings/
bin/

# VS Code
.vscode/

# NetBeans
nbproject/
build/
nbbuild/
dist/
nbdist/
.nb-gradle/

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
.flattened-pom.xml

# Gradle
.gradle/
build/
gradle/
gradlew
gradlew.bat

# JetBrains
.idea/
*.iml

# OS
.DS_Store
Thumbs.db

# Test reports
test-results/
```

### 3️⃣ pom.xml (Optionnel - pour Maven)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>fr.taibi</groupId>
    <artifactId>database-architecture</artifactId>
    <version>1.0.0</version>

    <name>Database Architecture - Java</name>
    <description>Educational project on database architecture concepts</description>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.10.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## ✅ Actions à Effectuer sur GitHub

### 1. Ajouter une description
- Description: "Java implementation of database architecture concepts including disk block management, free lists, and join algorithms optimization"

### 2. Ajouter les topics
- `java`
- `database`
- `education`
- `database-architecture`
- `join-algorithms`
- `optimization`
- `data-structures`

### 3. Pousser les fichiers
```bash
git add README.md .gitignore pom.xml
git commit -m "docs: add comprehensive documentation and build configuration"
git push origin main
```

---

## 📌 Suggestions d'Améliorations
- Ajouter des datasets de test
- Créer des benchmarks comparatifs
- Documenter les résultats expérimentaux
- Ajouter des visualisations des performance
- Créer une GUI pour tester les jointures
