#!/usr/bin/env groovy

package org.example

def call(String dockerhubUsername, String dockerhubRepo, String imageTag) {
  echo "Building Docker image ${dockerhubUsername}/${dockerhubRepo}:${imageTag}"
  dir('8 - Build Automation & CI-CD with Jenkins/jenkins-exercises') {
    sh "docker build -t ${dockerhubUsername}/${dockerhubRepo}:${imageTag} -f Dockerfile ."
  }
}