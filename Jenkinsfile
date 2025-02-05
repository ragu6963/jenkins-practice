pipeline {
  agent any

  stages { 
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
          sh 'docker compose down'
          
          // BuildKit을 사용하여 새로운 Docker 이미지 빌드 및 컨테이너 실행
          sh 'DOCKER_BUILDKIT=1 COMPOSE_DOCKER_CLI_BUILD=1 docker compose up -d --build'
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
}