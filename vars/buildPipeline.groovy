#!/usr/bin/env groovy
def call(Map pipelineParams) {
  pipeline {
    agent any

    environment {
      IMAGE_TAG = "${env.IMAGE_VERSION}"
    }

    parameters {
        choice(
        name: 'VERSION_INCREMENT',
        choices: ['patch', 'minor', 'major'],
        description: 'Select the version increment type'
        )
    }

    stages {
      stage('Increment Version') {
        steps {
          script {
            // params.<parameter_name> is used for parameters defined within the pipeline using the parameters block and can be accessed throughout the pipeline.
            def versioningStage = new org.example.VersioningStage()
            versioningStage(params.VERSION_INCREMENT)
          }
        }
      }

      stage('Run Tests') {
        steps {
          script {
            def testStage = new org.example.TestStage()
            testStage()
          }
        }
      }

      stage('Build Image') {
        steps {
          script {
            // pipelineParams.<parameter_name> is used for custom parameters passed from the Jenkinsfile to the shared library function.
            def buildStage = new org.example.BuildStage()
            buildStage(pipelineParams.dockerhubUsername, pipelineParams.dockerhubRepo, env.IMAGE_TAG)
          }
        }
      }

      stage('Deploy') {
        steps {
          script {
            def deployStage = new org.example.DeployStage()
            deployStage(pipelineParams.dockerhubUsername, pipelineParams.dockerhubRepo, env.IMAGE_TAG)
          }
        }
      }

      stage('Commit Version') {
        steps {
          script {
            def commitStage = new org.example.CommitStage()
            commitStage(env.IMAGE_TAG)
          }
        }
      }
    }

    post {
      success {
        echo 'Pipeline executed successfully!'
      }
      failure {
        echo 'Pipeline execution failed!'
      }
    }
  }
}