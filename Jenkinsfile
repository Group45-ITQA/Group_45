pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    stages {
        stage('Setup') {
            steps {
                // Clean workspace and get code
                cleanWs()
                git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
                
                // Create folders for reports
                bat 'mkdir test-reports'
            }
        }

        stage('Run Tests') {
            parallel {
                stage('UI Tests') {
                    steps {
                        dir('Functional_Testing') {
                            // Run UI tests and generate reports
                            bat 'mvn test -Dcucumber.filter.tags="@UIRegression"'
                        }
                    }
                }
                
                stage('API Tests') {
                    steps {
                        dir('API_Testing') {
                            // Run API tests and generate reports
                            bat 'mvn test -Dcucumber.filter.tags="@APIRegression"'
                        }
                    }
                }
            }
        }

        stage('Reports') {
            steps {
                // Publish TestNG reports for both test suites
                step([$class: 'TestNGPublisher', 
                      reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'])
                
                // Archive test results
                archiveArtifacts artifacts: '**/target/surefire-reports/**/*.*', 
                               allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}