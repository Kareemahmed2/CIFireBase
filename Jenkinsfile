pipeline {
    agent any

    tools {
        jdk 'JDK21'        // Must match Jenkins global tool name
        maven 'Maven3'
    }

    environment {
        // Add your local NodeJS path to PATH
        PATH = "C:\\Program Files\\nodejs;${env.PATH}"
        FIREBASE_TOKEN = credentials('FIREBASE_TOKEN')
    }

    stages {

        stage('Verify NodeJS') {
            steps {
                bat 'node -v'
                bat 'npm -v'
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Kareemahmed2/CIFireBase.git'
            }
        }

        stage('Install Firebase CLI') {
            steps {
                bat 'npm install -g firebase-tools'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn -B test'
            }
        }

        stage('Deploy to Firebase Hosting') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                bat 'firebase deploy --only hosting --token %FIREBASE_TOKEN%'
            }
        }
    }

    post {
        success {
            echo '🎉 Build successful! Tests passed and deployed to Firebase Hosting.'
        }
        failure {
            echo '❌ Build failed. Deployment skipped.'
        }
    }
}
