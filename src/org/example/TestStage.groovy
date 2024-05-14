#!/usr/bin/env groovy

package org.example

def call() {
  echo 'Running tests for the application...'
  dir('app') {
    sh 'npm install'
    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
      sh 'npm test'
      error "Tests failed. Please fix the failing tests and rerun the pipeline."
    }
  }
}