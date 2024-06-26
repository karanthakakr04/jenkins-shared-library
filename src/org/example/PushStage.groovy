#!/usr/bin/env groovy

package org.example

def call(String dockerhubUsername, String dockerhubRepo, String imageTag) {
  echo 'Push the Docker image to a registry...'
  withCredentials([usernamePassword(credentialsId: 'docker-hub-access', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
    sh """
      echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin
      docker push ${dockerhubUsername}/${dockerhubRepo}:${imageTag}
    """
  }
}