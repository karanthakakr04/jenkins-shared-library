#!/usr/bin/env groovy

package org.example

def call() {
  echo 'Run tests for the application...'
  dir('8 - Build Automation & CI-CD with Jenkins/jenkins-exercises/app') {
    sh 'npm install'
    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
      script {
        def testResult = sh(script: 'npm test -- --detectOpenHandles', returnStatus: true)
        if (testResult != 0) {
          error "Tests failed. Please fix the failing tests and rerun the pipeline."
        }
      }
    }
  }
}