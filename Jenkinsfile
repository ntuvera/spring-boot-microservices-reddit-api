  
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args ' -v /tmp/.m2:/tmp/.m2'
        }
    }
    stages {
        stage('Build Eureka Server') {
            steps {
                sh 'cd eureka-server && mvn -B -DskipTests clean package'
            }
        }
        stage('Build API Gateway') {
            steps {
                sh 'cd eureka-server && mvn -B -DskipTests clean package'
            }
        }
        stage('Test Eureka Server') {
            steps {
                sh 'cd eureka-server && mvn test'
            }
            post {
                always {
                    junit 'eureka-server/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Test API Gateway') {
            steps {
                sh 'cd api-gateway && mvn test'
            }
            post {
                always {
                    junit 'api-gateway/target/surefire-reports/*.xml'
                }
            }
        }
    }

}