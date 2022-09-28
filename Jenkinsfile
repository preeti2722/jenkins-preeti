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
        
<<<<<<< HEAD
        stage('quality'){
        	steps{
        	 	sh 'mvn sonar:sonar'
        	 	}
        	} 	
        stage('build') {
=======
        stage('jar') {
>>>>>>> e5bdb463a6695a8bab37f83e0ef9a14dae8ce54c
            steps {
            sh 'mvn package -DskipTests=true'  
            }
        }
    }
}
