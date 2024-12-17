pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    stages {
        stage('Get Code') {
            steps {
                // Clean workspace and get code from GitHub
                cleanWs()
                git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
            }
        }

        stage('Run Tests') {
            parallel {
                // Run UI tests
                stage('UI Tests') {
                    steps {
                        dir('Functional_Testing') {
                            bat 'mvn clean test'
                        }
                    }
                }
                
                // Run API tests
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
        // Clean up after we're done
        always {
            cleanWs()
        }
    }
}