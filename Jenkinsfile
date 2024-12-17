pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        jdk 'JDK 21'
    }

    environment {
        // Project directories
        FUNCTIONAL_TEST_DIR = 'Functional_Testing'
        API_TEST_DIR = 'API_Testing'
        
        // Chrome configuration
        CHROME_DRIVER_VERSION = '120.0.6099.109'
        
        // Test configurations
        API_BASE_URL = 'http://localhost:7081'
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    // Clean workspace and get fresh code
                    cleanWs()
                    echo "🚀 Initializing test environment..."
                    git branch: 'dev', url: 'https://github.com/Group45-ITQA/Group_45.git'
                }
            }
        }

        stage('Setup Test Environment') {
            parallel {
                stage('Setup UI Tests') {
                    steps {
                        script {
                            echo "🔧 Setting up ChromeDriver for UI tests..."
                            
                            // Create WebDriver directory
                            bat 'mkdir -p webdriver'
                            
                            // Download and setup ChromeDriver
                            powershell '''
                                $ChromeDriverUrl = "https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/120.0.6099.109/win64/chromedriver-win64.zip"
                                $OutputFile = "webdriver\\chromedriver.zip"
                                
                                Invoke-WebRequest -Uri $ChromeDriverUrl -OutFile $OutputFile
                                Expand-Archive -Path $OutputFile -DestinationPath "webdriver" -Force
                                Move-Item -Path "webdriver\\chromedriver-win64\\chromedriver.exe" -Destination "webdriver\\chromedriver.exe" -Force
                            '''
                            
                            bat 'setx PATH "%PATH%;%CD%\\webdriver" /M'
                        }
                    }
                }
                
                stage('Setup API Tests') {
                    steps {
                        script {
                            echo "📡 Verifying API test configuration..."
                            // Verify API server is accessible
                            bat """
                                curl --connect-timeout 10 --max-time 10 ${API_BASE_URL} || echo "Warning: API server might not be running"
                            """
                        }
                    }
                }
            }
        }

        stage('Execute Tests') {
            parallel {
                stage('Run UI Tests') {
                    steps {
                        script {
                            try {
                                dir(FUNCTIONAL_TEST_DIR) {
                                    echo "🌐 Running UI functional tests..."
                                    bat '''
                                        mvn clean test ^
                                        -Dmaven.test.failure.ignore=true ^
                                        -Dcucumber.plugin="html:target/cucumber-reports/ui-tests.html" ^
                                        -Dcucumber.plugin="json:target/cucumber-reports/ui-tests.json"
                                    '''
                                }
                            } catch (Exception e) {
                                echo "⚠️ UI tests encountered an error: ${e.message}"
                                currentBuild.result = 'UNSTABLE'
                            }
                        }
                    }
                }
                
                stage('Run API Tests') {
                    steps {
                        script {
                            try {
                                dir(API_TEST_DIR) {
                                    echo "🔌 Running API integration tests..."
                                    bat '''
                                        mvn clean test ^
                                        -Dmaven.test.failure.ignore=true ^
                                        -Dcucumber.plugin="html:target/cucumber-reports/api-tests.html" ^
                                        -Dcucumber.plugin="json:target/cucumber-reports/api-tests.json" ^
                                        -Dbase.url=%API_BASE_URL%
                                    '''
                                }
                            } catch (Exception e) {
                                echo "⚠️ API tests encountered an error: ${e.message}"
                                currentBuild.result = 'UNSTABLE'
                            }
                        }
                    }
                }
            }
        }

        stage('Generate Reports') {
            steps {
                script {
                    echo "📊 Generating test reports..."
                    
                    // Publish UI Test Reports
                    cucumber buildStatus: 'UNSTABLE',
                            reportTitle: 'UI Functional Test Report',
                            fileIncludePattern: '**/ui-tests.json',
                            trendsLimit: 10,
                            classifications: [
                                ['key': 'Browser', 'value': 'Chrome'],
                                ['key': 'Environment', 'value': 'Test']
                            ]
                    
                    // Publish API Test Reports
                    cucumber buildStatus: 'UNSTABLE',
                            reportTitle: 'API Integration Test Report',
                            fileIncludePattern: '**/api-tests.json',
                            trendsLimit: 10,
                            classifications: [
                                ['key': 'API URL', 'value': env.API_BASE_URL],
                                ['key': 'Environment', 'value': 'Test']
                            ]
                }
            }
        }
    }

    post {
        always {
            script {
                echo "🧹 Performing cleanup..."
                
                // Archive test artifacts
                archiveArtifacts artifacts: '**/target/cucumber-reports/**/*', 
                               allowEmptyArchive: true
                
                // Cleanup processes
                bat '''
                    taskkill /F /IM chromedriver.exe /T > nul 2>&1 || exit /B 0
                    taskkill /F /IM chrome.exe /T > nul 2>&1 || exit /B 0
                '''
                
                cleanWs()
            }
        }
        success {
            echo '''
            ✅ Test Execution Summary:
            - Both UI and API test suites completed
            - Reports generated successfully
            - All resources cleaned up
            '''
        }
        failure {
            echo '''
            ❌ Test Execution Summary:
            - One or more test suites failed
            - Check individual test reports for details
            - Review console output for specific errors
            '''
        }
        unstable {
            echo '''
            ⚠️ Test Execution Summary:
            - Tests completed with some failures
            - Check test reports for failed scenarios
            - Verify system prerequisites and configurations
            '''
        }
    }
}