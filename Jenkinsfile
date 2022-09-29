pipeline {
    agent any
    
    environment{
    	DOCKER_IMG_NAME="user-service"
    	DOCKER_TMP_CONTAINER_NAME="tmp-user-service-container"
    	DOCKER_REPO="preeti2903"
    }

    stages {
        stage('clean') {
            steps {
            sh 'mvn clean'  
            }
        }
        
        stage('compile') {
            steps {
            sh 'mvn compile'  
            }
        }
        
         stage('test') {
            steps {
            echo 'running tests.....'
            sh 'mvn test'  
            }
        }
        
        /*stage('quality'){
        	steps{
        	 	sh 'mvn sonar:sonar'
        	 	}
        	} */	
        	
        stage('build') {
            steps {
            sh 'mvn package -DskipTests=true'  
            }
        }
         stage('dockerize') {
            steps {
            echo 'building the docker inmage for user-service..'
            sh "docker build -t ${DOCKER_IMG_NAME}:latest -t ${DOCKER_IMG_NAME}:${env.BUILD_ID} ."  
            }
        }
         stage('integration tests') {
            steps {
            echo 'running the tmp-user-service-container for integration testing..'
            sh "docker run -dp 7070:8080 --rm --name ${DOCKER_TMP_CONTAINER_NAME} ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest" 
            sleep 10
            sh 'curl -i http://localhost:7070/api/users'  
            }
        }
         stage('docker publish') {
            steps {
				withDockerRegistry([credentialsId:'docker_creds',url:'']) {
				sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest"
				sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}:latest"
				
				}
			}
		}
     }

     post {
            always {
            sh "docker stop ${DOCKER_TMP_CONTAINER_NAME}"
            sh "docker rmi ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}"
            }
        }
    
}
