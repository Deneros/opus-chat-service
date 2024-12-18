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
                withCredentials([usernamePassword(credentialsId: 'github-pat',
                                                 usernameVariable: 'GITHUB_USER',
                                                 passwordVariable: 'GITHUB_PAT')]) {
                    script {
                        // Docker login using PAT
                        sh "echo ${GITHUB_PAT} | docker login ghcr.io -u ${GITHUB_USER} --password-stdin"
                        
                        // Push the Docker image
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }
    }
}
