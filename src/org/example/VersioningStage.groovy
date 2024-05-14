#!/usr/bin/env groovy

package org.example

def call(String versionIncrement) {
  echo "Incrementing application version with ${versionIncrement}"
  dir('app') {
    def versionType = input(
      id: 'versionType',
      message: 'Select the version increment type:',
      ok: 'Increment',
      parameters: [
        choice(name: 'type', choices: ['patch', 'minor', 'major'], description: 'Version increment type')
      ]
    )
    sh "npm version ${versionType}"
    def packageJson = readJSON file: 'package.json'
    def appVersion = packageJson.version
    def buildNumber = env.BUILD_NUMBER
    def imageVersion = "${appVersion}-${buildNumber}"
    env.IMAGE_VERSION = imageVersion
  }
}