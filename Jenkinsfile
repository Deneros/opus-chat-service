pipeline {
    agent none // No usar un agente global, asignar agentes por etapa

    environment {
        DOCKER_IMAGE = "ghcr.io/deneros/chat-service:latest" // Imagen Docker
    }

    stages {
        stage('Build Docker Image') {
            agent { label 'opus-agent' } // Agente para construir la imagen
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push Docker Image') {
            agent { label 'opus-agent' } // Agente para empujar la imagen
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-pat',
                                                 usernameVariable: 'GITHUB_USER',
                                                 passwordVariable: 'GITHUB_PAT')]) {
                    script {
                        sh "echo ${GITHUB_PAT} | docker login ghcr.io -u ${GITHUB_USER} --password-stdin"
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            agent { label 'kubectl-agent' } // Agente para manejar Kubernetes
            steps {
                script {
                    // Actualiza el Deployment en Kubernetes
                    sh "kubectl set image deployment/chat-service chat-service=${DOCKER_IMAGE} --record"
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed."
        }
        success {
            echo "Deployment successful."
        }
        failure {
            echo "Pipeline failed."
        }
    }
}



// pipeline {
//     agent {
//         label 'opus-agent'  
//     }

//     environment {
//         DOCKER_IMAGE = "ghcr.io/deneros/chat-service:latest"
//         // SONARQUBE_SERVER = 'SonarQubeServer'
//         // KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-id'
//     }

//     stages {
//         stage('Checkout Code') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Run Unit Tests') {
//             steps {
//                 script {
//                     sh './gradlew test' // Cambiar a la herramienta de pruebas que uses
//                 }
//             }
//         }

//         // stage('Code Quality Analysis') {
//         //     steps {
//         //         withSonarQubeEnv("${SONARQUBE_SERVER}") {
//         //             script {
//         //                 sh './gradlew sonarqube' // Comando para análisis SonarQube
//         //             }
//         //         }
//         //     }
//         // }

//         stage('Build Docker Image') {
//             steps {
//                 script {
//                     sh "docker build -t ${DOCKER_IMAGE} ."
//                 }
//             }
//         }

//         stage('Push Docker Image') {
//             steps {
//                 withCredentials([usernamePassword(credentialsId: 'github-pat',
//                                                  usernameVariable: 'GITHUB_USER',
//                                                  passwordVariable: 'GITHUB_PAT')]) {
//                     script {
//                         // Docker login using PAT
//                         sh "echo ${GITHUB_PAT} | docker login ghcr.io -u ${GITHUB_USER} --password-stdin"
                        
//                         // Push the Docker image
//                         sh "docker push ${DOCKER_IMAGE}"
//                     }
//                 }
//             }
//         }

//         stage('Deploy to Kubernetes') {
//             steps {
//                 withKubeConfig(credentialsId: "${KUBECONFIG_CREDENTIALS_ID}") {
//                     script {
//                         // Actualiza el deployment en Kubernetes
//                         sh "kubectl set image deployment/chat-service chat-service=${DOCKER_IMAGE} --record"
//                     }
//                 }
//             }
//         }
//     }

//     post {
//         always {
//             echo "Pipeline completed."
//         }
//         success {
//             echo "Deployment successful."
//         }
//         failure {
//             echo "Pipeline failed."
//         }
//     }
// }
