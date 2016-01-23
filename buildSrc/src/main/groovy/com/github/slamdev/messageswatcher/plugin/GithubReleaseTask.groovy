package com.github.slamdev.messageswatcher.plugin

import com.github.slamdev.messageswatcher.plugin.GithubReleaseExtension.Asset
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.bundling.War
import org.kohsuke.github.GHRelease
import org.kohsuke.github.GHReleaseBuilder
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub

import java.util.regex.Pattern

class GithubReleaseTask extends DefaultTask {

    private static final Pattern URL_PATTERN = Pattern.compile(
            '\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])')

    private final GithubReleaseExtension extension

    GithubReleaseTask() {
        extension = project.extensions.getByType(GithubReleaseExtension)
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    void run() {
        GitHub github = GitHub.connectUsingOAuth(extension.oAuthToken)
        String repoName = extension.repoName ?: acquireRepoName()
        GHRepository repo = github.getRepository(repoName)
        String tag = extension.snapshotReleaseTag
        repo.listReleases().findAll { it.tagName == tag }.each { it.delete() }
        repo.refs.findAll { it.ref == 'refs/tags/' + tag }.each { it.delete() }
        GHReleaseBuilder builder = repo.createRelease(extension.snapshotReleaseTag)
        GHRelease release = builder.name(extension.snapshotReleaseName).prerelease(true).body('').create()
        acquireAssets().each { release.uploadAsset(it.file, it.contentType) }
    }

    private String acquireRepoName() {
        String repoUrl = exec('git remote -v', project.rootDir).split('/n').first().find(URL_PATTERN)
        repoUrl.replace('https://github.com/', '').replace('.git', '')
    }

    private List<Asset> acquireAssets() {
        if (!extension.assets) {
            List<Project> projects = [project] + project.subprojects
            extension.assets.addAll(projects.collectMany {
                List<Asset> assets = []
                if (it.plugins.hasPlugin(WarPlugin)) {
                    File file = ((War) it.tasks.getByName(WarPlugin.WAR_TASK_NAME)).archivePath
                    Asset asset = [contentType: 'application/zip', file: file]
                    assets << asset
                }
                if (it.plugins.hasPlugin('com.github.johnrengelman.shadow')) {
                    File file = ((Jar) it.tasks.getByName('shadowJar')).archivePath
                    Asset asset = [contentType: 'application/zip', file: file]
                    assets << asset
                }
                assets
            })
        }
        extension.assets
    }

    private static String exec(String command, File workDir) {
        StringBuilder out = new StringBuilder()
        StringBuilder error = new StringBuilder()
        List<String> environmentVariables = [/*'VAR_NAME=var-value', 'VAR_NAME=var-value'*/]
        try {
            Process proc = command.execute(environmentVariables, workDir);
            proc.consumeProcessOutput(out, error)
            proc.waitForOrKill(1000)
        } catch (IOException e) {
            error.append(e.message)
        }
        if (error.length()) {
            throw new IOException("$error\nCommand: $command")
        }
        out.toString()
    }
}
