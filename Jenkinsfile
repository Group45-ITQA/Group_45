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
                            script {
                                if (!fileExists('testing.xml')) {
                                    error 'testing.xml not found in Functional_Testing directory'
                                }
                            }
                            bat 'mvn clean test'
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
                            // Start the JAR from root directory
                            bat 'start java -jar demo-0.0.1-SNAPSHOT.jar'
                            // Wait for application to initialize
                            sleep(time: 30, unit: 'SECONDS')
                            
                            // Run API tests
                            dir('API_Testing') {
                                bat 'mvn clean test'
                            }
                        }
                    }
                    post {
                        always {
                            // Stop the JAR application
                            bat 'taskkill /F /IM java.exe'
                            
                            // Generate reports
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
