
pipeline {
    agent any
    stages {
        stage('Inject Environment Variables') {
            steps {
                script {
                    // Jenkins Credentials에서 파일 경로 가져오기
                    withCredentials([file(credentialsId: 'secret_file', variable: 'ENV_FILE_PATH')]) {
                        // EnvInject를 사용해 파일 주입
                        writeFile file: 'env.properties', text: readFile(ENV_FILE_PATH)

                        // EnvInject로 환경 변수 설정
                        properties([
                            pipelineTriggers([]),
                            [$class: 'EnvInjectJobProperty',
                                info: [
                                    propertiesFilePath: 'env.properties'
                                ]
                            ]
                        ])
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
