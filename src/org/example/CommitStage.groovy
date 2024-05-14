#!/usr/bin/env groovy

package org.example

def call(String imageVersion) {
  echo 'Committing the version increment to Git...'
  withCredentials([usernamePassword(credentialsId: 'github-credentials', usernameVariable: 'GITHUB_USERNAME', passwordVariable: 'GITHUB_PASSWORD')]) {
    sh "git config user.email 'jenkins@example.com'"
    sh "git config user.name 'Jenkins'"
    sh "git remote set-url origin https://${GITHUB_USERNAME}:${GITHUB_PASSWORD}@github.com/your-username/your-repository.git"
    dir('app') {
      sh "git add package.json"
    }
    sh "git commit -m 'Update version to ${imageVersion}'"
    sh "git push origin HEAD:main"
  }
}