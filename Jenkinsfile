pipeline {
    agent any
    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
    }

    stages {
        stage('Get Code') {
            steps {
                cleanWs()
                git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
            }
        }

        stage('Download Dependencies') {
            parallel {
                stage('UI Test Dependencies') {
                    steps {
                        dir('Functional_Testing') {
                            // Download dependencies once
                            bat 'mvn dependency:go-offline'
                        }
                    }
                }
                stage('API Test Dependencies') {
                    steps {
                        dir('API_Testing') {
                            bat 'mvn dependency:go-offline'
                        }
                    }
                }
            }
        }

        stage('Run Tests') {
            parallel {
                stage('UI Tests') {
                    steps {
                        dir('Functional_Testing') {
                            script {
                                if (!fileExists('testing.xml')) {
                                    error 'testing.xml not found in Functional_Testing directory'
                                }
                            }
                            // Use offline mode to avoid downloading dependencies again
                            bat 'mvn clean test -o'
                        }
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml'
                            allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'Functional_Testing/target/allure-results']]
                            ])
                        }
                    }
                }
                
                stage('API Tests') {
                    steps {
                        script {
                            bat 'start java -jar demo-0.0.1-SNAPSHOT.jar'
                            sleep(time: 30, unit: 'SECONDS')
                            
                            dir('API_Testing') {
                                bat 'mvn clean test -o'
                            }
                        }
                    }
                    post {
                        always {
                            bat 'taskkill /F /IM java.exe || exit 0'  // Added || exit 0 to prevent failure if process already stopped
                            junit '**/target/surefire-reports/*.xml'
                            allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'API_Testing/target/allure-results']]
                            ])
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
