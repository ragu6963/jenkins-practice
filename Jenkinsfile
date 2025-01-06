pipeline {
    agent any

    environment {
        // Jenkins Credentials에 저장된 환경 변수 불러오기
        DB_USER = credentials('DB_USER')  // 예: DB_USER는 Jenkins에 저장된 credentials ID
        DB_PASSWORD = credentials('DB_PASSWORD')  // 예: DB_PASSWORD는 credentials ID
        API_KEY = credentials('API_KEY')  // 예: API_KEY는 credentials ID
    }

    stages {
        stage('Generate .env File') {
            steps {
                script {
                    // .env 파일 생성
                    writeFile file: '.env', text: """
                    DB_USER=${env.DB_USER}
                    DB_PASSWORD=${env.DB_PASSWORD}
                    API_KEY=${env.API_KEY}
                    """
                }
                echo '.env file has been generated.'
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    // docker-compose up -d 실행
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
        cleanup {
            // docker-compose down을 통해 리소스 정리
            script {
                sh 'docker-compose down'
            }
        }
    }
}