pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    // Enhanced parameters to control test execution
    parameters {
        choice(
            name: 'TEST_SCOPE', 
            choices: ['All', 'UI', 'API'], 
            description: 'Select which tests to run'
        )
        booleanParam(
            name: 'GENERATE_REPORTS', 
            defaultValue: true, 
            description: 'Generate comprehensive test reports'
        )
    }

    environment {
        // Define project directories
        UI_PROJECT_DIR = 'Functional_Testing'
        API_PROJECT_DIR = 'API_Testing'
    }

    stages {
        stage('Preparation') {
            steps {
                script {
                    // Clean workspace and provide detailed logging
                    cleanWs()
                    echo "Preparing test environment..."
                    echo "Selected Test Scope: ${params.TEST_SCOPE}"
                }
                
                // Checkout repository
                git branch: 'main', url: 'https://github.com/Group45-ITQA/Group_45.git'
            }
        }

        stage('Validate Project Structure') {
            steps {
                script {
                    // Check if project directories exist
                    def uiProjectExists = fileExists env.UI_PROJECT_DIR
                    def apiProjectExists = fileExists env.API_PROJECT_DIR

                    if (!uiProjectExists) {
                        error "UI Testing project directory not found: ${env.UI_PROJECT_DIR}"
                    }

                    if (!apiProjectExists) {
                        error "API Testing project directory not found: ${env.API_PROJECT_DIR}"
                    }
                }
            }
        }

        stage('Compile and Validate Projects') {
            parallel {
                stage('Compile UI Project') {
                    when {
                        expression { 
                            params.TEST_SCOPE == 'All' || params.TEST_SCOPE == 'UI' 
                        }
                    }
                    steps {
                        dir(env.UI_PROJECT_DIR) {
                            bat 'mvn clean compile'
                        }
                    }
                }

                stage('Compile API Project') {
                    when {
                        expression { 
                            params.TEST_SCOPE == 'All' || params.TEST_SCOPE == 'API' 
                        }
                    }
                    steps {
                        dir(env.API_PROJECT_DIR) {
                            bat 'mvn clean compile'
                        }
                    }
                }
            }
        }

        stage('Run Test Suites') {
            parallel {
                stage('UI Functional Tests') {
                    when {
                        expression { 
                            params.TEST_SCOPE == 'All' || params.TEST_SCOPE == 'UI' 
                        }
                    }
                    steps {
                        dir(env.UI_PROJECT_DIR) {
                            bat 'mvn test -Dcucumber.filter.tags="@UIRegression"'
                        }
                    }
                    post {
                        always {
                            dir(env.UI_PROJECT_DIR) {
                                testNG(
                                    reportFilenamePattern: '**/target/surefire-reports/testng-results.xml',
                                    reportName: 'UI Functional Test Results'
                                )
                            }
                        }
                    }
                }

                stage('API Integration Tests') {
                    when {
                        expression { 
                            params.TEST_SCOPE == 'All' || params.TEST_SCOPE == 'API' 
                        }
                    }
                    steps {
                        dir(env.API_PROJECT_DIR) {
                            bat 'mvn test -Dcucumber.filter.tags="@APIRegression"'
                        }
                    }
                    post {
                        always {
                            dir(env.API_PROJECT_DIR) {
                                testNG(
                                    reportFilenamePattern: '**/target/surefire-reports/testng-results.xml',
                                    reportName: 'API Integration Test Results'
                                )
                            }
                        }
                    }
                }
            }
        }

        stage('Generate Comprehensive Reports') {
            when {
                expression { params.GENERATE_REPORTS }
            }
            steps {
                script {
                    // You might want to use specific reporting plugins here
                    echo "Generating comprehensive test reports..."
                }
            }
        }
    }

    post {
        always {
            script {
                // Cleanup and final status
                cleanWs()
                
                // Enhanced status notification
                def testStatus = currentBuild.result ?: 'SUCCESS'
                echo "Pipeline completed with status: ${testStatus}"
            }
        }
        
        success {
            echo '✅ All tests completed successfully!'
        }
        
        failure {
            echo '❌ Test execution encountered failures. Please review reports.'
            // Optional: Send notifications (email, Slack, etc.)
        }
        
        unstable {
            echo '⚠️ Some tests failed or were unstable. Requires investigation.'
        }
    }
}