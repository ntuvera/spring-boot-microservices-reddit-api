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
                sh 'echo "inside Build worked"'
            }
        }
        stage('Confirmation') {
          steps {
                sh 'echo "Outside Build worked"'
          }
        }
    }
