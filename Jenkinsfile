pipeline {
    agent any
    
    environmet{
    	DOCKER_IMG_NAME='user-service'
    	DOCKER_TMP_CONTAINER_NAME='tmp-user-service-container'
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
            sh 'docker build -t ${DOCKER_IMG_NAME}:latest -t ${DOCKER_IMG_NAME}:${env.BUILD_ID} .'  
            }
        }
         stage('integration tests') {
            steps {
            echo 'running the tmp-user-service-container for integration testing..'
            sh 'docker run -dp 7070:8080 --rm --name ${DOCKER_TMP_CONTAINER_NAME} ${DOCKER_IMG_NAME}:latest' 
            sleep 30
            sh 'curl -i http://localhost:7070/api/users'  
            }
        }
        
    }
     post {
            always {
            sh 'docker stop ${DOCKER_TMP_CONTAINER_NAME}'  
            }
        }
    
}
