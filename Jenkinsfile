pipeline {
    agent any

    tools {
        jdk 'JDK21'        // Match the name in Jenkins Global Tool Configuration
        maven 'Maven3'
        nodejs 'nodeJs'    // Match NodeJS installation name in Jenkins
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
                script {
                    // Get NodeJS path from Jenkins
                    def nodeHome = tool name: 'nodeJs', type: 'NodeJS'
                    withEnv(["PATH=${nodeHome}\\bin;${env.PATH}"]) {
                        bat 'npm install -g firebase-tools'
                    }
                }
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
                script {
                    def nodeHome = tool name: 'nodeJs', type: 'NodeJS'
                    withEnv(["PATH=${nodeHome}\\bin;${env.PATH}"]) {
                        bat 'firebase deploy --only hosting --token %FIREBASE_TOKEN%'
                    }
                }
            }
        }
    }

    post {
        success {
            echo "🎉 Build successful! Tests passed and deployed to Firebase Hosting."
        }
        failure {
            echo "❌ Build failed. Deployment skipped."
        }
    }
}
