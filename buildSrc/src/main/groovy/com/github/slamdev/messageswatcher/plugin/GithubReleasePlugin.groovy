package com.github.slamdev.messageswatcher.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GithubReleasePlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        target.extensions.create('githubRelease', GithubReleaseExtension)
        target.task('githubRelease', type: GithubReleaseTask)
    }
}
