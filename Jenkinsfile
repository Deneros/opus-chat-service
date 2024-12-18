pipeline {
    agent any

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
                withCredentials([gitHubApp(
                    credentialsId: "opus-githubapp",
                    appIDVariable: 'APP_ID',
                    privateKeyVariable: 'PRIVATE_KEY'
                )]) {
                    script {
                        // Authenticate with GitHub using Docker
                        sh "echo ${PRIVATE_KEY} | docker login ghcr.io -u ${APP_ID} --password-stdin"

                        // Push the image
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }
    }
}
