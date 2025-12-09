pipeline {
    agent any

    tools {
        jdk 'JDK21'        // Must match name in Jenkins global tools
        maven 'Maven3'
        nodejs 'nodeJs'
    }

    environment {
        FIREBASE_TOKEN = credentials('FIREBASE_TOKEN')
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Kareemahmed2/CIFireBase.git'
            }
        }

        stage('Install Firebase CLI') {
            steps {
                bat "npm install -g firebase-tools"
            }
        }

        stage('Build') {
            steps {
                bat "mvn -B clean compile"
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn -B test"
            }
        }

        stage('Deploy to Firebase Hosting') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                bat "firebase deploy --only hosting --token %FIREBASE_TOKEN%"
            }
        }
    }

    post {
        success {
            echo "🎉 Build successful! Tests passed. Deployed to Firebase Hosting."
        }
        failure {
            echo "❌ Build failed. Deployment skipped."
        }
    }
}
