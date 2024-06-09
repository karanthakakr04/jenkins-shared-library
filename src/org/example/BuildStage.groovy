#!/usr/bin/env groovy

package org.example

def call(String dockerhubUsername, String dockerhubRepo, String imageTag) {
  echo 'Build the Docker image with the incremented version...'
  dir('8 - Build Automation & CI-CD with Jenkins/jenkins-exercises') {
    sh "docker build -t ${dockerhubUsername}/${dockerhubRepo}:${imageTag} -f Dockerfile ."
  }
}