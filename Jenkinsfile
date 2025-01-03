pipeline {
    agent any
    stages {
        stage('Inject Environment Variables') {
            steps {
                script {
                    // Jenkins Credentials에서 파일 경로 가져오기
                    withCredentials([file(credentialsId: 'secret_file', variable: 'ENV_FILE_PATH')]) {
                        // EnvInject 플러그인으로 환경 변수 파일 주입
                        injectEnvVars(propertiesFilePath: ENV_FILE_PATH)
                    }
                }
            }
        }
        stage('Use Injected Variables') {
            steps {
                script {
                    // 주입된 환경 변수 사용
                    echo "NAME: ${env.NAME}"
                    echo "PORT: ${env.PORT}"
                }
            }
        }
    }
}


