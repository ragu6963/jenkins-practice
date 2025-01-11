pipeline {
  agent any

  stages { 
    stage('Build Start') {
      steps {
        script { 
          // 디스코드 알림 메세지 작성
          withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
            // 디스코드 알림 메세지 작성
            // description : 메세지 설명문
            // link : Jenkins BUILD 주소
            // title : 메세지 제목
            // webhookURL : 메세지 전송 URL
            discordSend description: """
            Jenkins Build Start
            """,
            link: env.BUILD_URL, 
            title: "${env.JOB_NAME} : ${currentBuild.displayName} 시작", 
            webhookURL: "$discord_webhook"
            }
        }
      }
    }
    stage('Load Environment Variables') {
      steps {
        script {
          // Jenkins Credentials에서 Secret File 가져오기
          // credentialsId : credentials 생성 당시 작성한 ID
          // variable : 스크립트 내부에서 사용할 변수 이름
          // 환경 변수 파일 로드
          withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
            // 환경 변수 파일을 작업 디렉토리로 복사
            sh 'cp $env_file .env'

            // 환경 변수 파일 권한 설정 (읽기/쓰기)
            sh 'chmod 644 .env'

          }
        }
      }
    }
    // 도커 컴포즈 실행
    stage('Run Docker Compose') {
      steps {
        script {
          // 기존 Docker 컨테이너 중지
          sh 'docker-compose down'
          
          // BuildKit을 사용하여 새로운 Docker 이미지 빌드 및 컨테이너 실행
          sh 'DOCKER_BUILDKIT=1 COMPOSE_DOCKER_CLI_BUILD=1 docker-compose up -d --build'
        }
      }
    }

    stage('Clean Docker Image') {
      steps {
        script {
          // 빌드 후 사용하지 않는 Docker 이미지 삭제
          sh 'docker image prune -f'
        }
      }
    }
  }
  // 빌드 결과에 따른 후처리 작업
  post {
        // 빌드 성공 시
        success {
            // 디스코드 알림 메세지 작성
            // description : 메세지 설명문
            // link : Jenkins BUILD 주소
            // result : 빌드 결과
            // duration : 빌드 실행 시간
            // title : 메세지 제목
            // webhookURL : 메세지 전송 URL
            withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                        discordSend description: """
                        제목 : ${currentBuild.displayName}
                        결과 : ${currentBuild.currentResult}
                        실행 시간 : ${currentBuild.duration / 1000}s
                        """,
                        link: env.BUILD_URL, result: currentBuild.currentResult, 
                        title: "${env.JOB_NAME} : ${currentBuild.displayName} 성공", 
                        webhookURL: "$discord_webhook"
            }
        }
        // 빌드 실패 시
        failure {
            withCredentials([string(credentialsId: 'discord-webhook', variable: 'discord_webhook')]) {
                        discordSend description: """
                        제목 : ${currentBuild.displayName}
                        결과 : ${currentBuild.currentResult}
                        실행 시간 : ${currentBuild.duration / 1000}s
                        """,
                        link: env.BUILD_URL, result: currentBuild.currentResult, 
                        title: "${env.JOB_NAME} : ${currentBuild.displayName} 실패", 
                        webhookURL: "$discord_webhook"
            }
        }
    }
}