#!/usr/bin/env groovy

def call(Map pipelineParams) {

  stage('Increment Version') {
    script {
      def versioningStage = new org.example.VersioningStage()
      versioningStage()
    }
  }

  stage('Run Tests') {
    script {
      def testStage = new org.example.TestStage()
      testStage()
    }
  }

  stage('Build Image') {
    script {
      def buildStage = new org.example.BuildStage()
      buildStage(pipelineParams.dockerhubUsername, pipelineParams.dockerhubRepo, env.IMAGE_VERSION)
    }
  }

  stage('Push Image') {
    script {
      def pushStage = new org.example.PushStage()
      pushStage(pipelineParams.dockerhubUsername, pipelineParams.dockerhubRepo, env.IMAGE_VERSION)
    }
  }

  stage('Commit Version') {
    script {
      def commitStage = new org.example.CommitStage()
      commitStage(env.IMAGE_VERSION, pipelineParams.githubRepoUrl)
    }
  }
}