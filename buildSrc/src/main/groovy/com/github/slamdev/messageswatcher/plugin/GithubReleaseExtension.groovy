package com.github.slamdev.messageswatcher.plugin

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode

class GithubReleaseExtension {

    String oAuthToken

    String repoName

    String snapshotReleaseName = 'Latest snapshot'

    String snapshotReleaseTag = 'latest-snapshot'

    List<Asset> assets = []

    @SuppressWarnings('GroovyUnusedDeclaration')
    asset(Closure closure) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        Asset asset = new Asset()
        closure.delegate = asset
        assets << asset
        closure()
    }

    @EqualsAndHashCode
    @Canonical
    static class Asset implements Serializable {

        private static final long serialVersionUID = 1L

        File file

        String contentType
    }
}
