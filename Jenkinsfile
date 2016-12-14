#!/usr/bin/env groovy

def config = [emailRecipients: 'your.email.here@email.com']
def buildStateHasChanged = false;

pipeline {
    agent docker: 'maven:3.3.9-alpine'

    jobProperties { 
        buildDiscarder(logRotator(numToKeepStr:'10'))
    }

    stages {
        stage('maven-build') {
            steps {
                withMaven(mavenSettingsConfig: 'vantage') {
                    sh 'mvn clean install'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            junit '**/target/surefire-reports/TEST-*.xml'

        }

        // Mail only on SUCCESS if we were previously FAILURE
        success {
            script {
                if (buildStateHasChanged == true) {
                    sendEmail(config, 'SUCCESS')
                }
            }
        }

        // We're doing this to catch build state changes in order to allow us to
        // mail SUCCESS only when it changes from FAILURE to SUCCESS
        changed {
            echo "Build state has changed..."
            script {
                buildStateHasChanged = true
            }
        }

        // Mail on all FAILURE
        failure {
            sendEmail(config, 'FAILURE')
        }
    }    
}

def sendEmail(config, status) {
    def subject = config.subject ? config.subject : "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${status}!"
    def content = '$DEFAULT_CONTENT'

    def to = []
    to << emailextrecipients([[$class: 'RequesterRecipientProvider']])

    if (config.emailRecipients != null) {
        to << config.emailRecipients
    }

    if (status != 'SUCCESS') {
        content += '\n$BUILD_LOG'
        to << emailextrecipients([[$class: 'CulpritsRecipientProvider']])
    }

    emailext(body: content, mimeType:'text/plain',
        replyTo: '$DEFAULT_REPLYTO',
        subject: subject,
        to: to.join(','))
}
