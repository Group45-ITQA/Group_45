pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    // Adding parameters allows us to control which tests to run
    parameters {
        booleanParam(name: 'RUN_FUNCTIONAL_TESTS', defaultValue: true, description: 'Run Functional Tests')
        booleanParam(name: 'RUN_API_TESTS', defaultValue: false, description: 'Run API Tests (Not yet implemented)')
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                git branch: 'main', url: 'https://github.com/Group45-ITQA/UI_Testing.git'
            }
        }

        // We'll make the test execution conditional
        stage('Test Suites') {
            stages {
                stage('Functional Tests') {
                    when {
                        expression { params.RUN_FUNCTIONAL_TESTS }
                    }
                    steps {
                        dir('Functional_Testing') {
                            bat 'mvn clean compile'
                            // Adding a warning message about API dependency
                            bat '''
                                echo "Note: Functional tests are running without complete API testing infrastructure"
                                mvn test
                            '''
                        }
                    }
                    post {
                        always {
                            dir('Functional_Testing') {
                                testNG(reportFilenamePattern: '**/target/surefire-reports/testng-results.xml',
                                      reportName: 'Functional Test Results')
                            }
                        }
                    }
                }

                stage('API Tests') {
                    when {
                        expression { params.RUN_API_TESTS }
                    }
                    steps {
                        // This will only run when API tests are implemented and enabled
                        dir('API_Testing') {
                            echo "API Testing project is not yet implemented"
                            // Commented out until API project is ready
                            // bat 'mvn clean compile'
                            // bat 'mvn test'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Test execution completed successfully!'
        }
        failure {
            echo 'Test execution failed!'
        }
        unstable {
            echo 'Test execution was unstable - some tests may have failed'
        }
    }
}