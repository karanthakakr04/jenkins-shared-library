#!/usr/bin/env groovy

package org.example

def call(String imageVersion, String githubRepoUrl) {
  echo 'Commit the version increment to Git...'
  sh 'git config --global user.email "jenkins@example.com"'
  sh 'git config --global user.name "Jenkins"'
  withCredentials([usernamePassword(credentialsId: 'github-pat', usernameVariable: 'GITHUB_USERNAME', passwordVariable: 'GITHUB_PAT')]) {
    def gitRepoUrl = githubRepoUrl
    sh "git remote set-url origin https://${GITHUB_USERNAME}:${GITHUB_PAT}@${gitRepoUrl.replace('https://', '')}"
    dir('8 - Build Automation & CI-CD with Jenkins/jenkins-exercises/app') {
      sh 'git add package.json'
    }
    sh "git commit -m 'Update version to ${imageVersion}'"
    sh 'git push origin HEAD:main'
  }
}