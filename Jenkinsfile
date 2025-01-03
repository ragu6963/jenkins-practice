
pipeline {
    agent any
    stages { 
      stage('Checkout') {
    steps {
        checkout scm
    }
}
        stage('Use Injected Variables') {
            steps {
                script {
                    // 주입된 환경 변수 사용
                    echo "MultiBranch"
                }
            }
        }
    }
}
