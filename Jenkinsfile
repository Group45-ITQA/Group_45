pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
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
                    echo "🚀 Initializing Comprehensive Test Automation Pipeline"
                    echo "🔍 Preparing to run both UI and API tests"
                }
                
                // Checkout dev branch
                git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
            }
        }

        stage('Validate Project Structure') {
            steps {
                script {
                    // Check if project directories exist
                    def uiProjectExists = fileExists env.UI_PROJECT_DIR
                    def apiProjectExists = fileExists env.API_PROJECT_DIR

                    if (!uiProjectExists) {
                        error "❌ UI Testing project directory not found: ${env.UI_PROJECT_DIR}"
                    }

                    if (!apiProjectExists) {
                        error "❌ API Testing project directory not found: ${env.API_PROJECT_DIR}"
                    }
                }
            }
        }

        stage('Compile Projects') {
            parallel {
                stage('Compile UI Project') {
                    steps {
                        dir(env.UI_PROJECT_DIR) {
                            bat 'mvn clean compile'
                        }
                    }
                }

                stage('Compile API Project') {
                    steps {
                        dir(env.API_PROJECT_DIR) {
                            bat 'mvn clean compile'
                        }
                    }
                }
            }
        }

        stage('Run Comprehensive Test Suites') {
            parallel {
                stage('UI Functional Tests') {
                    steps {
                        dir(env.UI_PROJECT_DIR) {
                            bat 'mvn test -Dcucumber.filter.tags="@UIRegression"'
                        }
                    }
                    post {
                        always {
                            dir(env.UI_PROJECT_DIR) {
                                junit '**/target/surefire-reports/*.xml'
                                testNG(
                                    reportFilenamePattern: '**/target/surefire-reports/testng-results.xml',
                                    reportName: 'UI Functional Test Results'
                                )
                            }
                        }
                    }
                }

                stage('API Integration Tests') {
                    steps {
                        dir(env.API_PROJECT_DIR) {
                            bat 'mvn test -Dcucumber.filter.tags="@APIRegression"'
                        }
                    }
                    post {
                        always {
                            dir(env.API_PROJECT_DIR) {
                                junit '**/target/surefire-reports/*.xml'
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
            steps {
                script {
                    echo "📊 Consolidating Test Reports"
                    // You might want to use specific reporting plugins here
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
                echo "🏁 Pipeline completed with status: ${testStatus}"
            }
        }
        
        success {
            echo '✅ Both UI and API tests completed successfully!'
        }
        
        failure {
            echo '❌ Test execution encountered failures. Please review reports.'
        }
        
        unstable {
            echo '⚠️ Some tests failed or were unstable. Requires investigation.'
        }
    }
}