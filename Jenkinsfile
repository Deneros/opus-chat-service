pipeline {
    agent {
        label 'opus-agent'  
    }

    environment {
        DOCKER_IMAGE = "ghcr.io/deneros/chat-service:latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'opus-githubapp',
                                                 usernameVariable: 'GITHUB_APP',
                                                 passwordVariable: 'GITHUB_ACCESS_TOKEN')]) {
                    script {
                        sh "docker --version" 
                        // Authenticate with GitHub using Docker
                        sh "echo ${GITHUB_ACCESS_TOKEN} | docker login ghcr.io -u ${APP_ID} --password-stdin"
                        // Push the image
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }
    }
}
