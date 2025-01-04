pipeline {
    agent any
    environment {
        API_PORT = credentials('API_PORT') // Jenkins에 저장된 'api-key'를 참조
    }
    stages {
        stage('Print API Key') {
            steps {
                echo "The API key is ${API_PORT}" // 실제 값 출력 (테스트 환경에서만 사용)
            }
        }
    }
}
