pipeline {
    agent any

    stages {
        stage('Load Environment Variables') {
            steps {
                script {
                    // Jenkins Credentials에서 Secret File 가져오기
                    // credentialsId : credentials 생성 당시 작성한 ID
                    // variable : 스크립트 내부에서 사용할 변수 이름
                    withCredentials([file(credentialsId: 'secret-file', variable: 'secret-file')]) {
                        // Secret File을 작업 디렉토리에 복사
                        sh 'cp $secret-file .env'

                        // docker-compose가 .env 파일을 읽을 수 있게 권한 추가
                        sh 'chmod 644 .env'
                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    sh 'docker-compose up -d --build'
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