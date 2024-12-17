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
                
                // Verify feature files exist
                script {
                    echo "Verifying Cucumber feature files..."
                    if (!fileExists('Functional_Testing/src/test/resources/features')) {
                        error 'UI Testing feature files directory is missing!'
                    }
                    if (!fileExists('API_Testing/src/test/resources/features')) {
                        error 'API Testing feature files directory is missing!'
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        // Run UI tests with detailed output
                        dir('Functional_Testing') {
                            echo "Starting UI Tests..."
                            bat 'mvn clean test -Dcucumber.filter.tags="@UIRegression" -Dcucumber.publish.quiet=true'
                        }
                    } catch (Exception e) {
                        echo "UI Tests encountered issues: ${e.message}"
                        // Continue to API tests even if UI tests fail
                    }

                    try {
                        // Run API tests with detailed output
                        dir('API_Testing') {
                            echo "Starting API Tests..."
                            bat 'mvn clean test -Dcucumber.filter.tags="@APIRegression" -Dcucumber.publish.quiet=true'
                        }
                    } catch (Exception e) {
                        echo "API Tests encountered issues: ${e.message}"
                    }
                }
            }
        }

        stage('Generate Reports') {
            steps {
                script {
                    // Publish Cucumber reports
                    cucumber buildStatus: 'UNSTABLE',
                            reportTitle: 'Combined Test Report',
                            fileIncludePattern: '**/cucumber.json',
                            trendsLimit: 10,
                            classifications: [
                                [   
                                    'key': 'Browser',
                                    'value': 'Chrome'
                                ]
                            ]
                }
            }
        }
    }

    post {
        always {
            // Clean workspace after completion
            cleanWs()
        }
        success {
            echo '''
            ✅ Test Execution Summary:
            - All test suites completed
            - Reports have been generated
            - Workspace has been cleaned
            '''
        }
        failure {
            echo '''
            ❌ Test Execution Summary:
            - Some tests failed
            - Check the Cucumber reports for details
            - Review console output for specific errors
            '''
        }
    }
}