  
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
                sh 'cd api-gateway && mvn -B -DskipTests clean package'
            }
        }
        stage('Build Users API') {
            steps {
                sh 'cd users-api && mvn -B -DskipTests clean package'
            }
        }
        stage('Build Comments API') {
            steps {
                sh 'cd comments-api && mvn -B -DskipTests clean package'
            }
        }
        stage('Build Posts API') {
            steps {
                sh 'cd posts-api && mvn -B -DskipTests clean package'
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
        stage('Test Users API') {
            steps {
                sh 'cd users-api && mvn test'
            }
            post {
                always {
                    junit 'users-api/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Test Comments API') {
            steps {
                sh 'cd comments-api && mvn test'
            }
            post {
                always {
                    junit 'comments-api/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Test Posts API') {
            steps {
                sh 'cd posts-api && mvn test'
            }
            post {
                always {
                    junit 'posts-api/target/surefire-reports/*.xml'
                }
            }
        }
    }

}