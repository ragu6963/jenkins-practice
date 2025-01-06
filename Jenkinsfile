pipeline {
    agent any

    stages {
        stage('Load Environment Variables') {
            steps {
                script {
                    // Jenkins Credentials에서 Secret File 가져오기
                    withCredentials([file(credentialsId: 'secret-file', variable: 'ENV_FILE')]) {
                        // Secret File을 작업 디렉토리에 복사
                        sh 'cp $ENV_FILE .env'
                    }
                    echo '.env file has been created from credentials.'
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    // docker-compose 실행
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}