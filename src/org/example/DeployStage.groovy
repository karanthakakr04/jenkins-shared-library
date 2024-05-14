#!/usr/bin/env groovy

package org.example

def call(String dockerhubUsername, String dockerhubRepo, String imageTag) {
  echo "Pushing Docker image ${dockerhubUsername}/${dockerhubRepo}:${imageTag}"
  withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
    sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
    sh "docker push ${dockerhubUsername}/${dockerhubRepo}:${imageTag}"
  }
}