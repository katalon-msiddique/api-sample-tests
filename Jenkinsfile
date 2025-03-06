pipeline {
    agent any
    environment {
        KATALON_CMD = "/Applications/Katalon Studio Engine.app/Contents/MacOS/katalonc"
        KATALON_API_KEY = credentials('KATALON_API_KEY')
        PROJECT_PATH = "${WORKSPACE}/main/api-sample-tests.prj"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/katalon-msiddique/api-sample-tests'
            }
        }
        stage('Execute Katalon Tests') {
            steps {
                sh '''
                ${KATALON_CMD} -noSplash -runMode=console \
                -projectPath="${PROJECT_PATH}" \
                -retry=0 -testSuitePath="Test Suites/YourTestSuite" \
                -executionProfile="default" -browserType="Chrome" \
                -apiKey=${KATALON_API_KEY}
                '''
            }
        }
    }
}
