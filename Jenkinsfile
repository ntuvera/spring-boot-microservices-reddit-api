  
pipeline {
    agent any
    stages {
        stage('Build Eureka Server') {
            steps {
                sh 'cd eureka-server && mvn clean package'
            }
        }
    }
}