#!/usr/bin/env groovy

package org.example

def call(String dockerhubUsername, String dockerhubRepo, String imageTag) {
  echo "Building Docker image ${dockerhubUsername}/${dockerhubRepo}:${imageTag}"
  sh "docker build -t ${dockerhubUsername}/${dockerhubRepo}:${imageTag} ."
}