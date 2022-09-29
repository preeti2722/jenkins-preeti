pipeline {
    agent any

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
            sh 'docker build -t user-service:latest .'  
            }
        }
         stage('integration tests') {
            steps {
            sh 'docker run -dp 7070:8080 --rm --name tmp-user-service-container user-service:latest' 
            sleep 30
            sh 'curl -i http://localhost:7070/api/users'  
            }
        }
        
    }
     post {
            always {
            sh 'docker stop tmp-user-service-container'  
            }
        }
    
}
