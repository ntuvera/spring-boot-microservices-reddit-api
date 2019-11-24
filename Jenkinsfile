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
                /* sh 'cd ..' */
                /* sh 'cd api-gateway && mvn -B -DskipTests clean package' */
                /* sh 'cd ..' */
                /* sh 'cd users-api && mvn -B -DskipTests clean package' */
                /* sh 'cd ..' */
                /* sh 'cd posts-api && mvn -B -DskipTests clean package' */
                /* sh 'cd ..' */
                /* sh 'cd comments-api && mvn -B -DskipTests clean package' */
                /* sh 'cd ..' */
            }
        }
    }
}
