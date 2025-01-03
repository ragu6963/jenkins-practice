pipeline {
    agent any
    stages {
        stage('Read Secret File') {
            steps {
                script {
                    // withCredentials로 Secret File 경로 가져오기
                    withCredentials([file(credentialsId: 'secret_file', variable: 'SECRET_FILE')]) {
                        // Secret File 내용 읽기
                        def secretContent = readFile(SECRET_FILE).trim()
                        
                        // 읽어온 내용 출력 (보안 상 실제로는 출력하지 않는 것이 좋음)
                        echo "Secret Content: ${secretContent}"
                        
                        // 환경 변수로 저장하거나 사용할 수 있음
                        env.SECRET_CONTENT = secretContent
                    }
                }
            }
        }
        stage('Use Secret Content') {
            steps {
                script {
                    // 환경 변수 사용
                    echo "Using Secret Content: ${env.SECRET_CONTENT}"
                }
            }
        }
    }
}
