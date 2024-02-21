pipeline {
    agent any
    parameters {
  string defaultValue: 'master', name: 'BRANCH', trim: true
}


    stages {
        stage('Git checkout') {
            steps {
                git branch: '${BRANCH}', url: 'https://github.com/MaciejZacharski/RestAssuredTests'

            }
        }
         stage('Run REST Assured API Tests') {
            steps {
                powershell '''mvn clean test
'''

            }
        }
            stage('Create Html Report') {
            steps {
               publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/surefire-reports', reportFiles: 'emailable-report.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])

            }
        }

    }
}