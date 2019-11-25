  
pipeline {
    agent any
    stages {
        stage('Build Eureka Server') {
            steps {
                sh 'cd eureka-server && mvn -B -DskipTests clean package'
            }
        }
    }
}