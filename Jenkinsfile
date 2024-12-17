pipeline {
    agent any

    environment {
        // Variables para Docker y credenciales
        DOCKER_IMAGE = "ghcr.io/tu-usuario/chat-service:latest"
        DOCKER_CREDENTIALS = "github-app-credentials"
    }

    stages {
        // Etapa 1: Clonar el repositorio
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        // Etapa 2: Compilar y construir el proyecto con Maven
        stage('Build Project') {
            steps {
                sh './mvnw clean install'
            }
        }

        // Etapa 3: Construir la imagen Docker
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        // Etapa 4: Publicar la imagen en GitHub Packages
        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDENTIALS}",
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'TOKEN'
                )]) {
                    script {
                        sh "echo ${TOKEN} | docker login ghcr.io -u ${USERNAME} --password-stdin"
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        // Etapa 5: Desplegar usando Docker Compose
        stage('Deploy Service') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'El pipeline se ejecutó correctamente 🎉'
        }
        failure {
            echo 'Error en el pipeline ❌'
        }
    }
}
