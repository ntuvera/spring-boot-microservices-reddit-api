pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args ' -v /tmp/.m2:/tmp/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'cd eureka-server && mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'cd eureka-server && mvn test'
            }
            post {
                always {
                    junit 'eureka-server/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') { 
            steps {
                sh 'pwd'
                sh 'eureka-server/jenkins/scripts/deliver.sh' 
            }
        }
    }
}
