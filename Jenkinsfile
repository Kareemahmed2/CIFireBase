pipeline {
    agent any

    environment {
        // Add NodeJS and global npm binaries to PATH
        NODEJS_HOME = "C:\\Program Files\\nodejs"
        NPM_GLOBAL = "${env.HOME}\\AppData\\Roaming\\npm"
        PATH = "${NODEJS_HOME};${NPM_GLOBAL};${env.PATH}"

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
                git branch: 'master', url: 'https://github.com/Kareemahmed2/CIFireBase.git'
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
}
