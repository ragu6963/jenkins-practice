pipeline {
    agent any

    stages {
        stage('Load Environment Variables') {
            steps {
                script {
                    // Jenkins Credentials에서 Secret File 가져오기
                    // credentialsId : credentials 생성 당시 작성한 ID
                    // variable : 스크립트 내부에서 사용할 변수 이름
                    withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
                        // Secret File을 작업 디렉토리에 복사
                        sh 'cp $env_file .env'

                        // docker-compose가 .env 파일을 읽을 수 있게 권한 추가
                        sh 'chmod 644 .env'

                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    sh 'DOCKER_BUILDKIT=1 COMPOSE_DOCKER_CLI_BUILD=1 docker-compose up -d --build'
                }
                
            }

            post {
              withCredentials([string(credentialsId: 'discord-webhook', variable: 'Discord')]) {
                webhookURL: "$Discord"
                title: "Jenkins Build Result", 
                discordSend description: "젠킨스 빌드 결과", 
                link: env.BUILD_URL, result: currentBuild.currentResult,  // 빌드 URL 과 빌드 결과
            }
            }
        }
    }
}