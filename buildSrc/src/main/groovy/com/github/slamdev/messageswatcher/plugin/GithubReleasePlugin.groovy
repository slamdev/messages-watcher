package com.github.slamdev.messageswatcher.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GithubReleasePlugin implements Plugin<Project> {

    static final PLUGIN_NAME = 'githubRelease'

    @Override
    void apply(Project target) {
        target.extensions.create(PLUGIN_NAME, GithubReleaseExtension)
        target.task(PLUGIN_NAME, type: GithubReleaseTask)
    }
}
