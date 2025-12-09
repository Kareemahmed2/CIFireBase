pipeline {
    agent any

    tools {
        jdk 'JDK21'        // Make sure this matches the name in Global Tool Configuration
        maven 'Maven3'     // Same for Maven installation
        nodejs 'NodeJs'  // Node version for Firebase CLI
    }

    environment {
        FIREBASE_TOKEN = credentials('FIREBASE_TOKEN') // Your Firebase CI token from credentials
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
                sh 'npm install -g firebase-tools'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Deploy to Firebase Hosting') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                sh 'firebase deploy --only hosting'
            }
        }
    }

    post {
        success {
            echo '🎉 Build successful! Deployed to Firebase Hosting.'
        }
        failure {
            echo '❌ Build failed. Tests did NOT pass. Deployment skipped.'
        }
    }
}
