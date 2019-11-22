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
                sh 'new changes'
                sh 'cd eureka-server && mvn -B -DskipTests clean package'
                sh 'pwd'
                sh 'cd ../api-gateway && mvn -B -DskipTests clean package'
            }
        }
        stage('Confirmation') {
          steps {
                sh 'echo "Outside Build worked"'
          }
        }
    }
}
