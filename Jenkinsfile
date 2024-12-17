pipeline {
    agent any

    // Define tools needed for the pipeline
    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    // Define options to customize pipeline behavior
    options {
        // Add timestamps to console output
        timestamps()
        // Enable stage timing in the Jenkins UI
        buildDiscarder(logRotator(numToKeepStr: '10'))
        // Disable concurrent builds
        disableConcurrentBuilds()
    }

    // Environment variables for project configuration
    environment {
        UI_PROJECT_DIR = 'Functional_Testing'
        API_PROJECT_DIR = 'API_Testing'
        // Add report directories for better organization
        REPORT_DIR = 'test-reports'
        CUCUMBER_REPORT_DIR = "${REPORT_DIR}/cucumber-reports"
    }

    stages {
        stage('Initialize') {
            steps {
                // Clean workspace and checkout code
                cleanWs()
                echo "🚀 Starting Test Automation Pipeline"
                git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
            }
        }

        stage('Project Validation') {
            steps {
                script {
                    // Validate project structure
                    if (!fileExists(env.UI_PROJECT_DIR)) {
                        error "❌ UI Testing directory not found: ${env.UI_PROJECT_DIR}"
                    }
                    if (!fileExists(env.API_PROJECT_DIR)) {
                        error "❌ API Testing directory not found: ${env.API_PROJECT_DIR}"
                    }
                    
                    // Create report directories
                    sh "mkdir -p ${env.REPORT_DIR}"
                    sh "mkdir -p ${env.CUCUMBER_REPORT_DIR}"
                }
            }
        }

        stage('Dependencies') {
            parallel {
                stage('UI Dependencies') {
                    steps {
                        dir(env.UI_PROJECT_DIR) {
                            bat 'mvn clean install -DskipTests'
                        }
                    }
                }
                stage('API Dependencies') {
                    steps {
                        dir(env.API_PROJECT_DIR) {
                            bat 'mvn clean install -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Execute Tests') {
            parallel {
                stage('UI Tests') {
                    stages {
                        stage('Run UI Tests') {
                            steps {
                                dir(env.UI_PROJECT_DIR) {
                                    bat 'mvn test -Dcucumber.filter.tags="@UIRegression"'
                                }
                            }
                        }
                        stage('UI Test Reports') {
                            steps {
                                dir(env.UI_PROJECT_DIR) {
                                    // Publish TestNG results
                                    step([$class: 'TestNGPublisher',
                                          reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'])
                                    
                                    // Publish Cucumber reports
                                    cucumber buildStatus: 'UNSTABLE',
                                            reportTitle: 'UI Test Report',
                                            fileIncludePattern: '**/cucumber-reports/*.json',
                                            trendsLimit: 10
                                }
                            }
                        }
                    }
                }
                
                stage('API Tests') {
                    stages {
                        stage('Run API Tests') {
                            steps {
                                dir(env.API_PROJECT_DIR) {
                                    bat 'mvn test -Dcucumber.filter.tags="@APIRegression"'
                                }
                            }
                        }
                        stage('API Test Reports') {
                            steps {
                                dir(env.API_PROJECT_DIR) {
                                    // Publish TestNG results
                                    step([$class: 'TestNGPublisher',
                                          reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'])
                                    
                                    // Publish Cucumber reports
                                    cucumber buildStatus: 'UNSTABLE',
                                            reportTitle: 'API Test Report',
                                            fileIncludePattern: '**/cucumber-reports/*.json',
                                            trendsLimit: 10
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Consolidate Reports') {
            steps {
                script {
                    echo "📊 Generating Combined Test Reports"
                    // Archive the test reports
                    archiveArtifacts artifacts: '**/target/surefire-reports/**/*.*,**/cucumber-reports/**/*.*',
                                   allowEmptyArchive: true
                }
            }
        }
    }

    post {
        always {
            script {
                // Clean workspace
                cleanWs()
                def testStatus = currentBuild.result ?: 'SUCCESS'
                echo "🏁 Pipeline completed with status: ${testStatus}"
            }
        }
        success {
            echo '✅ All test suites completed successfully!'
        }
        failure {
            echo '❌ Test execution failed. Please check the reports for details.'
        }
        unstable {
            echo '⚠️ Test execution was unstable. Some tests may have failed.'
        }
    }
}