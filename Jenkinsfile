pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    stages {
        stage('Get Code') {
            steps {
                cleanWs()
                git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
            }
        }

        stage('Run Tests') {
            parallel {
                stage('UI Tests') {
                    steps {
                        dir('Functional_Testing') {
                            // Verify testng.xml exists before running tests
                            script {
                                if (!fileExists('testng.xml')) {
                                    error 'testng.xml not found in Functional_Testing directory'
                                }
                            }
                            bat 'mvn clean test'
                        }
                    }
                }
                
                stage('API Tests') {
                    steps {
                        dir('API_Testing') {
                            bat 'mvn clean test'
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'All tests completed successfully!'
        }
        failure {
            echo 'Some tests failed - check the test results for details'
        }
        always {
            cleanWs()
        }
    }
}